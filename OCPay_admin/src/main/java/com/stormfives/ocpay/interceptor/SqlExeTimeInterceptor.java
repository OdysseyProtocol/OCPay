package com.stormfives.ocpay.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by TerryJ on 2017/1/15.
 *
 *   统计 sql执行的时间
 */
@Intercepts({ @Signature(method = "query", type = Executor.class, args = {MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class }),
        @Signature(method = "update", type = Executor.class, args = {  MappedStatement.class,Object.class  }),
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class, CacheKey.class,BoundSql.class}),} )
public class SqlExeTimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        result = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Configuration configuration = mappedStatement.getConfiguration();
        Connection connection = configuration.getEnvironment().getDataSource().getConnection();
        try {
            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            //新增到数据库
            if (time > 0 && boundSql.getSql().toLowerCase().startsWith("select")) {
                String sql = "insert into log_sqlexetime(sqlstr,exetime) values (?,?)";
                String[] keys = {"sqlstr", "exetime"};
                //替换执行sql中的参数
                String sqlstr = this.showSql(configuration, boundSql);
                String[] values = {sqlstr, time + ""};
                this.insertLogExeTime(mappedStatement, connection, sql, keys, values);
            }
            //拦截记录update语句
            this.logupdatesql(boundSql, mappedStatement, connection);
            //执行完之后关闭连接
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 保存到sql执行记录表
     * @param mappedStatement
     * @param connection
     * @param selectSql
     */
    private void insertLogExeTime(MappedStatement mappedStatement, Connection connection,String selectSql,
                                  String[] keys,String[] values) {
        MapperMethod.ParamMap logParamMap = this.paramMap(keys,values);
        List<ParameterMapping> logParameterMappings =this.parameterMappings(keys,mappedStatement);
        BoundSql selectBoundSql = new BoundSql(mappedStatement.getConfiguration(), selectSql, logParameterMappings, logParamMap);
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, logParamMap, selectBoundSql);
        //通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(selectSql);
            //通过parameterHandler给PreparedStatement对象设置参数
            parameterHandler.setParameters(pstmt);
            //之后就是执行获取总记录数的Sql语句和获取结果了。
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 拦截修改语句
     * @return
     * @throws Throwable
     */
    public Object logupdatesql(BoundSql boundSql,MappedStatement mappedStatement,Connection connection) throws Throwable {
        Object result = null;

        //不是更新的sql，直接pass
        if(!boundSql.getSql().startsWith("update") && !boundSql.getSql().startsWith("delete") ){
            return null;
        }

        String sqlInfo = showSql(mappedStatement.getConfiguration(),boundSql);

        String[] sqlSplit = getSqlInfo(boundSql.getSql());

        MapperMethod.ParamMap paramMap = new MapperMethod.ParamMap();

        List<ParameterMapping> parameterMappings = new ArrayList<>();

        //需要更新的参数
        List<String> updateParam = new ArrayList<>();
        if(!StringUtils.isEmpty(sqlSplit[3])){
            Collections.addAll(updateParam, sqlSplit[3].split(","));
        }

        String selectSql = selectsql(sqlSplit[0],sqlInfo.substring(sqlInfo.indexOf("where")));

        //给当前的page参数对象设置总记录数
        String beforeUpdateData = this.getBeforeUpdateData(mappedStatement,connection,selectSql,parameterMappings,paramMap,updateParam);
        //表名
        String tableName = sqlSplit[0];
        String logUpdateDataSql = "insert into log_updatedata (tablename,beforeupdatedata,updatesql) values (?,?,?)";
        String[] keys={"tablename","beforeupdatedata","updatesql"};
        String[] values={tableName,beforeUpdateData.trim(),sqlInfo};

        insertLogExeTime(mappedStatement,connection,logUpdateDataSql,keys,values);
        return null;
    }


    private static String[] getSqlInfo(String sql) throws JSQLParserException {
        sql = sql.trim().toLowerCase();
        String[] sqlsplit = new String[4];
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        if(sql.startsWith("update") ){
            Update update = (Update)parserManager.parse(new StringReader(sql));

            //更新的字段名
            List columns = update.getColumns();
            String columnStr = "";
            for(Object column : columns){
                columnStr += ","+column;
            }
            if(!StringUtils.isEmpty(columnStr)){
                columnStr = columnStr.substring(1);
            }

            String tableName = update.getTables().get(0).getName();
            sqlsplit[0] = tableName;
            sqlsplit[1] = update.getWhere().toString();
            sqlsplit[2] = update.getExpressions().toString();
            sqlsplit[3] = columnStr;
        } else if(sql.startsWith("delete")){
            Delete delete = (Delete)parserManager.parse(new StringReader(sql));
            sqlsplit[0] = delete.getTable().getName();
            sqlsplit[1] =  delete.getWhere().toString();
            sqlsplit[2] =  "";
        }else if(sql.startsWith("insert")){
            Insert insert = (Insert)parserManager.parse(new StringReader(sql));
            sqlsplit[0] = insert.getTable().getName();
            sqlsplit[1] = "";
            sqlsplit[2] = insert.getItemsList().toString();
        }
        return sqlsplit;
    }

    /**
     * 获取更新前的数据
     *
     * @param mappedStatement
     * @param connection
     * @param selectSql
     * @param paralist
     * @param updateParam
     */
    private String getBeforeUpdateData(MappedStatement mappedStatement, Connection connection,String selectSql,
                                       List<ParameterMapping> parameterMappings,MapperMethod.ParamMap paralist,
                                       List<String> updateParam) {
        //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
        //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
        //通过查询Sql语句获取到对应的计算总记录数的sql语句
        //List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //利用Configuration、查询记录数的Sql语句、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql selectBoundSql = new BoundSql(mappedStatement.getConfiguration(), selectSql, parameterMappings, paralist);
        //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paralist, selectBoundSql);
        //通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(selectSql);
            //通过parameterHandler给PreparedStatement对象设置参数
            parameterHandler.setParameters(pstmt);
            //之后就是执行获取总记录数的Sql语句和获取结果了。
            rs = pstmt.executeQuery();
           // StringBuffer updateParamValue = new StringBuffer();
            Map<String,Object>updateParamValue = new HashMap(){};
            if (rs.next()) {
                for(String param : updateParam){
                    //updateParamValue.append(" "+param+"="+rs.getString(param));
                    updateParamValue.put(param,rs.getString(param));
                }
            }
            return JSON.toJSONString(updateParamValue);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    private static String selectsql(String tablename ,String wherestr){
        String selectsql = "select * from " + tablename +" "+ wherestr;
        return selectsql;
    }

    public MapperMethod.ParamMap paramMap(String[] key,String[] value){
        MapperMethod.ParamMap logParamMap = new MapperMethod.ParamMap();
        for(int i=0;i<key.length;i++){
            logParamMap.put(key[i],value[i]);
        }
        return logParamMap;
    }

    public List<ParameterMapping> parameterMappings(String[] keys,MappedStatement mappedStatement ){
        List<ParameterMapping> logParameterMappings = new ArrayList<>();
        for(int i=0;i<keys.length;i++){
            ParameterMapping parameterMapping=new ParameterMapping.Builder(mappedStatement.getConfiguration(),keys[i],String.class).build();
            logParameterMappings.add(parameterMapping);
        }
        return logParameterMappings;
    }

    /**
     * 替换sql中的参数
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}

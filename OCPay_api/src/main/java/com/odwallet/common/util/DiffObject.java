package com.odwallet.common.util;

import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * Created by zxb on 21/02/2017.
 */
@Component
public class DiffObject<T> {

    public String contrastObj(Object oldBean, Object newBean) {

        String str = "";

        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;

        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if (o2 == null) {   //如果信值不是空  则判断
                    continue;
                }

                if (o1 == null){

                    if (i != 1) {
                        str += ";";
                    }
                    str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                    i++;
                }

                if (o1 instanceof BigDecimal){
                    if (((BigDecimal) o1).doubleValue() != ((BigDecimal) o2).doubleValue()){
                        if (i != 1) {
                            str += ";";
                        }
                        str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                        i++;
                    }
                } else if (o1 instanceof Double){
                    if (((Double) o1).doubleValue() != ((Double) o2).doubleValue()){
                        if (i != 1) {
                            str += ";";
                        }
                        str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                        i++;
                    }
                }else if (!o1.toString().equalsIgnoreCase(o2.toString())){
                    if (i != 1) {
                        str += ";";
                    }
                    str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                    i++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

    //返回对象不为空的字段的name及value
    public String contrastObj(Object object){
        String str = "";

        T pojo = (T) object;

        try {
            Class clazz = pojo.getClass();
            Field[] fields = pojo.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                //反射机制,通过属性名获取属性值
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                //获取属性get方法
                Method getMethod = pd.getReadMethod();
                //获取属性值
                Object value = getMethod.invoke(pojo);
                if (value == null) {   //如果属性值为空  跳过
                    continue;
                }
                if (value instanceof BigDecimal){
                        if (i != 1) {
                            str += ";";
                        }
                        str += i + "、" + field.getName() + " : " + value + " ";
                        i++;
                } else if (value instanceof Double){
                        if (i != 1) {
                            str += ";";
                        }
                        str += i + "、" + field.getName() + " : " + value + " ";
                        i++;
                }else{
                    if (i != 1) {
                        str += ";";
                    }
                    str += i + "、" + field.getName() + " : " + value + " ";
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}

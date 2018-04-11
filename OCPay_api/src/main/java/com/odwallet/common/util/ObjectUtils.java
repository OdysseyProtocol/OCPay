package com.odwallet.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: LuoYuanchun
 * Date: 17/8/24
 * Time: 下午4:16
 */
public class ObjectUtils<T> {


    //返回对象不为空的字段的name及value
    public String toString(Object object) {

        StringBuffer str = new StringBuffer("");

        T pojo = (T) object;

        try {
            Class clazz = pojo.getClass();
            Field[] fields = pojo.getClass().getDeclaredFields();
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
                if (value instanceof BigDecimal) {
                    str.append(field.getName() + " : " + value + " " + '\r');

                } else if (value instanceof Double) {

                    str.append(field.getName() + " : " + value + " " + '\r');

                } else {

                    str.append(field.getType() + " " + field.getName() + " : " + value + " " + '\r');

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}

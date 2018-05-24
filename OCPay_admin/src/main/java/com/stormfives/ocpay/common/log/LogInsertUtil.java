package com.stormfives.ocpay.common.log;

import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlw on 2017/9/8.
 */
@Component
public class LogInsertUtil<T> {

    public final static String NEW_LINE_WINDOWS = "\r\n";

    public String createLog(T item, Class clazz, Boolean ignoreNull) {
        List<T> list = new ArrayList<T>();
        list.add(item);
        return createLog(list, clazz, ignoreNull);
    }

    public String createLog(List<T> list, Class clazz, Boolean ignoreNull) {

        StringBuilder log = new StringBuilder();
        //数据内容
        log.append(listToContent(list, clazz, "; ", "; ", ignoreNull));
        return log.toString();
    }

    private StringBuilder listToContent(List<T> list, Class clazz, String propertySeparator, String itemSeparator, Boolean ignoreNull) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder subContent = new StringBuilder();
        StringBuilder line = new StringBuilder();
        Object itemProperty = new Object();
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            line.setLength(0);

            for (int j = 0; j < fields.length; j++) {
                try {
                    Remark remark = fields[j].getAnnotation(Remark.class);
                    if (null == remark) {
                        continue;
                    }

                    PropertyDescriptor pd = new PropertyDescriptor(fields[j]
                            .getName(), clazz);
                    Method getMethod = pd.getReadMethod();
                    itemProperty = getMethod.invoke(item);

                    if (ignoreNull && itemProperty == null) {
                        continue;
                    }
                    line.append(remark.value() + ":");
                    line.append(itemProperty == null ? "" : itemProperty);

                    line.append(propertySeparator);
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (line.lastIndexOf(propertySeparator) > 0) {
                //去掉末尾分隔符
                subContent.append(line.subSequence(0, line.length()- propertySeparator.length()).toString());
            } else {
                subContent.append(line);
            }
            subContent.append(itemSeparator);
        }
        return subContent;
    }
}

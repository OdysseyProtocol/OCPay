package com.stormfives.ocpay.common;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.ReflectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class EntityUtils {

    public static <R, T> R transformVoResponse(T source, Class<R> clazz) {
        Object target = ReflectUtils.newInstance(clazz);
        BeanUtils.copyProperties(source, target);
        return (R) target;
    }

    public static <R, T> List<R> transformVoResponse(List<T> source, Class<R> clazz) {
        return source.stream()
                .parallel()
                .map(param -> EntityUtils.transformVoResponse(param, clazz))
                .collect(Collectors.toList());
    }
}
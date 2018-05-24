package com.stormfives.ocpay.common.util;

import org.springframework.util.CollectionUtils;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Created by zhaimi on 15/12/8.
 */
public class EnumerationUtils {
    public static <T> Iterable<T> toIterable(Supplier<Enumeration<T>> enumerationSupplier) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return CollectionUtils.toIterator(enumerationSupplier.get());
            }
        };
    }
}

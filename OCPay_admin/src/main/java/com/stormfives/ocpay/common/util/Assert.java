package com.stormfives.ocpay.common.util;
/**
 * assert util-tool
 * @author niuchangqing
 *
 */
public abstract class Assert {

    protected Assert() {}

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void areNotNull(String message,Object ... obj) {
    	notNull(obj, message);
    	for (Object object : obj) {
			if (object==null) {
				throw new IllegalArgumentException(message);
			}
		}
    }
    public static void equals(Object obj,Object obj2, String message) {
        if (obj == null&&obj2==null) {
        	return;
        }
        if (obj == null || obj2==null) {
        	throw new IllegalArgumentException(message);
		}
        if (!obj.equals(obj2)) {
        	throw new IllegalArgumentException(message);
		}
    }

}

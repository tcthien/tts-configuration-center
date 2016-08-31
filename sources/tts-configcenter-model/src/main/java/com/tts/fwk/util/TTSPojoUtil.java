package com.tts.fwk.util;

public class TTSPojoUtil {
    public static String toString(Object... objs) {
        StringBuilder sb = new StringBuilder();
        if (objs != null) {
            String concat = "[";
            for (Object object : objs) {
                sb.append(concat).append(object);
                concat = ", ";
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public static boolean equalsObject(Object value1, Object value2) {
        return (value1 == null && value2 == null) || (value1 != null && value1.equals(value2));
    }

    public static int buildHashCode(Object... objects) {
        if (objects == null) {
            return 0;
        }
        int result = 1;
        for (Object object : objects) {
            result = 31 * result + (object != null ? object.hashCode() : 0);
        }
        return result;
    }
}

package com.kneissler.language.util;

import java.lang.reflect.Field;

/**
 * Helper class to convert pojo to json string (we prefer to implement this with minimally required
 * functionality by ourselves instead of adding a deoendency on some powerful library like Jackson or Gson.
 */
public class Json {
    public static String asString(Object o) {
        StringBuilder sb = new StringBuilder();
        appendObject(sb, o);
        return sb.toString();
    }

    private static void appendObject(StringBuilder sb, Object o) {
        if (o instanceof String) {
            sb
            .append("\"")
            .append(o)
            .append("\"");
        } else if (o.getClass().isArray()){
            sb.append("[");
            appendArray(sb, (Object[]) o);
            sb.append("]");
        } else {
            sb.append("{");
            appendFields(sb, o);
            sb.append("}");
        }
    }

    public static void appendFields(StringBuilder sb, Object o) {
        boolean first = true;
        Class<?> cl = o.getClass();
        for (Field f : cl.getFields()) {
            try {
                Object value = f.get(o);
                if (value != null) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(",");
                    }
                    appendObject(sb, f.getName());
                    sb.append("=");
                    appendObject(sb, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Could not access field "+f, e);
            }
        }
    }

    private static void appendArray(StringBuilder sb, Object[] array) {
        boolean first = true;
        for (Object o : array) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            appendObject(sb, o);
        }
    }
}

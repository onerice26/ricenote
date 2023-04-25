package priv.onerice.ricenote.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author onerice
 * @date 2023/4/10
 * @apiNote
 */
public class ObjectUtil {

    public static boolean equal(Object s1, Object s2) {
        if (s1 == s2) {
            return true;
        } else {
            return s1 != null && s2 != null ? s1.equals(s2) : false;
        }
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else {
            return object instanceof Map ? ((Map) object).isEmpty() : false;
        }
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}

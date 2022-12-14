import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 给 mybatis 映射文件提供帮助的工具类. 请一定不要删除!
 */
public class Ognl {

    /**
     * <pre>
     * 在下面的情况下返回 true
     * null        空对象
     * array       长度为 0
     * Collection  长度为 0
     * map         长度为 0
     * String      空白字
     * </pre>
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        String str = obj.toString().trim();
        return str.length() == 0 || "null".equalsIgnoreCase(str);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

}

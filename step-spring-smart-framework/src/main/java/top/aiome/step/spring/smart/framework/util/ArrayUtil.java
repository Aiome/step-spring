package top.aiome.step.spring.smart.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Description: 数组操作工具类<br>
 *
 * @author mahongyan 2019-08-03 18:10
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}

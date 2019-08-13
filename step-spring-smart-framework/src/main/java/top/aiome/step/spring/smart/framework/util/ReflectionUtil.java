package top.aiome.step.spring.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description: 反射工具类<br>
 *
 * @author mahongyan 2019-08-03 17:05
 */
public final class ReflectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     *
     * @author mahongyan
     * @date 2019-08-03 17:10
     */
    public static Object newInstance(Class<?> clz) {
        Object instance;
        try {
            instance = clz.newInstance();
        } catch (Exception e) {
            logger.error("创建实例 {} 失败", clz.getName(), e);
            throw new RuntimeException(e);
        }

        return instance;
    }

    /**
     * 调用方法
     *
     * @author mahongyan
     * @date 2019-08-03 17:13
     */
    public static Object invokeMethod(Object obj, Method method, Object ...args) {
        Object result;
        method.setAccessible(true);
        try {
            result = method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("调用方法{}失败", Object.class.getName() + "." + method, e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 设置成员变量的值
     *
     * @author mahongyan
     * @date 2019-08-03 17:17
     */
    public static void setField(Object obj, Field field, Object value) {

        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            logger.error("设置字段{}值{}失败", field, value, e);
            throw new RuntimeException(e);
        }
    }
}

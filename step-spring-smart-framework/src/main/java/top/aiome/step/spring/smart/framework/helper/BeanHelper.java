package top.aiome.step.spring.smart.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import top.aiome.step.spring.smart.framework.util.ReflectionUtil;

/**
 * Description: 助手类<br>
 *
 * @author mahongyan 2019-08-03 17:18
 */
public final class BeanHelper {

    private static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    /**
     * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
     *
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        logger.info("<><><><><><><><><> Start 初始化 Smart Framework Bean对象集合");
        for (Class<?> clz : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(clz);

            BEAN_MAP.put(clz, obj);

            logger.info("Bean集合增加:{}", clz.getName());
        }
        logger.info("<><><><><><><><><> Stop 初始化 Smart Framework Bean对象集合 完毕");
    }

    /**
     * 获取 Bean 映射
     *
     * @author mahongyan
     * @date 2019-08-03 17:22
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取 Bean 实例
     *
     * @author mahongyan
     * @date 2019-08-03 17:24
     */
    public static <T> T getBean(Class<T> clz) {
        if (!BEAN_MAP.containsKey(clz)) {
            throw new RuntimeException("未找到" + clz +"类");
        }

        return (T) BEAN_MAP.get(clz);
    }

    /**
     * 设置Bean实例
     *
     * @author mahongyan
     * @date 2019-08-11 13:49
     */
    public static void setBean(Class<?> clz, Object obj) {
        BEAN_MAP.put(clz, obj);
    }
}

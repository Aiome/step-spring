package top.aiome.step.spring.smart.framework.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

import top.aiome.step.spring.smart.framework.annotation.Inject;
import top.aiome.step.spring.smart.framework.util.ArrayUtil;
import top.aiome.step.spring.smart.framework.util.CollectionUtil;
import top.aiome.step.spring.smart.framework.util.ReflectionUtil;

/**
 * Description: 依赖注入助手类<br>
 *
 * @author mahongyan 2019-08-03 17:31
 */
public final class IocHelper {

    private static final Logger logger = LoggerFactory.getLogger(IocHelper.class);

    static {
        // 获取所有的Bean 类与Bean实例之间的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        logger.info("<><><><><><><><><> Start Smart Framework Ioc依赖注入");

        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历 Beam Map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 从 BeanMap 中获取bean类和bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                // 获取Bean类定义的所有成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    // 遍历 Bean Field
                    for (Field beanField : beanFields) {
                        // 判断当前Bean Field是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 在bean map 中获取bean field 对一个的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if (beanFieldInstance != null) {
                                // 通过反射初始化 BeanFild的值
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                                logger.info("Bean对象:{} 字段:{} 注入:{}", beanInstance.getClass().getName(), beanField.getName(), beanFieldInstance.getClass().getName());
                            }
                        }
                    }
                }
            }
        }

        logger.info("<><><><><><><><><> Stop Smart Framework Ioc依赖注入 完毕");
    }
}

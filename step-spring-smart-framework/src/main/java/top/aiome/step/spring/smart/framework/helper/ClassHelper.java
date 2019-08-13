package top.aiome.step.spring.smart.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import top.aiome.step.spring.smart.framework.annotation.Controller;
import top.aiome.step.spring.smart.framework.annotation.Service;
import top.aiome.step.spring.smart.framework.util.ClassUtil;

/**
 * Description: 类操作助手类<br>
 *
 * @author mahongyan 2019-08-03 16:53
 */
public final class ClassHelper {

    private static final Logger logger = LoggerFactory.getLogger(ClassHelper.class);
    /**
     * 定义类集合(用于存储所有加载的类)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
        logger.info("<><><><><><><><><> Start 初始化 Smart Framework 类集合 {} 下已加载的类", basePackage);
        CLASS_SET.forEach(item -> {logger.info("类集合增加:{}", item.getName());});
        logger.info("<><><><><><><><><> Stop 初始化 Smart Framework 类集合 {} 下已的类增加完毕", basePackage);
    }

    /**
     * 获取应用包名下的所有类
     *
     * @author mahongyan
     * @date 2019-08-03 16:57
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有 service 类
     *
     * @author mahongyan
     * @date 2019-08-03 17:00
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(Service.class)) {
                classSet.add(clz);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有 controller 类
     *
     * @author mahongyan
     * @date 2019-08-03 17:01
     */
    public static Set<Class<?>> getControllerSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(Controller.class)) {
                classSet.add(clz);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有Bean类(包括Service、Controller)
     *
     * @author mahongyan
     * @date 2019-08-03 17:02
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerSet());

        return beanClassSet;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现）
     *
     * @author mahongyan
     * @date 2019-08-11 14:02
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (superClass.isAssignableFrom(clz) && !superClass.equals(clz)) {
                classSet.add(clz);
            }
        }

        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     *
     * @author mahongyan
     * @date 2019-08-11 14:04
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clz : CLASS_SET) {
            if (clz.isAnnotationPresent(annotationClass)) {
                classSet.add(clz);
            }
        }

        return classSet;
    }
}

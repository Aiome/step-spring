package top.aiome.step.spring.smart.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import top.aiome.step.spring.smart.framework.annotation.Aspect;
import top.aiome.step.spring.smart.framework.annotation.Service;
import top.aiome.step.spring.smart.framework.proxy.AspectProxy;
import top.aiome.step.spring.smart.framework.proxy.Proxy;
import top.aiome.step.spring.smart.framework.proxy.ProxyManager;
import top.aiome.step.spring.smart.framework.proxy.TransactionProxy;

/**
 * Description: 方法拦截器助手类<br>
 *
 * @author mahongyan 2019-08-11 14:12
 */
public class AopHelper {

    private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            logger.info("<><><><><><><><><> Start Smart Framework Aop替换代理对象");
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            // 为Bean容器内每一个被代理的切点类替换为CGLIB实例
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                logger.info("Bean集合对象替换: {} 替换为 {}", BeanHelper.getBean(targetClass).getClass(), proxy.getClass());
                BeanHelper.setBean(targetClass, proxy);
            }
            logger.info("<><><><><><><><><> Stop Smart Framework Aop替换代理对象 完毕");
        } catch (Exception e) {
            logger.error("aop 失败", e);
        }
    }

    /**
     * 返回Aspect的所有切点类
     *
     * @author mahongyan
     * @date 2019-08-12 15:14
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();

        if (!annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }

        return targetClassSet;
    }

    /**
     * 返回所有切面类与所有切点类的映射关系
     *
     * @author mahongyan
     * @date 2019-08-12 15:13
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        // key：切面类 value: 切点类
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);

        return proxyMap;
    }

    /**
     * 返回TransactionProxy与所有@Service的映射
     *
     * @author mahongyan
     * @date 2019-08-12 22:28
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);

        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }

    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);

        for (Class<?> proxyClass : proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
    }

    /**
     * 返回切点类与切面List的关系（此处多个切面切面没有指定顺序，Spring中有处理切面优先级）
     *
     * @author mahongyan
     * @date 2019-08-12 15:18
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        // key：切面类 value: 切点类
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }
}

package top.aiome.step.spring.smart.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Description: 代理管理器<br>
 *
 * @author mahongyan 2019-08-04 15:11
 */
public class ProxyManager {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {

        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
                return new ProxyChain(targetClass, o, method, proxy, objects, proxyList).doProxyChain();
            }
        });
    }
}

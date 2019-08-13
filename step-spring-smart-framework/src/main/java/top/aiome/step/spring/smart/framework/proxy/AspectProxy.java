package top.aiome.step.spring.smart.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Description: 切面代理<br>
 *
 * @author mahongyan 2019-08-04 15:17
 */
public abstract class AspectProxy implements Proxy {

    public static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    /**
     * 执行链式代理
     *
     * @author mahongyan
     * @date 2019-08-04 15:06
     */
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> clz = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();

        try {

            if (intercept(clz, method, params)) {
                before(clz, method, params);
                result = proxyChain.doProxyChain();
                after(clz, method, params, result);
            } else {
                result = proxyChain.doProxyChain();
            }

        } catch (Exception e) {
          logger.error("代理失败", e);
          error(clz, method, params, e);
          throw e;
        } finally {
            end();
        }

        return result;
    }

    public void begin() {
    }

    public boolean intercept(Class<?> clz, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void before(Class<?> clz, Method method, Object[] params) throws Throwable {
    }

    public void after(Class<?> clz, Method method, Object[] params, Object result) throws Throwable {
    }

    public void error(Class<?> clz, Method method, Object[] params, Throwable e) throws Throwable {
    }

    public void end() {
    }
}

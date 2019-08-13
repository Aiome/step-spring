package top.aiome.step.spring.smart.framework.proxy;

/**
 * Description: 代理接口<br>
 *
 * @author mahongyan 2019-08-04 14:53
 */
public interface Proxy {

    /**
     * 执行链式代理
     *
     * @author mahongyan
     * @date 2019-08-04 15:06
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}

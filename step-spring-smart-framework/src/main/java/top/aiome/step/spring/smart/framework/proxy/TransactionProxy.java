package top.aiome.step.spring.smart.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import top.aiome.step.spring.smart.framework.annotation.Transaction;
import top.aiome.step.spring.smart.framework.helper.DatabaseHelper;

/**
 * Description: <br>
 *
 * @author mahongyan 2019-08-12 22:05
 */
public class TransactionProxy implements Proxy{

    private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = ThreadLocal.withInitial(() -> false);

    /**
     * 执行链式代理
     *
     * @author mahongyan
     * @date 2019-08-04 15:06
     */
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(Boolean.TRUE);

            try {
                DatabaseHelper.beginTransaction();
                logger.debug("开始事务");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                logger.debug("回滚事务");
                throw e;
            } finally {
                FLAG_HOLDER.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }

        return result;
    }
}

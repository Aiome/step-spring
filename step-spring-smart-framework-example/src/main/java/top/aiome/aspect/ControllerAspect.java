package top.aiome.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import top.aiome.step.spring.smart.framework.annotation.Aspect;
import top.aiome.step.spring.smart.framework.annotation.Controller;
import top.aiome.step.spring.smart.framework.proxy.AspectProxy;

/**
 * Description: 拦截 Controller 所有方法<br>
 *
 * @author mahongyan 2019-08-11 13:41
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.info("---------- begin ----------");
        logger.info(String.format("class:%s", cls.getName()));
        logger.info(String.format("method:%s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        logger.info("time: " + (System.currentTimeMillis() - begin) + "ms");
        logger.info("----------- end -----------");
    }

}

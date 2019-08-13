package top.aiome.step.spring.smart.framework.bean;

import java.lang.reflect.Method;

/**
 * Description: 封装Action信息<br>
 *
 * @author mahongyan 2019-08-03 18:23
 */
public class Handler {
    /**
     * Action 方法
     */
    private Method actionMethod;

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.actionMethod = actionMethod;
        this.controllerClass = controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }
}

package top.aiome.step.spring.smart.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import top.aiome.step.spring.smart.framework.annotation.Action;
import top.aiome.step.spring.smart.framework.bean.Handler;
import top.aiome.step.spring.smart.framework.bean.Request;
import top.aiome.step.spring.smart.framework.util.ArrayUtil;
import top.aiome.step.spring.smart.framework.util.CollectionUtil;

/**
 * Description: 控制器助手类<br>
 *
 * @author mahongyan 2019-08-03 18:19
 */
public final class ControllerHelper {

    /**
     * 用于存放请求与处理器的映射关系
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        // 获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            // 遍历这些Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller 类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();

                if (ArrayUtil.isNotEmpty(methods)) {
                    // 遍历这些Controller中的方法
                    for (Method method : methods) {
                        // 判断当前方法是否带有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证URL映射规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    // 获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];

                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);

                                    // 初始化Action Map
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     *
     * @author mahongyan
     * @date 2019-08-03 18:38
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}

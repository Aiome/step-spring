package top.aiome.step.spring.smart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Action方法注解<br>
 *
 * @author mahongyan 2019-08-03 16:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 请求类型与路径
     *
     * @author mahongyan
     * @date 2019-08-03 16:50
     */
    String value();
}

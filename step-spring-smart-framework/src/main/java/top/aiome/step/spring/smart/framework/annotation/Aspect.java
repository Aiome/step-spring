package top.aiome.step.spring.smart.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 切面注解<br>
 *
 * @author mahongyan 2019-08-04 14:51
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     *
     * @author mahongyan
     * @date 2019-08-04 14:52
     */
    Class<? extends Annotation> value();
}

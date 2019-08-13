package top.aiome.step.spring.smart.framework;

import top.aiome.step.spring.smart.framework.helper.AopHelper;
import top.aiome.step.spring.smart.framework.helper.BeanHelper;
import top.aiome.step.spring.smart.framework.helper.ClassHelper;
import top.aiome.step.spring.smart.framework.helper.ControllerHelper;
import top.aiome.step.spring.smart.framework.helper.IocHelper;
import top.aiome.step.spring.smart.framework.util.ClassUtil;

/**
 * Description: 加载相应 Helper 类<br>
 *
 * @author mahongyan 2019-08-03 18:56
 */
public final class HelperLoader {

    /**
     * 加载
     *
     * @author mahongyan
     * @date 2019-08-03 23:56
     */
    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };

        for (Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}

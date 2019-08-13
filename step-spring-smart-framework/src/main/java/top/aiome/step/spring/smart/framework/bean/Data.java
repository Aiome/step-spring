package top.aiome.step.spring.smart.framework.bean;

/**
 * Description: 数据对象<br>
 *
 * @author mahongyan 2019-08-03 19:16
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}

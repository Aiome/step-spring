package top.aiome.step.spring.smart.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 视图对象<br>
 *
 * @author mahongyan 2019-08-03 19:13
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public View addModel(String key, Object value){
        model.put(key, value);

        return this;
    }
}

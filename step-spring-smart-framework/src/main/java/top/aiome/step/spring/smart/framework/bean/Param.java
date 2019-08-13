package top.aiome.step.spring.smart.framework.bean;

import java.util.Map;

import top.aiome.step.spring.smart.framework.util.CastUtil;

/**
 * Description: 请求参数对象<br>
 *
 * @author mahongyan 2019-08-03 19:03
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     *
     * @author mahongyan
     * @date 2019-08-03 19:04
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }
}

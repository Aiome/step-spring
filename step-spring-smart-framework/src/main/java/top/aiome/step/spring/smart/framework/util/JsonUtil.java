package top.aiome.step.spring.smart.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: JSON工具类<br>
 *
 * @author mahongyan 2019-08-03 21:24
 */
public final class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转为JSON
     *
     * @author mahongyan
     * @date 2019-08-03 21:30
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("java 对象转 json 失败", e);
            throw new RuntimeException(e);
        }

        return json;
    }

    /**
     * 将JSON转POJO
     *
     * @author mahongyan
     * @date 2019-08-03 21:33
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            logger.error("json 对象转 java 失败", e);
            throw new RuntimeException(e);
        }

        return pojo;
    }
}

package top.aiome.step.spring.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Description: 编码与解码工具类<br>
 *
 * @author mahongyan 2019-08-03 21:16
 */
public final class CodecUtil {

    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将url编码
     *
     * @author mahongyan
     * @date 2019-08-03 21:18
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            logger.error("编码失败", e);
            throw new RuntimeException(e);
        }

        return target;
    }

    /**
     * 将URL解码
     *
     * @author mahongyan
     * @date 2019-08-03 21:20
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            logger.error("解码失败", e);
            throw new RuntimeException(e);
        }

        return target;
    }
}

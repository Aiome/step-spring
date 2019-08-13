package top.aiome.step.spring.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;

/**
 * Description: 流操作工具类<br>
 *
 * @author mahongyan 2019-08-03 21:09
 */
public final class StreamUtil {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     *
     * @author mahongyan
     * @date 2019-08-03 21:15
     */
    public static String getString(ServletInputStream stream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            logger.error("获取字符串失败", e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}

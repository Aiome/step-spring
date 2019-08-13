package top.aiome.step.spring.smart.framework.helper;

import java.util.Properties;

import top.aiome.step.spring.smart.framework.ConfigConstant;
import top.aiome.step.spring.smart.framework.util.PropsUtil;

/**
 * Description: 属性文件助手类<br>
 *
 * @author mahongyan 2019-08-03 15:25
 */
public final class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取jdbc驱动
     *
     * @author mahongyan
     * @date 2019-08-03 15:57
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取jdbc url
     *
     * @author mahongyan
     * @date 2019-08-03 15:58
     */
    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取jdbc name
     *
     * @author mahongyan
     * @date 2019-08-03 15:59
     */
    public static String getJdbcUsername() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取jdbc 密码
     *
     * @author mahongyan
     * @date 2019-08-03 16:00
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     *
     * @author mahongyan
     * @date 2019-08-03 16:00
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用JSP路径
     *
     * @author mahongyan
     * @date 2019-08-03 16:01
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}

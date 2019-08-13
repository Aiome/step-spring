package top.aiome.step.spring.smart.framework;

/**
 * Description: 提供相关配置项常量<br>
 *
 * @author mahongyan 2019-08-03 15:17
 */
public interface ConfigConstant {

    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USERNAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    String APP_JSP_PATH = "smart.fraamework.app.jsp_path";
    String APP_ASSET_PATH = "smart.framework.app.asset_path";
}

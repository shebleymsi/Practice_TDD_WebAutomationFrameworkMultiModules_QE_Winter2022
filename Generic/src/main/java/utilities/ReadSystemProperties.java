package utilities;

import java.util.Properties;

public class ReadSystemProperties {


    public static void getSystemInfo() {

        System.out.println(System.getProperty("os.name"));

    }


    static Properties readProperty = ReadPropertiesFrom.loadProperties("src/main/resources/Config.properties");


    public static String getEnvUrl() {
        String url = null;
        if (envName.equalsIgnoreCase("Dev")) {
            url = readProperty.getProperty("DEV_URL");
        } else if (envName.equalsIgnoreCase("Qa")) {
            url = readProperty.getProperty("QA_URL");
        } else if (envName.equalsIgnoreCase("Prod")) {
            url = readProperty.getProperty("PROD_URL");
        }
        return url;
    }

    // Default Environment
    public static String envName = System.getProperty("env", "Prod");


}

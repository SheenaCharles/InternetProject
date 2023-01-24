package dataProvider;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    protected static Properties properties;
    public ConfigReader() {
        BufferedReader reader;
        String propertyFilePath = "C:\\Users\\Anand Sambanthan\\Desktop\\Java Programming\\gitpractice\\InternetProject\\src\\test\\java\\configs\\Configuration.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        if(baseUrl != null) {
            return baseUrl;
        } else {
            throw new RuntimeException("base_Url not specified in the configuration.properties file.");
        }
    }

    public String getSauceLabUserName() {
        String sauceLabUsername = properties.getProperty("sauceLabUsername");
        if(sauceLabUsername != null) {
            return sauceLabUsername;
        } else {
            throw new RuntimeException("sauceLabUsername not specified in the configuration.properties file.");
        }
    }

    public String getSauceLabAccessKey() {
        String sauceLabAccessKey = properties.getProperty("sauceLabAccessKey");
        if(sauceLabAccessKey != null) {
            return sauceLabAccessKey;
        } else {
            throw new RuntimeException("sauceLabAccessKey not specified in the configuration.properties file.");
        }
    }
    public String getSauceLabUrl() {
        String sauceLabUrl = properties.getProperty("sauceLabUrl");
        if(sauceLabUrl != null) {
            return sauceLabUrl;
        } else {
            throw new RuntimeException("sauceLabUrl not specified in the configuration.properties file.");
        }
    }
    public String getLambdaTestUserName() {
        String lambdaTestUsername = properties.getProperty("lambdaTestUsername");
        if(lambdaTestUsername != null) {
            return lambdaTestUsername;
        } else {
            throw new RuntimeException("lambdaTestUsername not specified in the configuration.properties file.");
        }
    }

    public String getLambdaTestAccessKey() {
        String lambdaTestAccessKey = properties.getProperty("lambdaTestAccessKey");
        if(lambdaTestAccessKey != null) {
            return lambdaTestAccessKey;
        } else {
            throw new RuntimeException("lambdaTestAccessKey not specified in the configuration.properties file.");
        }
    }
    public String getLambdaURL() {
        String lambdaTestUrl = properties.getProperty("lambdaTestUrl");
        if(lambdaTestUrl != null) {
            return lambdaTestUrl;
        } else {
            throw new RuntimeException("lambdaTestUrl not specified in the configuration.properties file.");
        }
    }
}

package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class responsible for reading values from .properties files.
 *
 * Example environment.properties:
 *
 * baseUrl=https://automationexercise.com
 * browser=chrome
 * timeout=10
 *
 * Example usage:
 *
 * String url = PropertiesReader.get("src/test/resources/environment.properties", "baseUrl");
 */
public class PropertiesReader {

    /**
     * Loads the whole properties file into a Properties object.
     */
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read properties file from: " + filePath, e
            );
        }

        return properties;
    }

    /**
     * Reads a single value by its key.
     */
    public static String get(String filePath, String key) {
        String value = loadProperties(filePath).getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Key '" + key + "' not found in: " + filePath
            );
        }

        return value;
    }
}
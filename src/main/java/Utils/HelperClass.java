
package Utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Utility class responsible for reading test data from JSON files.
 *
 * The class uses Gson to deserialize JSON data into Java objects.
 *
 * Example JSON:
 *
 * {
 *   "username": "tomsmith",
 *   "password": "SuperSecretPassword!"
 * }
 *
 * Example usage:
 *
 * LoginData data = JsonDataReader.readJson(
 *         "src/test/resources/TestData/loginData.json",
 *         LoginData.class
 * );
 *
 * System.out.println(data.getUsername());
 * System.out.println(data.getPassword());
 */
public class HelperClass {

    /*
     * Gson object used to convert JSON data into Java objects.
     */
    private static final Gson gson = new Gson();

    /**
     * Reads a JSON file and converts its content into an object
     * of the requested Java class.
     *
     * This method is Generic because it can return ANY Java type
     * that matches the structure of the JSON file.
     *
     * For example:
     *
     * LoginData.class  -> LoginData object
     * User.class       -> User object
     * Product.class    -> Product object
     * Employee.class   -> Employee object
     *
     * @param filePath Path of the JSON file.
     * @param classType The Java class that the JSON should be converted to.
     * @param <T> Generic type representing the returned Java object.
     *
     * @return Java object containing the data from the JSON file.
     *
     * @throws RuntimeException if the file cannot be read or parsed.
     */
    public static <T> T readJson(String filePath, Class<T> classType) {

        /*
         * try-with-resources automatically closes the FileReader
         * after finishing reading the JSON file.
         */
        try (Reader reader = new FileReader(filePath)) {

            /*
             * Gson reads the JSON from the Reader and converts it
             * into an object of the class provided by classType.
             *
             * Example:
             *
             * gson.fromJson(reader, LoginData.class)
             *
             * returns:
             *
             * LoginData object
             */
            return gson.fromJson(reader, classType);

        } catch (IOException e) {

            /*
             * Convert the checked IOException into RuntimeException
             * so the test fails immediately if the test data
             * cannot be loaded.
             */
            throw new RuntimeException(
                    "Failed to read JSON test data from: " + filePath,
                    e
            );
        }
    }
}




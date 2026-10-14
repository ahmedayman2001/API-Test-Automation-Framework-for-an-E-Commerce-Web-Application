package TestData;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides utility methods for generating random test data.
 */
public class RandomDataGenerator {

    /**
     * Generates a random full name using predefined first and last names.
     *
     * @return a randomly generated full name
     */
    public static String generateRandomName() {
        String[] firstNames = {
                "Rahul", "Amit", "Arjun", "Rohan",
                "Vikram", "Priya", "Ananya", "Neha"
        };

        String[] lastNames = {
                "Sharma", "Patel", "Singh", "Kumar",
                "Gupta", "Verma", "Mehta", "Shah"
        };

        int firstIndex = ThreadLocalRandom.current()
                .nextInt(firstNames.length);

        int lastIndex = ThreadLocalRandom.current()
                .nextInt(lastNames.length);

        return firstNames[firstIndex] + " " + lastNames[lastIndex];
    }

    /**
     * Generates a unique email address using a random UUID.
     *
     * @return a randomly generated email address
     */
    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID()
                .toString().replace("-", "") + "@example.com";
    }

    /**
     * Generates a random password containing letters, digits, and special characters.
     *
     * @return a randomly generated 12-character password
     */
    public static String generateRandomPassword() {
        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "0123456789" +
                        "!@#$%&*";

        StringBuilder password = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // Generate a password of 12 characters.
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    /**
     * Generates a random city from a predefined list of Indian cities.
     *
     * @return a randomly selected city
     */
    public static String generateRandomCity() {

        String[] cities = {
                "Mumbai",
                "Delhi",
                "Bangalore",
                "Hyderabad",
                "Chennai",
                "Kolkata",
                "Pune",
                "Ahmedabad",
                "Jaipur",
                "Lucknow"
        };

        int index = ThreadLocalRandom.current()
                .nextInt(cities.length);

        return cities[index];
    }
}
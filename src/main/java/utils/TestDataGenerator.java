package utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * TestDataGenerator provides dynamic and valid test data for registration and workflows.
 */
public final class TestDataGenerator {

    private TestDataGenerator() {
        // Prevent instantiation
    }

    /**
     * Returns a standard test user full name.
     */
    public static String getFullName() {
        return "Test User";
    }

    /**
     * Generates a unique email address using the current timestamp.
     */
    public static String getEmail() {
        return "user" + System.currentTimeMillis() + "@test.com";
    }

    /**
     * Generates a valid 10-digit Indian mobile number starting with 9.
     */
    public static String getMobileNumber() {
        long suffix = ThreadLocalRandom.current().nextLong(100_000_000L, 1_000_000_000L);
        return "9" + suffix;
    }

    /**
     * Returns a valid password satisfying common complexity rules.
     */
    public static String getPassword() {
        return "Test@12345";
    }

    /**
     * Returns confirm password matching the password.
     */
    public static String getConfirmPassword() {
        return getPassword();
    }
}

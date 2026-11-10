package utils;

import java.time.Duration;

/**
 * Constants holds central static values used across the test framework.
 */
public final class Constants {

    private Constants() {
        // Prevent instantiation
    }

    public static final String BASE_URL = "https://www.ekhadiindia.com";
    public static final String REGISTRATION_URL = BASE_URL + "/users/registration";
    public static final String ACCESSORIES_URL = BASE_URL + "/search?category=Accessories-aa1BB";

    public static final Duration EXPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(15);
    public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);

    public static final String SCREENSHOTS_DIR = "screenshots";
}

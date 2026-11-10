package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotUtil handles capturing and saving browser screenshots with timestamps.
 */
public final class ScreenshotUtil {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtil() {
        // Prevent instantiation
    }

    /**
     * Captures a screenshot and saves it into the screenshots/ directory.
     *
     * @param driver   Active WebDriver instance
     * @param fileName Base name for the screenshot file (e.g. "cart")
     * @return Path to the saved screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String fileName) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver instance cannot be null when taking a screenshot.");
        }

        try {
            Path directory = Paths.get(Constants.SCREENSHOTS_DIR);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
            String fullFileName = fileName + "_" + timestamp + ".png";
            Path destinationPath = directory.resolve(fullFileName);

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot captured: " + destinationPath.toAbsolutePath());
            return destinationPath.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + e.getMessage(), e);
        }
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * RegistrationPage represents the user registration screen (/users/registration).
 * Encapsulates form fields, checkboxes, and navigation actions without any assertions.
 */
public class RegistrationPage extends BasePage {

    // Locators
    private final By fullNameInput = By.id("name");
    private final By emailInput = By.id("email");
    private final By mobileInput = By.id("mobile");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("password_confirmation");
    private final By termsCheckbox = By.id("terms");
    private final By termsLabel = By.cssSelector("label[for='terms']");
    private final By backToHomeLink = By.xpath("//a[contains(., 'Back to Home')]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void enterFullName(String fullName) {
        type(fullNameInput, fullName);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterMobile(String mobile) {
        type(mobileInput, mobile);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordInput, confirmPassword);
    }

    /**
     * Ticks the Terms and Privacy Policy checkbox if it is not already selected.
     */
    public void acceptTerms() {
        WebElement checkbox = waitForVisibility(termsCheckbox);
        if (!checkbox.isSelected()) {
            try {
                click(checkbox);
            } catch (Exception e) {
                // If label overlay blocks direct input click
                try {
                    click(termsLabel);
                } catch (Exception fallback) {
                    clickWithJs(checkbox);
                }
            }
        }
    }

    /**
     * Fills all registration inputs and accepts terms.
     * Intentionally does not submit the form to avoid creating live accounts and OTPs.
     */
    public void fillRegistrationForm(String fullName, String email, String mobile, String password) {
        enterFullName(fullName);
        enterEmail(email);
        enterMobile(mobile);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptTerms();
    }

    /**
     * Clicks the 'Back to Home' button to return to the homepage.
     */
    public void clickBackToHome() {
        try {
            click(backToHomeLink);
        } catch (Exception e) {
            click(By.cssSelector("a.btn-warning[href*='ekhadiindia.com']"));
        }
    }
}

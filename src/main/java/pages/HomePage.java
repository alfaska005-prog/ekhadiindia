package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Constants;

import java.util.List;

/**
 * HomePage encapsulates all elements and actions available on the eKhadi India home page.
 * Contains only locators and actions — no assertions.
 */
public class HomePage extends BasePage {

    // Locators
    private final By allLinks = By.tagName("a");
    private final By headerLogo = By.cssSelector("a.navbar-brand img[src*='frontend/images/logo/logo.png']");
    private final By registerLink = By.cssSelector("a.top-bar-item[href*='users/registration']");
    private final By accessoriesCategoryLink = By.cssSelector(".exo-menu a[href*='category=Accessories']");
    private final By generalAccessoriesLinks = By.cssSelector("a[href*='category=Accessories']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates directly to the eKhadi India home page.
     */
    public void open() {
        driver.get(Constants.BASE_URL);
        waitForVisibility(headerLogo);
    }

    /**
     * Counts all <a> tags present on the page.
     *
     * @return Total number of anchor links
     */
    public int getTotalLinksCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allLinks));
        List<WebElement> links = driver.findElements(allLinks);
        return links.size();
    }

    /**
     * Checks if the website header logo is displayed.
     *
     * @return true if visible, false otherwise
     */
    public boolean isLogoDisplayed() {
        return isDisplayed(headerLogo);
    }

    /**
     * Clicks the Registration link in the top bar.
     */
    public void clickRegisterLink() {
        try {
            click(registerLink);
        } catch (Exception e) {
            clickFirstDisplayed(By.cssSelector("a[href*='users/registration']"));
        }
    }

    /**
     * Clicks the Accessories category link from the desktop navigation menu.
     */
    public void clickAccessoriesCategory() {
        try {
            click(accessoriesCategoryLink);
        } catch (Exception e) {
            clickFirstDisplayed(generalAccessoriesLinks);
        }
    }
}

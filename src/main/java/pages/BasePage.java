package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.util.List;

/**
 * BasePage encapsulates reusable browser actions and explicit wait strategies.
 * All Page Object classes extend BasePage. Page classes contain only locators
 * and actions—assertions are strictly preserved in test classes.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIMEOUT);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        try {
            waitForClickability(locator).click();
        } catch (Exception e) {
            // Scroll into view and retry, or use JavascriptExecutor if standard click is intercepted
            WebElement element = driver.findElement(locator);
            scrollToElement(element);
            try {
                element.click();
            } catch (Exception fallbackEx) {
                clickWithJs(element);
            }
        }
    }

    protected void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            scrollToElement(element);
            try {
                element.click();
            } catch (Exception fallbackEx) {
                clickWithJs(element);
            }
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        }
    }

    protected void sendKeys(By locator, String text) {
        type(locator, text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds all elements matching a locator and returns the first one that is currently displayed.
     * Useful when menus are duplicated for desktop, mobile, or sidebar drawers.
     */
    protected WebElement findFirstDisplayedElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return elements.isEmpty() ? null : elements.get(0);
    }

    protected void clickFirstDisplayed(By locator) {
        WebElement displayedElement = findFirstDisplayedElement(locator);
        if (displayedElement != null) {
            click(displayedElement);
        } else {
            click(locator);
        }
    }

    protected void clickWithJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

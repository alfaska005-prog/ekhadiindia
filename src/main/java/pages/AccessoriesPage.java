package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * AccessoriesPage represents the Accessories product catalog page (/search?category=Accessories-aa1BB).
 * Encapsulates product listing locators and actions without any assertions.
 */
public class AccessoriesPage extends BasePage {

    // Locators
    private final By productCards = By.cssSelector(".product-box-2");
    private final By productTitleLinks = By.cssSelector("h2.product-title a");

    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits until product elements are visible on the listing page.
     */
    public void waitForProductsToLoad() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitleLinks));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitleLinks));
    }

    /**
     * Retrieves all product names currently displayed on the page.
     *
     * @return List of product title strings
     */
    public List<String> getAllProductNames() {
        waitForProductsToLoad();
        List<WebElement> productElements = driver.findElements(productTitleLinks);
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productElements) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                productNames.add(text);
            }
        }
        return productNames;
    }

    /**
     * Clicks on the first product title link to navigate to its Product Detail Page.
     */
    public void clickFirstProduct() {
        waitForProductsToLoad();
        List<WebElement> products = driver.findElements(productTitleLinks);
        if (products.isEmpty()) {
            throw new RuntimeException("No products found to click on AccessoriesPage.");
        }
        click(products.get(0));
    }
}

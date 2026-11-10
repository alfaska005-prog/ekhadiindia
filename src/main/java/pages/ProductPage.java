package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ProductPage encapsulates actions on the Product Detail Page (PDP).
 * Includes adding product to cart and querying updated header cart quantity.
 */
public class ProductPage extends BasePage {

    // Locators
    private final By addToCartButton = By.cssSelector("button.add-to-cart");
    private final By cartNumber = By.cssSelector("#cart_items .nav-box-number");
    private final By cartSideNavNumber = By.id("cart_items_sidenav");
    private final By addToCartModal = By.id("addToCart");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the 'Add to cart' button.
     */
    public void clickAddToCart() {
        WebElement button = waitForClickability(addToCartButton);
        scrollToElement(button);
        click(button);
    }

    /**
     * Retrieves the current cart count displayed in the header.
     *
     * @return Integer value of items in cart
     */
    public int getCartCount() {
        try {
            WebElement countElement = driver.findElement(cartNumber);
            String text = countElement.getText().trim();
            if (text.isEmpty()) {
                WebElement sideCount = driver.findElement(cartSideNavNumber);
                text = sideCount.getText().trim();
            }
            String numericOnly = text.replaceAll("[^0-9]", "");
            return numericOnly.isEmpty() ? 0 : Integer.parseInt(numericOnly);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Explicitly waits until the cart count updates to be greater than the baseline count.
     *
     * @param initialCount Baseline cart quantity before adding product
     * @return Updated cart count
     */
    public int waitForCartCountToUpdate(int initialCount) {
        wait.until(d -> {
            try {
                int count = getCartCount();
                return count > initialCount;
            } catch (Exception e) {
                return false;
            }
        });
        return getCartCount();
    }
}

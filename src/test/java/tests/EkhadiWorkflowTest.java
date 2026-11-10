package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;
import utils.ScreenshotUtil;
import utils.TestDataGenerator;

import java.util.List;

/**
 * EkhadiWorkflowTest implements the required TestNG test scenarios for eKhadi India.
 * All assertions are strictly placed here inside the test class.
 */
public class EkhadiWorkflowTest extends BaseTest {

    /**
     * Test 1: Count all <a> tags on the homepage, print the total, and assert count > 0.
     */
    @Test(priority = 1, description = "Count and print all anchor links on the homepage")
    public void shouldPrintTotalNumberOfLinksOnHomepage() {
        int totalLinks = homePage.getTotalLinksCount();
        System.out.println("==================================================");
        System.out.println("Total number of links on eKhadi India homepage: " + totalLinks);
        System.out.println("==================================================");

        Assert.assertTrue(totalLinks > 0, "Total number of links on homepage should be greater than 0.");
    }

    /**
     * Test 2: Verify that the website header logo is displayed.
     */
    @Test(priority = 2, description = "Verify that the header logo is displayed")
    public void shouldDisplayWebsiteLogo() {
        boolean isLogoVisible = homePage.isLogoDisplayed();
        System.out.println("Website logo displayed: " + isLogoVisible);

        Assert.assertTrue(isLogoVisible, "Header logo should be visible on the homepage.");
    }

    /**
     * Test 3: Navigate to Registration page, fill the form, accept terms,
     * click 'Back to Home' (without registering account), and assert returning to home.
     */
    @Test(priority = 3, description = "Fill registration form and navigate back to home")
    public void shouldFillRegistrationFormAndNavigateBackToHome() {
        homePage.clickRegisterLink();

        String fullName = TestDataGenerator.getFullName();
        String email = TestDataGenerator.getEmail();
        String mobile = TestDataGenerator.getMobileNumber();
        String password = TestDataGenerator.getPassword();

        registrationPage.fillRegistrationForm(fullName, email, mobile, password);
        registrationPage.clickBackToHome();

        String currentUrl = homePage.getPageUrl();
        System.out.println("Returned to URL: " + currentUrl);

        Assert.assertTrue(currentUrl.startsWith(Constants.BASE_URL),
                "URL should return to homepage after clicking Back to Home. Current: " + currentUrl);
        Assert.assertTrue(homePage.isLogoDisplayed(),
                "Website logo should be displayed upon returning to homepage.");
    }

    /**
     * Test 4: Navigate to Accessories category, wait for products to load,
     * print product names, and assert the list is not empty.
     */
    @Test(priority = 4, description = "Verify Accessories category products listing")
    public void shouldDisplayAccessoriesProducts() {
        homePage.clickAccessoriesCategory();
        accessoriesPage.waitForProductsToLoad();

        List<String> productNames = accessoriesPage.getAllProductNames();
        System.out.println("==================================================");
        System.out.println("Accessories Products Found (" + productNames.size() + "):");
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println((i + 1) + ". " + productNames.get(i));
        }
        System.out.println("==================================================");

        Assert.assertFalse(productNames.isEmpty(), "Accessories product list should not be empty.");
        Assert.assertTrue(productNames.size() >= 1, "Expected at least 1 product in Accessories listing.");
    }

    /**
     * Test 5: Open the first Accessories product, add to cart, verify cart count updates,
     * and capture a timestamped screenshot saved in screenshots/.
     */
    @Test(priority = 5, description = "Add first product to cart and capture timestamped screenshot")
    public void shouldAddFirstProductToCartAndCaptureScreenshot() {
        homePage.clickAccessoriesCategory();
        accessoriesPage.waitForProductsToLoad();

        int initialCartCount = productPage.getCartCount();
        System.out.println("Initial cart count: " + initialCartCount);

        accessoriesPage.clickFirstProduct();
        productPage.clickAddToCart();

        int updatedCartCount = productPage.waitForCartCountToUpdate(initialCartCount);
        System.out.println("Updated cart count: " + updatedCartCount);

        Assert.assertTrue(updatedCartCount > initialCartCount,
                "Cart count should increment after adding product to cart.");
        Assert.assertTrue(updatedCartCount > 0,
                "Cart count should be greater than 0.");

        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "cart");
        Assert.assertNotNull(screenshotPath, "Screenshot path should not be null.");
        System.out.println("Test 5 completed successfully. Screenshot saved to: " + screenshotPath);
    }
}

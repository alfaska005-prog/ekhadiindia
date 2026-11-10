package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AccessoriesPage;
import pages.HomePage;
import pages.ProductPage;
import pages.RegistrationPage;
import utils.DriverFactory;

/**
 * BaseTest provides WebDriver lifecycle management (@BeforeMethod, @AfterMethod)
 * and initializes Page Object instances for all test classes.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected RegistrationPage registrationPage;
    protected AccessoriesPage accessoriesPage;
    protected ProductPage productPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.initDriver();
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        accessoriesPage = new AccessoriesPage(driver);
        productPage = new ProductPage(driver);

        // Open base homepage before every test
        homePage.open();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}

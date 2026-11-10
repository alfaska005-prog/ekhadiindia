# eKhadi India Selenium Automation Framework

An automated end-to-end testing framework for [eKhadi India](https://www.ekhadiindia.com) built using **Selenium WebDriver (v4.x)**, **Java 21**, **TestNG**, and **Maven**, following the **Page Object Model (POM)** design pattern.

---

## 🚀 Technology Stack

- **Language:** Java 21
- **Automation Framework:** Selenium WebDriver 4.26.0 (Selenium Manager handles Chrome driver natively)
- **Test Runner:** TestNG 7.10.2
- **Build Tool:** Apache Maven
- **Design Pattern:** Page Object Model (POM)
- **Browser:** Google Chrome (with Headless support)

---

## 📂 Project Structure

```text
ekhadi-selenium/
├── src/main/java/
│   ├── pages/
│   │   ├── BasePage.java              # Reusable explicit waits, click/type helpers
│   │   ├── HomePage.java              # Home page actions, links count, logo, navigation
│   │   ├── RegistrationPage.java      # Registration inputs, terms checkbox, back-to-home
│   │   ├── AccessoriesPage.java       # Product listing waits, product titles, selection
│   │   └── ProductPage.java           # Add to cart action, dynamic cart count reader
│   └── utils/
│       ├── Constants.java             # Static URLs, timeouts, directory paths
│       ├── DriverFactory.java         # Thread-safe WebDriver initialization & teardown
│       ├── TestDataGenerator.java     # Dynamic test user data (email, mobile, credentials)
│       └── ScreenshotUtil.java        # Timestamped screenshot capture utility
│
├── src/test/java/
│   └── tests/
│       ├── BaseTest.java              # Test lifecycle (@BeforeMethod, @AfterMethod)
│       └── EkhadiWorkflowTest.java    # 5 TestNG test scenarios with strict POM assertions
│
├── screenshots/                       # Output folder for test evidence screenshots
├── pom.xml                            # Maven project configuration & dependencies
├── testng.xml                         # TestNG test suite descriptor
├── .gitignore                         # Git exclusion rules (preserving screenshots)
└── README.md                          # Project documentation and execution instructions
```

---

## 🧪 Test Scenarios Covered

1. **`shouldPrintTotalNumberOfLinksOnHomepage()`**
   - Counts all `<a>` tags on the homepage using `By.tagName("a")`.
   - Prints the count to the console and asserts that `totalLinks > 0`.

2. **`shouldDisplayWebsiteLogo()`**
   - Verifies the header logo is visible on the homepage using explicit wait.
   - Asserts `isDisplayed()`.

3. **`shouldFillRegistrationFormAndNavigateBackToHome()`**
   - Navigates to the Registration page (`/users/registration`).
   - Generates and enters Full Name, unique email, 10-digit Indian mobile number, password, and confirm password.
   - Ticks the Terms and Privacy Policy checkbox.
   - Clicks "Back to Home" without clicking the final submit button (preventing unwanted live accounts and OTPs).
   - Asserts the user is returned to the homepage and that the logo is visible.

4. **`shouldDisplayAccessoriesProducts()`**
   - Navigates to the Accessories category page.
   - Waits for product cards to load.
   - Prints each product title to the console and asserts the catalog is not empty.

5. **`shouldAddFirstProductToCartAndCaptureScreenshot()`**
   - Navigates to the Accessories category and opens the first product detail page.
   - Clicks "Add to cart".
   - Explicitly waits for the header cart count to update.
   - Asserts the cart count is greater than 0.
   - Captures a timestamped PNG screenshot and saves it to `screenshots/cart_YYYYMMDD_HHMMSS.png`.

---

## ⚙️ Prerequisites

- **Java JDK 21** or higher installed and added to `PATH`
- **Apache Maven 3.9+** installed and added to `PATH`
- **Google Chrome** browser installed

---

## 🏃 How to Run Tests

### Run All Tests via Maven (Headless mode)
```bash
mvn clean test -Dheadless=true
```

### Run All Tests via Maven (Headed Browser mode)
```bash
mvn clean test -Dheadless=false
```

### Run Tests via TestNG XML directly
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run a Specific Test Method
```bash
mvn test -Dtest=EkhadiWorkflowTest#shouldDisplayWebsiteLogo
```

---

## 📸 Screenshots

Screenshots taken during test execution are stored inside the `screenshots/` directory with a timestamp:
```text
screenshots/cart_20261110_120400.png
```
As per requirements, the `screenshots/` folder is tracked and committed to git.

---

## 📦 Pushing to GitHub

To push this project to your GitHub repository, run the following commands in the project root:

```bash
# 1. Initialize git repository (if not already initialized)
git init

# 2. Stage all project files including screenshots
git add .

# 3. Create initial commit
git commit -m "feat: complete eKhadi India Selenium WebDriver automation framework"

# 4. Set the default branch to main
git branch -M main

# 5. Add your remote repository URL
git remote add origin https://github.com/alfaska005-prog/ekhadiindia.git

# 6. Push to GitHub
git push -u origin main
```

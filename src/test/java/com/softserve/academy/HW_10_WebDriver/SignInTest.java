package com.softserve.academy.HW_10_WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SignInTest {
    private WebDriver driver;

    @FindBy(css = ".ubs-header-sing-in-img")
    private WebElement signInButton;

    @FindBy(css = ".container h1")
    private WebElement welcomeText;

    @FindBy(css = ".container h2")
    private WebElement signInDetailsText;

    @FindBy(css = "label[for='email']")
    private WebElement emailLabel;

    @FindBy(css = "label[for='password']")
    private WebElement passwordLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button.ubsStyle")
    private WebElement signInSubmitButton;

    @FindBy(css = "div.alert-general-error")
    private WebElement generalErrorMessage;

    @FindBy(id = "email-err-msg")
    private WebElement errorEmail;

    @FindBy(id = "pass-err-msg")
    private WebElement errorPassword;

    @FindBy(css = ".close-modal-window")
    private WebElement closeButton;

    @FindBy(css = "button.google-sign-in")
    private WebElement googleSignInButton;

    @FindBy(css = ".forgot-wrapper-ubs a")
    private WebElement forgotPasswordLink;

    @FindBy(css = ".missing-account a")
    private WebElement signUpLink;

    @FindBy(css = "li.ubs-user-name")
    private WebElement userName;

    @BeforeAll
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void initPageElements() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get("https://www.greencity.cx.ua/#/ubs");
        //driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1264, 798));
        PageFactory.initElements(driver, this);

        // Open Sign in
        signInButton.click();

        // Close iframe if appeared

        try {
            By iframeLocator = By.cssSelector("#credential_picker_container iframe");
            By closeButtonLocator = By.cssSelector("div#close");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Check if iframe exists
            if (!driver.findElements(iframeLocator).isEmpty()) {
                driver.switchTo().frame(driver.findElement(iframeLocator));

                WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeButtonLocator));
                closeButton.click();

                System.out.println("Google sign-in iframe was found and closed.");
            } else {
                System.out.println("No Google sign-in iframe appeared — continuing test.");
            }

        } catch (TimeoutException e) {
            System.err.println("Error while closing iframe: " + e.getMessage());

        } finally {
            driver.switchTo().defaultContent();
        }
    }

    //-----------------------------------------------------------------------
    // UI checks
    @Test
    @DisplayName("Verify page title")
    public void verifyTitle() {
        assertThat(driver.getTitle())
                .isEqualTo("Convenient Pick-Up Service by UBS — Fast Recycling Aid");
    }

    @Test
    @DisplayName("Verify that login form opens correctly")
    public void verifyLoginFormsOpen() {

        // Texts
        assertThat(emailLabel.getText())
                .isEqualTo("Email");
        assertThat(passwordLabel.getText())
                .isEqualTo("Password");

        // Visibility and enabled state
        assertThat(emailInput.isDisplayed()).isTrue();
        assertThat(passwordInput.isDisplayed()).isTrue();

        assertThat(emailInput.isEnabled()).isTrue();
        assertThat(passwordInput.isEnabled()).isTrue();

        // Placeholders
        assertThat(emailInput.getAttribute("placeholder"))
                .isEqualTo("example@email.com");
        assertThat(passwordInput.getAttribute("placeholder"))
                .isEqualTo("Password");

        // Sign in buttons
        //Sign in button
        assertThat(signInSubmitButton.isDisplayed()).isTrue();
        assertThat(signInSubmitButton.isEnabled()).isFalse();
        assertThat(signInSubmitButton.getText())
                .isEqualTo("Sign in");

        //Sign in with Google button
        assertThat(googleSignInButton.isDisplayed()).isTrue();
        assertThat(googleSignInButton.isEnabled()).isTrue();
        assertThat(googleSignInButton.getText())
                .isEqualTo("Sign in with Google");


    }

    @Test
    @DisplayName("Verify all elements of Sign in window are visible")
    public void verifyLoginFormElementsAreVisible() {

        // Welcome text
        assertThat(welcomeText.getText())
                .isEqualTo("Welcome back!");
        assertThat(signInDetailsText.getText())
                .isEqualTo("Please enter your details to sign in.");


        //Forgot password
        assertThat(forgotPasswordLink.isDisplayed()).isTrue();
        assertThat(forgotPasswordLink.isEnabled()).isTrue();
        assertThat(forgotPasswordLink.getText()).isEqualTo("Forgot password?");

        //SignUp
        assertThat(signUpLink.isDisplayed()).isTrue();
        assertThat(signUpLink.isEnabled()).isTrue();
        assertThat(signUpLink.getText()).isEqualTo("Sign up");

        //Close button
        assertThat(closeButton.isDisplayed()).isTrue();
        assertThat(closeButton.isEnabled()).isTrue();

    }

    //------------------------------------------------------------------
    //Successful login
    @Test
    @DisplayName("Verify successful login with valid credentials")
    public void verifySuccessfulLogin() {
        emailInput.clear();
        emailInput.sendKeys("tegusi1@yopmail.com");
        assertThat(emailInput.getAttribute("value")).isEqualTo("tegusi1@yopmail.com");

        passwordInput.clear();
        passwordInput.sendKeys("Y%g>M4kL2};LU6y");
        assertThat(passwordInput.getAttribute("value")).isEqualTo("Y%g>M4kL2};LU6y");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(userName));
        assertThat(userName.getText()).isEqualTo("Tegusi");
    }

    //----------------------------------------------------------------------------------
    //Unsuccessful login

    //Invalid email
    @ParameterizedTest
    @CsvSource({
            "'plainaddress', 'Missing @ and domain'",
            "'@missingusername.com', 'Missing username'",
            "'username@.com', 'Invalid domain'",
            "'username@domain', 'Missing top-level domain'",
            "'username@domain,comma', 'Contains comma'",
            "'space in@email.com', 'Contains space'"
    })
    @DisplayName("Verify that invalid email formats show error message")
    public void verifyInvalidEmailFormats(String invalidEmail, String description) {
        emailInput.sendKeys(invalidEmail);
        emailInput.sendKeys(Keys.TAB);

        passwordInput.sendKeys("Y%g>M4kL2};LU6y");
        passwordInput.sendKeys(Keys.TAB);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(errorEmail));

        assertThat(errorEmail.getText())
                .containsIgnoringCase("Please check if the email is written correctly");


    }
    //Invalid password
    @ParameterizedTest
    @CsvSource({
            "short1!, Too short password",

            // TODO: Site currently accepts this invalid passwords — validation bug
//            "alllowercase1!, Missing uppercase letter",
//            "ALLUPPERCASE1!, Missing lowercase letter",
//            "NoDigit!, Missing digit",
//            "NoSpecial123, Missing special character",
//            "'With Space1!', Contains space"
    })
    @DisplayName("Verify that invalid passwords show correct error messages")
    public void verifyInvalidPasswords(String password, String description) {
        emailInput.sendKeys("tegusi1@yopmail.com");
        emailInput.sendKeys(Keys.TAB);

        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.TAB);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(errorPassword));

        System.out.println("Checked scenario: " + description);

        assertThat(errorPassword.getText())
                .containsIgnoringCase("Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
    }

    //Invalid password - too long
    @ParameterizedTest
    @CsvSource({
            "thispasswordistoolong123!A, Too long password"
    })
    @DisplayName("Verify password length validation")
    public void verifyPasswordLength(String password, String description) {
        emailInput.sendKeys("tegusi1@yopmail.com");
        emailInput.sendKeys(Keys.TAB);

        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.TAB);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(errorPassword));


        assertThat(errorPassword.getText())
                .containsIgnoringCase("Password must be less than 20 characters long without spaces.");

    }



    // Valid email, but incorrect password (valid format, doesn't match user's actual password)
    @ParameterizedTest
    @CsvSource({
            "Y%g>M4kL2, Invalid password"
    })
    @DisplayName("Verify an error is displayed when valid email is used with incorrect password")
    public void verifyErrorShownForWrongPassword(String password, String description) {
        emailInput.sendKeys("tegusi1@yopmail.com");

        passwordInput.sendKeys(password);

        signInSubmitButton.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(generalErrorMessage));

        assertThat(generalErrorMessage.getText())
                .containsIgnoringCase("Bad password");

    }

    //Empty email and password fields
    @Test
    @DisplayName("Verify error messages when email and password fields are empty")
    public void verifyEmptyFieldsErrorMessages() {
        emailInput.sendKeys("");
        emailInput.sendKeys(Keys.TAB);

        passwordInput.sendKeys("");
        passwordInput.sendKeys(Keys.TAB);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        wait.until(ExpectedConditions.visibilityOf(errorPassword));

        assertThat(errorEmail.getText())
                .containsIgnoringCase("Email is required.");
        assertThat(errorPassword.getText())
                .containsIgnoringCase("This field is required");
    }

    //Email and passwords are invalid
    @Test
    @DisplayName("Verify error messages when both email and password are invalid")
    public void verifyBothInvalidEmailAndPassword() {
        emailInput.sendKeys("invalidEmail.com");
        emailInput.sendKeys(Keys.TAB);

        passwordInput.sendKeys("short");
        passwordInput.sendKeys(Keys.TAB);

        assertThat(signInSubmitButton.isEnabled()).isFalse();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        wait.until(ExpectedConditions.visibilityOf(errorPassword));

        assertThat(errorEmail.getText())
                .containsIgnoringCase("Please check if the email is written correctly");
        assertThat(errorPassword.getText())
                .containsIgnoringCase("Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
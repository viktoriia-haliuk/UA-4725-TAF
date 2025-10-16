package com.softserve.academy.HW_11_Parametrized.tests;

import com.softserve.academy.HW_11_Parametrized.pages.SignInPage;
import com.softserve.academy.HW_11_Parametrized.pages.UbsHomePage;

import com.softserve.academy.HW_11_Parametrized.utils.EnvUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.params.ParameterizedTest;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SignInTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private SignInPage signInPage;

    //========================================================================================
    //                               SETUP & TEARDOWN
    //========================================================================================

    @BeforeAll
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void initPageElements() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
        driver.get("https://www.greencity.cx.ua/#/ubs");


        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // ---- Open Sign In and Close iframe if appeared----
        signInPage = new SignInPage(driver, wait);
        signInPage.openSignInModal();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    //========================================================================================
    //                               TESTS
    //========================================================================================

    //-------------------------------UI checks-------------------------------------------------

    // Title
    @Test
    @DisplayName("Verify page title")
    public void verifyTitle() {
        assertThat(signInPage.getTitle())
                .isEqualTo("Convenient Pick-Up Service by UBS — Fast Recycling Aid");
    }

    // Welcome text
    @Test
    @DisplayName("Verify welcome text")
    public void verifyWelcomeText() {
        assertThat(signInPage.getWelcomeText())
                .isEqualTo("Welcome back!");

    }

    // Sign In details text
    @Test
    @DisplayName("Verify sign In details text")
    public void verifySignInDetailsText() {
        assertThat(signInPage.getSignInDetailsText())
                .isEqualTo("Please enter your details to sign in.");

    }

    //---------------------------Sigh In form UI checks---------------------------------------------
    // Email
    @Test
    @DisplayName("Verify Email field UI elements")
    public void verifyEmailFieldUI() {
        assertThat(signInPage.isEmailInputVisible()).isTrue();
        assertThat(signInPage.isEmailInputEnabled()).isTrue();
        assertThat(signInPage.getEmailLabelText()).isEqualTo("Email");
        assertThat(signInPage.getEmailPlaceholder()).isEqualTo("example@email.com");
    }

    // Password
    @Test
    @DisplayName("Verify Password field UI elements")
    public void verifyPasswordFieldUI() {
        assertThat(signInPage.isPasswordInputVisible()).isTrue();
        assertThat(signInPage.isPasswordInputEnabled()).isTrue();
        assertThat(signInPage.getPasswordLabelText()).isEqualTo("Password");
        assertThat(signInPage.getPasswordPlaceholder()).isEqualTo("Password");
    }

    // Sign In Submit Button
    @Test
    @DisplayName("Verify Sign In Submit button UI elements")
    public void verifySignInSubmitButtonUI() {
        assertThat(signInPage.isSignInSubmitVisible()).isTrue();
        assertThat(signInPage.isSignInSubmitEnabled()).isFalse();
        assertThat(signInPage.getSignInSubmitText()).isEqualTo("Sign in");

    }


    // Sign In With Google Button
    @Test
    @DisplayName("Verify Sign In with Google button UI elements")
    public void verifySignInWithGoogleButtonUI() {
        assertThat(signInPage.isGoogleSignInVisible()).isTrue();
        assertThat(signInPage.isGoogleSignInEnabled()).isTrue();
        assertThat(signInPage.getGoogleSignInText()).isEqualTo("Sign in with Google");

    }

    // Forgot Password Link
    @Test
    @DisplayName("Verify forgot password and sign up links are visible and enabled")
    public void verifyForgotPasswordLinkUI() {
        assertThat(signInPage.isForgotPasswordVisible()).isTrue();
        assertThat(signInPage.isForgotPasswordEnabled()).isTrue();
        assertThat(signInPage.getForgotPasswordText())
                .isEqualTo("Forgot password?");
    }

    // Sign Up Link
    @Test
    @DisplayName("Verify forgot password and sign up links are visible and enabled")
    public void verifySignUpLinkUI() {
        assertThat(signInPage.isSignUpLinkVisible()).isTrue();
        assertThat(signInPage.isSignUpLinkEnabled()).isTrue();
        assertThat(signInPage.getSignUpLinkText())
                .isEqualTo("Sign up");
    }

    // Fields Input Checks And Button Enabled
    @Test
    @DisplayName("Verify fields accept input and Sign In button becomes enabled")
    public void verifyFieldsInputAndButtonEnabled() {
        signInPage.enterEmail("test@gmail.com");
        signInPage.enterPassword("Password123");


        assertThat(signInPage.getEmailInput().getAttribute("value"))
                .isEqualTo("test@gmail.com");
        assertThat(signInPage.getPasswordInput().getAttribute("value"))
                .isEqualTo("Password123");


        assertThat(signInPage.isSignInSubmitEnabled())
                .isTrue();
    }

    // Close Button And Close Modal
    @Test
    @DisplayName("Verify Close button and close Modal ")
    public void verifyCloseButton() {
        assertThat(signInPage.isCloseButtonVisible()).isTrue();
        assertThat(signInPage.isCloseButtonEnabled()).isTrue();

        assertThat(signInPage.isSignInModalVisible()).isTrue();

        signInPage.closeModal();

        assertThat(signInPage.isSignInModalVisible()).isFalse();

    }


    //---------------------------Sigh In functionality ---------------------------------------------
    // Successful login - positive checks
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/valid_logins.csv", numLinesToSkip = 1)
    @DisplayName("Verify successful login with multiple valid users")
    public void verifySuccessfulLogin(String email, String passwordKey, String expectedUserName) {
        String password = EnvUtils.getPassword(passwordKey);

        UbsHomePage ubsHomePage = signInPage.signInWith(email, password);

        assertThat(ubsHomePage.getLoggedInUserName()).isEqualTo(expectedUserName);

    }

    // Unsuccessful login - negative checks
    //Invalid email
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/invalid_emails.csv", numLinesToSkip = 1)
    @DisplayName("Verify invalid email validation messages")
    public void verifyInvalidEmails(String email, String expectedMessage) {
        String password = EnvUtils.getPassword("VALID_PASSWORD_1");

        signInPage.enterPassword(password);
        signInPage.enterEmail(email);
        signInPage.getEmailInput().sendKeys(Keys.TAB);

        assertThat(signInPage.getEmailErrorText())
                .isEqualTo(expectedMessage);
    }


    //Invalid password
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/invalid_passwords.csv", numLinesToSkip = 1)
    @DisplayName("Verify invalid password validation messages")
    public void verifyInvalidPasswords(String email, String passwordKey, String expectedMessage) {
        String password = EnvUtils.getPassword(passwordKey);

        signInPage.enterCredentials(email, password);
        signInPage.getPasswordInput().sendKeys(Keys.TAB);

        assertThat(signInPage.getPasswordErrorText())
                .isEqualTo(expectedMessage);
    }


    // Valid email, but incorrect password (valid format, doesn't match user's actual password)
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/wrong_password_case.csv", numLinesToSkip = 1)
    @DisplayName("Verify an error is displayed when valid email is used with incorrect password")
    public void verifyErrorShownForWrongPassword(String email, String passwordKey, String expectedMessage) {
        String password = EnvUtils.getPassword(passwordKey);

        signInPage.enterCredentials(email, password);
        signInPage.clickSignInSubmit();

        assertThat(signInPage.getGeneralErrorText())
                .isEqualTo(expectedMessage);

    }

   // Invalid email and password
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/invalid_logins.csv", numLinesToSkip = 1)
    @DisplayName("Verify error messages when both email and password are invalid")
    public void verifyBothInvalidEmailAndPassword(String email, String passwordKey, String expectedEmailMessage, String expectedPasswordMessage) {
        String password = EnvUtils.getPassword(passwordKey);

        signInPage.enterCredentials(email, password);

        signInPage.getEmailInput().sendKeys(Keys.TAB);

        assertThat(signInPage.getEmailErrorText())
                .isEqualTo(expectedEmailMessage);
        assertThat(signInPage.getPasswordErrorText())
                .isEqualTo(expectedPasswordMessage);

    }

    // Empty email and password fields
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/empty_credential_fields.csv", numLinesToSkip = 1)
    @DisplayName("Verify error messages are displayed when fields are empty")
    public void verifyEmptyFieldsErrorMessages(String email, String password, String expectedEmailMessage, String expectedPasswordMessage) {
        signInPage.enterCredentials(email, password);

        signInPage.getPasswordInput().sendKeys(Keys.TAB);

        assertThat(signInPage.getEmailErrorText())
                .isEqualTo(expectedEmailMessage);
        assertThat(signInPage.getPasswordErrorText())
                .isEqualTo(expectedPasswordMessage);
    }



}
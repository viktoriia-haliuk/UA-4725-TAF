package com.softserve.academy.HW_14_Loggs.functional;

import com.softserve.academy.HW_14_Loggs.pages.SignInPage;
import org.assertj.core.api.SoftAssertions;

import static com.softserve.academy.HW_14_Loggs.pages.LoginPageExpectedTexts.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInAssertions {
    private final SignInPage signInPage;
    private final SoftAssertions softly = new SoftAssertions();

    public SignInAssertions(SignInPage signInPage) {
        this.signInPage = signInPage;
    }

    // Title
    public void assertPageTitle(String expectedTitle) {
        assertThat(signInPage.getTitle()).isEqualTo(expectedTitle);
    }

    // Welcome, Sign In text
    public void assertWelcomeSignInDetailsText(String expectedWelcome, String expectedSignInDetails) {
        assertThat(signInPage.getWelcomeText()).isEqualTo(expectedWelcome);
        assertThat(signInPage.getSignInDetailsText()).isEqualTo(expectedSignInDetails);
    }

    // Email and Password fields
    public void assertEmailField() {
        softly.assertThat(signInPage.isEmailInputVisible()).isTrue();
        softly.assertThat(signInPage.isEmailInputEnabled()).isTrue();
        softly.assertThat(signInPage.getEmailLabelText()).isEqualTo(EXPECTED_EMAIL_LABEL_TEXT);
        softly.assertThat(signInPage.getEmailPlaceholder()).isEqualTo(EXPECTED_EMAIL_PLACEHOLDER_TEXT);
        softly.assertAll();
    }

    public void assertPasswordField() {
        softly.assertThat(signInPage.isPasswordInputVisible()).isTrue();
        softly.assertThat(signInPage.isPasswordInputEnabled()).isTrue();
        softly.assertThat(signInPage.getPasswordLabelText()).isEqualTo(EXPECTED_PASSWORD_LABEL_TEXT);
        softly.assertThat(signInPage.getPasswordPlaceholder()).isEqualTo(EXPECTED_PASSWORD_PLACEHOLDER_TEXT);
        softly.assertAll();
    }

    // Sign In Submit Button
    public void assertSignInSubmitButtonUI() {
        softly.assertThat(signInPage.isSignInSubmitVisible()).isTrue();
        softly.assertThat(signInPage.isSignInSubmitEnabled()).isFalse();
        softly.assertThat(signInPage.getSignInSubmitText()).isEqualTo(EXPECTED_SIGN_IN_SUBMIT_BUTTON_TEXT);
        softly.assertAll();

    }

    // Sign In With Google Button
    public void assertSignInWithGoogleButtonUI() {
        softly.assertThat(signInPage.isGoogleSignInVisible()).isTrue();
        softly.assertThat(signInPage.isGoogleSignInEnabled()).isTrue();
        softly.assertThat(signInPage.getGoogleSignInText()).isEqualTo(EXPECTED_SIGN_IN_WITH_GOOGLE_BUTTON_TEXT);
        softly.assertAll();

    }

    // Forgot Password Link
    public void assertForgotPasswordLinkUI() {
        softly.assertThat(signInPage.isForgotPasswordVisible()).isTrue();
        softly.assertThat(signInPage.isForgotPasswordEnabled()).isTrue();
        softly.assertThat(signInPage.getForgotPasswordText()).isEqualTo(EXPECTED_FORGOT_PASSWORD_LINK_TEXT);
        softly.assertAll();
    }

    public void assertSignUpLinkUI() {
        softly.assertThat(signInPage.isSignUpLinkVisible()).isTrue();
        softly.assertThat(signInPage.isSignUpLinkEnabled()).isTrue();
        softly.assertThat(signInPage.getSignUpLinkText()).isEqualTo(EXPECTED_SIGN_UP_LINK_TEXT);
        softly.assertAll();
    }

    //  Fields Input Checks And Button Enabled
    public void assertFieldsInputAndButtonEnabled(String email, String password) {
        softly.assertThat(signInPage.getEmailInput().getAttribute("value")).isEqualTo(email);
        softly.assertThat(signInPage.getPasswordInput().getAttribute("value")).isEqualTo(password);
        softly.assertThat(signInPage.isSignInSubmitEnabled()).isTrue();
        softly.assertAll();
    }

    // Close Button And Close Modal
    public void assertModalIsOpen() {
        softly.assertThat(signInPage.isCloseButtonVisible()).isTrue();
        softly.assertThat(signInPage.isCloseButtonEnabled()).isTrue();

        softly.assertThat(signInPage.isSignInModalVisible()).isTrue();
        softly.assertAll();
    }

    public void assertModalIsClosed() {
        softly.assertThat(signInPage.isSignInModalVisible()).isFalse();
    }


    // User name
    public void assertUserName(String actualUserName, String expectedUserName) {
        softly.assertThat(actualUserName).isEqualTo(expectedUserName);
        softly.assertAll();
    }

    // Email error message
    public void assertEmailErrorText(String expectedMessage) {
        softly.assertThat(signInPage.getEmailErrorText())
                .isEqualTo(expectedMessage);
    }


    // Password error message
    public void assertPasswordErrorText(String expectedMessage) {
        softly.assertThat(signInPage.getPasswordErrorText())
                .isEqualTo(expectedMessage);
    }

    // General error
    public void assertGeneralErrorText(String expectedMessage) {
        softly.assertThat(signInPage.getGeneralErrorText())
                .isEqualTo(expectedMessage);
    }

    // Invalid email and password
    public void assertErrorEmailAndPasswordMessages(String expectedEmailMessage, String expectedPasswordMessage) {
        softly.assertThat(signInPage.getEmailErrorText()).isEqualTo(expectedEmailMessage);
        softly.assertThat(signInPage.getPasswordErrorText()).isEqualTo(expectedPasswordMessage);
        softly.assertAll();

    }

    // Empty email and password
    public void assertEmptyEmailAndPasswordMessages(String expectedEmailMessage, String expectedPasswordMessage) {
        softly.assertThat(signInPage.getEmailErrorText()).isEqualTo(expectedEmailMessage);
        softly.assertThat(signInPage.getPasswordErrorText()).isEqualTo(expectedPasswordMessage);
        softly.assertAll();

    }


}

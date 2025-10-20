package com.softserve.academy.HW_14_Loggs.tests;

import com.softserve.academy.HW_14_Loggs.data.SignInTestData;
import com.softserve.academy.HW_14_Loggs.utils.EnvUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SignInNegativeTest extends SignInBaseTest {

    //============================ NEGATIVE TESTS ==================================

    //---------------------- Stream<Arguments> ------------------------------

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#invalidLoginAsArguments")
    @DisplayName("Verify error messages when both email and password are invalid")
    public void shouldVerifyBothInvalidEmailAndPassword(String email, String passwordKey, String expectedEmailMessage, String expectedPasswordMessage) {
        logger.info("Running test for invalid credentials: email={}, passwordKey={}", email, passwordKey);
        String password = EnvUtils.getPassword(passwordKey);


        logger.debug("Entering credentials and verifying error messages...");
        signInFunctions.enterCredentialsAndPasswordTab(email, password);

        signInAssertions.assertErrorEmailAndPasswordMessages(expectedEmailMessage, expectedPasswordMessage);
        logger.debug("Expected email message: {}, expected password message: {}", expectedEmailMessage, expectedPasswordMessage);
        logger.info("Error message verification completed for: {}", email);
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#invalidEmailsAsArguments")
    @DisplayName("Verify invalid email validation messages")
    public void shouldVerifyInvalidEmailMessage(String email, String expectedEmailErrorMessage) {
        logger.info("Running test for invalid email: {}", email);

        String password = EnvUtils.getPassword("VALID_PASSWORD_1");

        logger.debug("Entering invalid email and verifying validation message...");
        signInFunctions.enterCredentialsAndEmailTab(email, password);

        signInAssertions.assertEmailErrorText(expectedEmailErrorMessage);
        logger.debug("Expected email error message: {}", expectedEmailErrorMessage);
        logger.info("Invalid email validation verified for: {}", email);
    }


    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#invalidPasswordsAsArguments")
    @DisplayName("Verify invalid password validation messages")
    public void shouldVerifyInvalidPasswords(String email, String passwordKey, String expectedPasswordErrorMessage) {
        logger.info("Running test for invalid password: email={}, passwordKey={}", email, passwordKey);

        String password = EnvUtils.getPassword(passwordKey);

        logger.debug("Entering invalid password and verifying validation message...");
        signInFunctions.enterCredentialsAndPasswordTab(email, password);

        signInAssertions.assertPasswordErrorText(expectedPasswordErrorMessage);
        logger.debug("Expected password error message: {}", expectedPasswordErrorMessage);
        logger.info("Invalid password validation verified for: {}", email);
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#emptyFieldsAsArguments")
    @DisplayName("Verify error messages for empty fields")
    public void shouldVerifyEmptyFieldsErrorMessages(String email, String passwordKey, String expectedEmailMessage, String expectedPasswordMessage) {
        logger.info("Running test for empty fields: email={}, passwordKey={}", email, passwordKey);

        String password = EnvUtils.getPassword(passwordKey);

        logger.debug("Entering empty credentials and verifying error messages...");
        signInFunctions.enterCredentialsAndPasswordTab(email, password);

        signInAssertions.assertEmptyEmailAndPasswordMessages(expectedEmailMessage, expectedPasswordMessage);
        logger.debug("Expected messages → email: '{}', password: '{}'", expectedEmailMessage, expectedPasswordMessage);
        logger.info("Empty field error validation verified for: {}", email);
    }


    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#wrongPasswordsAsArguments")
    @DisplayName("Verify error shown for valid email with incorrect password")
    public void shouldVerifyErrorShownForWrongPassword(String email, String passwordKey, String expectedGeneralErrorMessage) {
        logger.info("Running test for wrong password: email={}, passwordKey={}", email, passwordKey);

        String password = EnvUtils.getPassword(passwordKey);

        logger.debug("Entering valid email with incorrect password...");
        signInFunctions.enterCredentials(email, password);
        signInFunctions.clickSignInSubmit();

        signInAssertions.assertGeneralErrorText(expectedGeneralErrorMessage);
        logger.debug("Expected general error message: {}", expectedGeneralErrorMessage);
        logger.info("Wrong password error verified for: {}", email);
    }


    //---------------------- Stream<SignInTestData> ------------------------------

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#invalidLoginsAsTestData")
    @DisplayName("Verify error messages when both email and password are invalid")
    public void shouldVerifyBothInvalidEmailAndPassword(SignInTestData testData) {
        logger.info("Running test for invalid credentials: email={}, passwordKey={}", testData.getEmail(), testData.getPasswordKey());

        String password = EnvUtils.getPassword(testData.getPasswordKey());

        logger.debug("Entering credentials and verifying error messages...");
        signInFunctions.enterCredentialsAndPasswordTab(testData.getEmail(), password);

        signInAssertions.assertErrorEmailAndPasswordMessages(
                testData.getExpectedEmailMessage(),
                testData.getExpectedPasswordMessage());
        logger.debug("Expected email message: {}, expected password message: {}", testData.getExpectedEmailMessage(), testData.getExpectedPasswordMessage());
        logger.info("Error message verification completed for: {}", testData.getEmail());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#invalidEmailsAsTestData")
    @DisplayName("Verify invalid email validation messages")
    public void shouldVerifyInvalidEmailMessage(SignInTestData testData) {
        logger.info("Running test for invalid email: {}", testData.getEmail());

        String password = EnvUtils.getPassword("VALID_PASSWORD_1");

        logger.debug("Entering invalid email and verifying validation message...");
        signInFunctions.enterCredentialsAndEmailTab(testData.getEmail(), password);

        signInAssertions.assertEmailErrorText(testData.getExpectedEmailMessage());
        logger.debug("Expected email error message: {}", testData.getExpectedEmailMessage());
        logger.info("Invalid email validation verified for: {}", testData.getEmail());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#invalidPasswordsAsTestData")
    @DisplayName("Verify invalid password validation messages")
    public void shouldVerifyInvalidPasswords(SignInTestData testData) {
        logger.info("Running test for invalid password: email={}, passwordKey={}", testData.getEmail(), testData.getPasswordKey());

        String password = EnvUtils.getPassword(testData.getPasswordKey());

        logger.debug("Entering invalid password and verifying validation message...");
        signInFunctions.enterCredentialsAndPasswordTab(testData.getEmail(), password);

        signInAssertions.assertPasswordErrorText(testData.getExpectedPasswordMessage());
        logger.debug("Expected password error message: {}", testData.getExpectedPasswordMessage());
        logger.info("Invalid password validation verified for: {}", testData.getEmail());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#emptyFieldsAsTestData")
    @DisplayName("Verify error messages for empty fields")
    public void shouldVerifyEmptyFieldsErrorMessages(SignInTestData testData) {
        logger.info("Running test for empty fields: email={}, passwordKey={}", testData.getEmail(), testData.getPasswordKey());

        String password = EnvUtils.getPassword(testData.getPasswordKey());

        logger.debug("Entering empty credentials and verifying error messages...");
        signInFunctions.enterCredentialsAndPasswordTab(testData.getEmail(), password);


        signInAssertions.assertEmptyEmailAndPasswordMessages(testData.getExpectedEmailMessage(), testData.getExpectedPasswordMessage());
        logger.debug("Expected messages → email: '{}', password: '{}'", testData.getExpectedEmailMessage(), testData.getExpectedPasswordMessage());
        logger.info("Empty field error validation verified for: {}", testData.getEmail());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#wrongPasswordsAsTestData")
    @DisplayName("Verify error shown for valid email with incorrect password")
    public void shouldVerifyErrorShownForWrongPassword(SignInTestData testData) {
        logger.info("Running test for wrong password: email={}, passwordKey={}", testData.getEmail(), testData.getPasswordKey());

        String password = EnvUtils.getPassword(testData.getPasswordKey());

        logger.debug("Entering valid email with incorrect password...");
        signInFunctions.enterCredentials(testData.getEmail(), password);
        signInFunctions.clickSignInSubmit();

        signInAssertions.assertGeneralErrorText(testData.getExpectedGeneralErrorMessage());
        logger.debug("Expected general error message: {}", testData.getExpectedGeneralErrorMessage());
        logger.info("Wrong password error verified for: {}", testData.getEmail());
    }


}

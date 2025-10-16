package com.softserve.academy.HW_13_ConstructingScripts.tests;

import com.softserve.academy.HW_13_ConstructingScripts.data.SignInTestData;
import com.softserve.academy.HW_13_ConstructingScripts.utils.EnvUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SignInNegativeTest extends SignInBaseTest {

    //============================ NEGATIVE TESTS ==================================

    //---------------------- Stream<Arguments> ------------------------------

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#invalidLoginAsArguments")
    @DisplayName("Verify error messages when both email and password are invalid")
    public void shouldVerifyBothInvalidEmailAndPassword(String email, String passwordKey, String expectedEmailMessage, String expectedPasswordMessage) {
        String password = EnvUtils.getPassword(passwordKey);
        signInFunctions.enterCredentialsAndPasswordTab(email, password);
        signInAssertions.assertErrorEmailAndPasswordMessages(expectedEmailMessage, expectedPasswordMessage);
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#invalidEmailsAsArguments")
    @DisplayName("Verify invalid email validation messages")
    public void shouldVerifyInvalidEmailMessage(String email, String expectedEmailErrorMessage) {
        String password = EnvUtils.getPassword("VALID_PASSWORD_1");
        signInFunctions.enterCredentialsAndEmailTab(email, password);
        signInAssertions.assertEmailErrorText(expectedEmailErrorMessage);
    }


    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#invalidPasswordsAsArguments")
    @DisplayName("Verify invalid password validation messages")
    public void shouldVerifyInvalidPasswords(String email, String passwordKey, String expectedPasswordErrorMessage) {
        String password = EnvUtils.getPassword(passwordKey);
        signInFunctions.enterCredentialsAndPasswordTab(email, password);
        signInAssertions.assertPasswordErrorText(expectedPasswordErrorMessage);
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#emptyFieldsAsArguments")
    @DisplayName("Verify error messages for empty fields")
    public void shouldVerifyEmptyFieldsErrorMessages(String email, String passwordKey, String expectedEmailMessage, String expectedPasswordMessage) {
        String password = EnvUtils.getPassword(passwordKey);
        signInFunctions.enterCredentialsAndPasswordTab(email, password);
        signInAssertions.assertEmptyEmailAndPasswordMessages(expectedEmailMessage, expectedPasswordMessage);
    }


    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#wrongPasswordsAsArguments")
    @DisplayName("Verify error shown for valid email with incorrect password")
    public void shouldVerifyErrorShownForWrongPassword(String email, String passwordKey, String expectedGeneralErrorMessage) {
        String password = EnvUtils.getPassword(passwordKey);
        signInFunctions.enterCredentials(email, password);
        signInFunctions.clickSignInSubmit();
        signInAssertions.assertGeneralErrorText(expectedGeneralErrorMessage);
    }


    //---------------------- Stream<SignInTestData> ------------------------------

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#invalidLoginsAsTestData")
    @DisplayName("Verify error messages when both email and password are invalid")
    public void shouldVerifyBothInvalidEmailAndPassword(SignInTestData testData) {
        String password = EnvUtils.getPassword(testData.getPasswordKey());
        signInFunctions.enterCredentialsAndPasswordTab(testData.getEmail(), password);
        signInAssertions.assertErrorEmailAndPasswordMessages(
                testData.getExpectedEmailMessage(),
                testData.getExpectedPasswordMessage());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#invalidEmailsAsTestData")
    @DisplayName("Verify invalid email validation messages")
    public void shouldVerifyInvalidEmailMessage(SignInTestData testData) {
        String password = EnvUtils.getPassword("VALID_PASSWORD_1");
        signInFunctions.enterCredentialsAndEmailTab(testData.getEmail(), password);
        signInAssertions.assertEmailErrorText(testData.getExpectedEmailMessage());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#invalidPasswordsAsTestData")
    @DisplayName("Verify invalid password validation messages")
    public void shouldVerifyInvalidPasswords(SignInTestData testData) {
        String password = EnvUtils.getPassword(testData.getPasswordKey());
        signInFunctions.enterCredentialsAndPasswordTab(testData.getEmail(), password);
        signInAssertions.assertPasswordErrorText(testData.getExpectedPasswordMessage());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#emptyFieldsAsTestData")
    @DisplayName("Verify error messages for empty fields")
    public void shouldVerifyEmptyFieldsErrorMessages(SignInTestData testData) {
        String password = EnvUtils.getPassword(testData.getPasswordKey());
        signInFunctions.enterCredentialsAndPasswordTab(testData.getEmail(), password);
        signInAssertions.assertEmptyEmailAndPasswordMessages(testData.getExpectedEmailMessage(), testData.getExpectedPasswordMessage());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#wrongPasswordsAsTestData")
    @DisplayName("Verify error shown for valid email with incorrect password")
    public void shouldVerifyErrorShownForWrongPassword(SignInTestData testData) {
        String password = EnvUtils.getPassword(testData.getPasswordKey());
        signInFunctions.enterCredentials(testData.getEmail(), password);
        signInFunctions.clickSignInSubmit();
        signInAssertions.assertGeneralErrorText(testData.getExpectedGeneralErrorMessage());
    }


}

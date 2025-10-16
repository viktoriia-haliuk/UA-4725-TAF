package com.softserve.academy.HW_13_ConstructingScripts.tests;

import com.softserve.academy.HW_13_ConstructingScripts.data.SignInTestData;
import com.softserve.academy.HW_13_ConstructingScripts.pages.UbsHomePage;
import com.softserve.academy.HW_13_ConstructingScripts.utils.EnvUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class SignInPositiveTest extends SignInBaseTest {

    //========================= SUCCESSFUL LOGIN ==================================

    //---------------------- Stream<SignInTestData> ------------------------------
    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#validLoginsAsTestData")
    @DisplayName("Verify successful login with multiple valid users")
    public void shouldVerifySuccessfulLogin(SignInTestData testData) {
        String password = EnvUtils.getPassword(testData.getPasswordKey());
        UbsHomePage ubsHomePage = signInFunctions.signInWith(testData.getEmail(), password);
        String actualUserName = ubsHomePage.getLoggedInUserName();
        signInAssertions.assertUserName(actualUserName, testData.getExpectedUserName());
    }

    //---------------------- Stream<Arguments> ------------------------------
    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_13_ConstructingScripts.data.SignInMethodSources#validLoginsAsArguments")
    @DisplayName("Verify successful login with multiple valid users")
    public void shouldVerifySuccessfulLogin(String email, String passwordKey, String expectedUserName) {
        String password = EnvUtils.getPassword(passwordKey);
        UbsHomePage ubsHomePage = signInFunctions.signInWith(email, password);
        String actualUserName = ubsHomePage.getLoggedInUserName();
        signInAssertions.assertUserName(actualUserName, expectedUserName);
    }

}

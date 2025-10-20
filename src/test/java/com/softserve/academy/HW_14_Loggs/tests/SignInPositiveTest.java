package com.softserve.academy.HW_14_Loggs.tests;

import com.softserve.academy.HW_14_Loggs.data.SignInTestData;
import com.softserve.academy.HW_14_Loggs.pages.UbsHomePage;
import com.softserve.academy.HW_14_Loggs.utils.EnvUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class SignInPositiveTest extends SignInBaseTest {

    //========================= SUCCESSFUL LOGIN ==================================

    //---------------------- Stream<SignInTestData> ------------------------------
    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#validLoginsAsTestData")
    @DisplayName("Verify successful login with multiple valid users")
    public void shouldVerifySuccessfulLogin(SignInTestData testData) {
        logger.info("Running login test for email: {}", testData.getEmail());

        logger.trace("Fetching password from environment for key: {}", testData.getPasswordKey());
        String password = EnvUtils.getPassword(testData.getPasswordKey());

        logger.trace("Attempting login via SignInFunctions...");
        UbsHomePage ubsHomePage = signInFunctions.signInWith(testData.getEmail(), password);
        logger.debug("Sign-in completed for email: {}", testData.getEmail());

        logger.trace("Fetching displayed username from UI...");
        String actualUserName = ubsHomePage.getLoggedInUserName();
        signInAssertions.assertUserName(actualUserName, testData.getExpectedUserName());

        logger.info("Login verification successful for user: {}", testData.getEmail());
    }

    //---------------------- Stream<Arguments> ------------------------------
    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#validLoginsAsArguments")
    @DisplayName("Verify successful login with multiple valid users")
    public void shouldVerifySuccessfulLogin(String email, String passwordKey, String expectedUserName) {
        logger.info("Running login test for email: {}", email);


        logger.trace("Fetching password from environment for key: {}", passwordKey);
        String password = EnvUtils.getPassword(passwordKey);

        logger.trace("Attempting login via SignInFunctions...");
        UbsHomePage ubsHomePage = signInFunctions.signInWith(email, password);
        logger.debug("Sign-in completed for email: {}", email);

        logger.trace("Fetching displayed username from UI...");
        String actualUserName = ubsHomePage.getLoggedInUserName();
        signInAssertions.assertUserName(actualUserName, expectedUserName);

        logger.info("Login verification successful for user: {}", email);

    }

}

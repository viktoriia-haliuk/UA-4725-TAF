package com.softserve.academy.HW_14_Loggs.tests;

import com.softserve.academy.HW_14_Loggs.functional.SignInAssertions;
import com.softserve.academy.HW_14_Loggs.functional.SignInFunctions;
import org.junit.jupiter.api.BeforeEach;

public abstract class SignInBaseTest extends TestRunner {

    protected SignInAssertions signInAssertions;
    protected SignInFunctions signInFunctions;

    @BeforeEach
    public void initSignInPageElements() {
        signInPage.openSignInModal();
        signInAssertions = new SignInAssertions(signInPage);
        signInFunctions = new SignInFunctions(signInPage, driver, wait);
    }
}

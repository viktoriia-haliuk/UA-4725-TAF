package com.softserve.academy.HW_13_ConstructingScripts.tests;

import com.softserve.academy.HW_13_ConstructingScripts.functional.SignInAssertions;
import com.softserve.academy.HW_13_ConstructingScripts.functional.SignInFunctions;
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

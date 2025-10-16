package com.softserve.academy.HW_12_JSInject.pages;

import org.openqa.selenium.By;

public class LoginPageLocators {

    //========================================================================================
    //                                HEADER / OPEN MODAL
    //========================================================================================
    public static final By SIGN_IN_BUTTON = By.cssSelector(".ubs-header-sing-in-img");

    //========================================================================================
    //                                MODAL TEXTS / LABELS
    //========================================================================================
    public static final By WELCOME_TEXT = By.cssSelector(".container h1");
    public static final By SIGN_IN_DETAILS_TEXT = By.cssSelector(".container h2");
    public static final By EMAIL_LABEL = By.cssSelector("label[for='email']");
    public static final By PASSWORD_LABEL = By.cssSelector("label[for='password']");

    //========================================================================================
    //                                INPUTS / BUTTONS / ERRORS
    //========================================================================================
    public static final By EMAIL_INPUT = By.id("email");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By SIGN_IN_SUBMIT_BUTTON = By.cssSelector("button.ubsStyle");

    public static final By GENERAL_ERROR_MESSAGE = By.cssSelector("div.alert-general-error");
    public static final By ERROR_EMAIL = By.id("email-err-msg");
    public static final By ERROR_PASSWORD = By.id("pass-err-msg");

    public static final By CLOSE_BUTTON = By.cssSelector("a.close-modal-window");
    public static final By GOOGLE_SIGN_IN_BUTTON = By.cssSelector("button.google-sign-in");
    public static final By FORGOT_PASSWORD_LINK = By.cssSelector(".forgot-wrapper-ubs a");
    public static final By SIGN_UP_LINK = By.cssSelector(".missing-account a");
    public static final By SIGN_IN_MODAL = By.cssSelector(".mat-mdc-dialog-container");

    //========================================================================================
    //                                USER NAME
    //========================================================================================
    public static final By USER_NAME = By.cssSelector("li.ubs-user-name");


}

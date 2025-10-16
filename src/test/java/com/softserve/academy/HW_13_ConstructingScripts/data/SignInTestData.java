package com.softserve.academy.HW_13_ConstructingScripts.data;

/**
 * Parameter object for Sign In test cases.
 * Supports multiple constructors to fit different CSV shapes:
 *  - valid logins: (email, passwordKey, expectedUserName)
 *  - invalid / field checks: (email, passwordKey, expectedEmailMessage, expectedPasswordMessage)
 *  - other single-error cases: (email, passwordKey, expectedGeneralErrorMessage) can use 4-arg constructor
 */
public class SignInTestData {
    // common fields
    private String email;
    private String passwordKey;

    // possible expected results
    private String expectedGeneralErrorMessage;
    private String expectedEmailMessage;
    private String expectedPasswordMessage;

    private String expectedUserName;


    // Full constructor (all fields)
    public SignInTestData(String email,
                          String passwordKey,

                          String expectedGeneralErrorMessage,
                          String expectedEmailMessage,
                          String expectedPasswordMessage,

                          String expectedUserName
                          ) {
        this.email = email;
        this.passwordKey = passwordKey;

        this.expectedGeneralErrorMessage = expectedGeneralErrorMessage;
        this.expectedEmailMessage = expectedEmailMessage;
        this.expectedPasswordMessage = expectedPasswordMessage;

        this.expectedUserName = expectedUserName;
    }

    // 3-arg constructor for valid login (email, passwordKey, expectedUserName)
    public SignInTestData(String email,
                          String passwordKey,
                          String expectedUserName) {
        this(email, passwordKey, "", "", "", expectedUserName);
    }

    // 4-arg constructor for invalid email and password (email, passwordKey, expectedEmailMessage, expectedPasswordMessage)
    public SignInTestData(String email,
                          String passwordKey,
                          String expectedEmailMessage,
                          String expectedPasswordMessage) {
        this(email, passwordKey, "", expectedEmailMessage, expectedPasswordMessage, "");
    }


    // getters
    public String getEmail() {
        return email;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public String getExpectedEmailMessage() {
        return expectedEmailMessage;
    }

    public String getExpectedPasswordMessage() {
        return expectedPasswordMessage;
    }

    public String getExpectedUserName() {
        return expectedUserName;
    }

    public String getExpectedGeneralErrorMessage() {
        return expectedGeneralErrorMessage;
    }

    @Override
    public String toString() {
        return "SignInTestData{" +
                "email='" + email + '\'' +
                ", passwordKey='" + passwordKey + '\'' +
                ", expectedEmailMessage='" + expectedEmailMessage + '\'' +
                ", expectedPasswordMessage='" + expectedPasswordMessage + '\'' +
                ", expectedUserName='" + expectedUserName + '\'' +
                ", expectedGeneralErrorMessage='" + expectedGeneralErrorMessage + '\'' +
                '}';
    }
}

package com.softserve.academy.HW_14_Loggs.data;

import java.util.List;

public class SignInDataRepository {

    // 🟡 VALID LOGINS
    public static List<SignInTestData> getValidLogins() {
        return List.of(
                new SignInTestData("tegusi1@yopmail.com", "VALID_PASSWORD_1", "Tegusi"),
                new SignInTestData("galpa2@yopmail.com", "VALID_PASSWORD_2", "Galpa")
        );
    }

    // 🟡 INVALID LOGINS (email + invalid password format)
    public static List<SignInTestData> getInvalidLogins() {
        return List.of(
                new SignInTestData(
                        "invalid1",
                        "INVALID_PASSWORD_1",
                        "Please check if the email is written correctly",
                        "Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), " +
                                "one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)"
                ),
                new SignInTestData(
                        "invalid2",
                        "INVALID_PASSWORD_2",
                        "Please check if the email is written correctly",
                        "Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), " +
                                "one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)"
                )
        );
    }

    // 🟡 INVALID EMAILS
    public static List<SignInTestData> getInvalidEmails() {
        return List.of(
                new SignInTestData("plainaddress", "", "Please check if the email is written correctly", ""),
                new SignInTestData("@missingusername.com", "", "Please check if the email is written correctly", ""),
                new SignInTestData("username@.com", "", "Please check if the email is written correctly", ""),
                new SignInTestData("username@domain", "", "Please check if the email is written correctly", ""),
                new SignInTestData("username@domain,comma", "", "Please check if the email is written correctly", ""),
                new SignInTestData("space in@email.com", "", "Please check if the email is written correctly", "")
        );
    }

    // 🟡 INVALID PASSWORDS (valid email but invalid password)
    public static List<SignInTestData> getInvalidPasswords() {
        return List.of(
                new SignInTestData("tegusi1@yopmail.com", "INVALID_TOO_SHORT_PASSWORD", "",
                        "Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), one lowercase letter (a-z), a digit (0-9), and a special character (~!@#$%^&*()+=_-{}[]|:;”?/<>,.)"),
                new SignInTestData("tegusi1@yopmail.com", "INVALID_TOO_LONG_PASSWORD", "",
                        "Password must be less than 20 characters long without spaces.")
        );
    }


    // 🟡 EMPTY FIELDS
    public static List<SignInTestData> getEmptyFieldsCases() {
        return List.of(
                new SignInTestData("", "EMPTY_PASSWORD", "Email is required.", "This field is required")
        );
    }


    // 🟡 WRONG PASSWORD CASE
    public static List<SignInTestData> getWrongPasswordCases() {
        return List.of(
                new SignInTestData(
                        "tegusi1@yopmail.com", "NOT_MATCHED_PASSWORD", "", "", "", "Bad password"
                )
        );
    }

}

package com.softserve.academy.HW_13_ConstructingScripts.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.softserve.academy.HW_13_ConstructingScripts.data.SignInDataRepository.*;

public class SignInMethodSources {


    // =====================================================
    //                Stream<Arguments>
    // =====================================================


    // Positive
    public static Stream<Arguments> validLoginsAsArguments() {
        return getValidLogins().stream()
                .map(d -> Arguments.of(
                        d.getEmail(),
                        d.getPasswordKey(),
                        d.getExpectedUserName()));
    }

    // Negative
    public static Stream<Arguments> invalidLoginAsArguments() {
        return getInvalidLogins().stream()
                .map(d -> Arguments.of(
                        d.getEmail(),
                        d.getPasswordKey(),
                        d.getExpectedEmailMessage(),
                        d.getExpectedPasswordMessage()));
    }

    public static Stream<Arguments> invalidEmailsAsArguments() {
        return getInvalidEmails().stream()
                .map(d -> Arguments.of(
                        d.getEmail(),
                        d.getPasswordKey(),
                        d.getExpectedEmailMessage()));

    }

    public static Stream<Arguments> invalidPasswordsAsArguments() {
        return getInvalidPasswords().stream()
                .map(d -> Arguments.of(
                        d.getEmail(),
                        d.getPasswordKey(),
                        d.getExpectedPasswordMessage()));

    }

    public static Stream<Arguments> emptyFieldsAsArguments() {
        return getEmptyFieldsCases().stream()
                .map(d -> Arguments.of(
                        d.getEmail(),
                        d.getPasswordKey(),
                        d.getExpectedEmailMessage(),
                        d.getExpectedPasswordMessage()));
    }

    public static Stream<Arguments> wrongPasswordsAsArguments() {
        return getWrongPasswordCases().stream()
                .map(d -> Arguments.of(
                        d.getEmail(),
                        d.getPasswordKey(),
                        d.getExpectedGeneralErrorMessage()));
    }


    // =====================================================
    //              Stream<SignInTestData>
    // =====================================================

    // Positive
    public static Stream<SignInTestData> validLoginsAsTestData() {
        return getValidLogins().stream();
    }

    // Negative
    public static Stream<SignInTestData> invalidLoginsAsTestData() {
        return getInvalidLogins().stream();
    }

    public static Stream<SignInTestData> invalidEmailsAsTestData() {
        return getInvalidEmails().stream();
    }

    public static Stream<SignInTestData> invalidPasswordsAsTestData() {
        return getInvalidPasswords().stream();
    }

    public static Stream<SignInTestData> emptyFieldsAsTestData() {
        return getEmptyFieldsCases().stream();
    }

    public static Stream<SignInTestData> wrongPasswordsAsTestData() {
        return getWrongPasswordCases().stream();
    }




}
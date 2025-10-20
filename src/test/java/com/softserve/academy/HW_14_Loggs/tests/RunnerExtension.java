package com.softserve.academy.HW_14_Loggs.tests;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class RunnerExtension implements AfterTestExecutionCallback {
    protected static boolean isTestSuccessful = false;

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean isExceptionPresent = context.getExecutionException().isPresent();
        System.out.println("\t\t\t\tException.isPresent() = " + isExceptionPresent); //false - SUCCESS, true - FAILED
        System.out.println("\t\t\t\tTest context.getDisplayName(): " + context.getDisplayName());

        isTestSuccessful = !isExceptionPresent;
    }
}
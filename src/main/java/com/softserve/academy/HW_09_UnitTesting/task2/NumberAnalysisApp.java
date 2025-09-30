package com.softserve.academy.HW_09_UnitTesting.task2;

import java.util.ArrayList;

public class NumberAnalysisApp {
    public static void main(String[] args) {
        ArrayList<Integer> randomGeneratedNumbers = NumberAnalysis.generateRandomNumbers();
        NumberAnalysis numbersList = new NumberAnalysis(randomGeneratedNumbers);

        numbersList.printNumbers();

        System.out.println("Min number: " + numbersList.findMin());


        System.out.println("Max number: " + numbersList.findMax());


        System.out.println("Average number: " + numbersList.findAverage());


        numbersList.removeEvenNumbers();
        System.out.println("\nAfter removing even numbers: ");
        numbersList.printNumbers();


        int checkNumber = 50;
        System.out.println("\nDoes the collection contain " + checkNumber + "? " + numbersList.containsNumber(checkNumber));

        numbersList.sortNumbers();
        System.out.println("\nSorted collection: ");
        numbersList.printNumbers();


    }
}

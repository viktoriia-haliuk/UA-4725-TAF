package com.softserve.academy.HW_09_UnitTesting_task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberAnalysis {
    ArrayList<Integer> numbers;

    public NumberAnalysis(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public static ArrayList<Integer> generateRandomNumbers() {
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            randomNumbers.add(random.nextInt(100) + 1); // числа від 1 до 100
        }
        return randomNumbers;
    }


    public void printNumbers() {
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public int findMin() {
        return Collections.min(numbers);
    }

    public int findMax() {
        return Collections.max(numbers);
    }

    public double findAverage() {
        if (numbers.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }


    public void removeEvenNumbers() {
        numbers.removeIf(num -> num % 2 == 0);
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    public void sortNumbers() {
        Collections.sort(numbers);
    }
}

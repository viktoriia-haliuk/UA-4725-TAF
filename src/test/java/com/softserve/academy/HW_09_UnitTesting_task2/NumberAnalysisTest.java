package com.softserve.academy.HW_09_UnitTesting_task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class NumberAnalysisTest {
    private NumberAnalysis analysis;

    @BeforeEach
    public void setUp() {
        analysis = new NumberAnalysis(new ArrayList<>(Arrays.asList(50, 27, 99, 17, 8, 83)));
    }

    //Positive scenarios
    @Test
    @DisplayName("Should find the minimum number correctly")
    public void testFindMin() {
        assertEquals(8, analysis.findMin());
    }

    @Test
    @DisplayName("Should find the maximum number correctly")
    public void testFindMax() {
        assertEquals(99, analysis.findMax());
    }

    @Test
    @DisplayName("Should calculate the average number with precision")
    public void testFindAverage() {
        assertEquals(47.333333333, analysis.findAverage(), 1e-6);
    }

    @Test
    @DisplayName("Should remove all even numbers from the list")
    public void testRemoveEvenNumbers() {
        analysis.removeEvenNumbers();
        assertEquals(Arrays.asList(27, 99, 17, 83), analysis.numbers);
    }

    @Test
    @DisplayName("Should return true when a number is present")
    public void testContainsNumberTrue() {
        assertTrue(analysis.containsNumber(8));
    }

    @Test
    @DisplayName("Should return false when a number is not present")
    public void testContainsNumberFalse() {
        assertFalse(analysis.containsNumber(100));
    }

    @Test
    @DisplayName("Should sort the numbers in ascending order")
    public void testSortNumbers() {
        analysis.sortNumbers();
        assertEquals(Arrays.asList(8, 17, 27, 50, 83, 99), analysis.numbers);
    }

    @Test
    @DisplayName("Generated list should contain exactly 20 elements")
    public void testGenerateRandomNumbersSize() {
        ArrayList<Integer> randoms = NumberAnalysis.generateRandomNumbers();
        assertEquals(20, randoms.size());
    }

    @Test
    @DisplayName("Generated numbers should be within the [1, 100] range")
    public void testGenerateRandomNumbersRange() {
        ArrayList<Integer> randoms = NumberAnalysis.generateRandomNumbers();
        assertTrue(randoms.stream().allMatch(n -> n >= 1 && n <= 100));
    }

    // Negative scenarios
    @Test
    public void testFindMinEmptyList() {
        NumberAnalysis emptyAnalysis = new NumberAnalysis(new ArrayList<>());
        assertThrows(NoSuchElementException.class, emptyAnalysis::findMin);
    }

    @Test
    public void testFindMaxEmptyList() {
        NumberAnalysis emptyAnalysis = new NumberAnalysis(new ArrayList<>());
        assertThrows(NoSuchElementException.class, emptyAnalysis::findMax);
    }

    @Test
    public void testFindAverageEmptyList() {
        NumberAnalysis emptyAnalysis = new NumberAnalysis(new ArrayList<>());
        assertEquals(0.0, emptyAnalysis.findAverage());
    }




}
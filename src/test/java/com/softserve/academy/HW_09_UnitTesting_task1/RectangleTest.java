package com.softserve.academy.HW_09_UnitTesting_task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest{
//Positive scenarios

    @Test
    @DisplayName("A rectangle with default parameters (width=1.0, height=1.0, angle=90.0) should be initialized by default constructor")
    public void testDefaultConstructor() {
        Rectangle rectangle = new Rectangle();
        assertEquals(1.0, rectangle.getWidth());
        assertEquals(1.0, rectangle.getHeight());
        assertEquals(90.0, rectangle.getAngle());
    }

    @Test
    @DisplayName("A rectangle with given width and height should be initialized by parameterized constructor")
    public void testConstructorWithParameters(){
        Rectangle rectangle = new Rectangle(5.0,10.0);
        assertEquals(5.0, rectangle.getWidth());
        assertEquals(10.0, rectangle.getHeight());
    }

    @Test
    @DisplayName("Proper width should be initiated")
    public void testSetsAndGetWidth() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(7.0);
        assertEquals(7.0, rectangle.getWidth());
    }


    @Test
    @DisplayName("Proper height should be initiated")
    public void testSetsAndGetHeight() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(8.0);
        assertEquals(8.0, rectangle.getHeight());
    }

    @ParameterizedTest (name = "Area of {0}x{1} should be {2}")
    @DisplayName("Area should be calculated properly")
    @CsvSource({
            "4.0, 8.0, 32.0",
            "5.0, 8.9, 44.5",
            "15.2, 16.0, 243.2",
            "8.0, 8.0, 64.0"
    })
    public void testCalculateArea(double width,double height, double expectedArea){
        Rectangle rectangle = new Rectangle(width,height);
        assertEquals(expectedArea, rectangle.calculateArea(), 1e-6);

    };

    @ParameterizedTest(name = "Perimeter of {0}x{1} should be {2}")
    @DisplayName("Perimeter should be calculated properly")
    @CsvSource({
            "3.5, 7.0, 21.0",
            "6.0, 4.5, 21.0",
            "12.7, 15.0, 55.4",
            "9.0, 9.0, 36.0"
    })
    public void testCalculatePerimeter(double width,double height, double expectedPerimeter){
        Rectangle rectangle = new Rectangle(width,height);
        assertEquals(expectedPerimeter, rectangle.calculatePerimeter(), 1e-6);

    };

    @ParameterizedTest (name = "Diagonal of {0}x{1} should be {2}")
    @DisplayName("Diagonal should be calculated properly")
    @CsvSource({
            "2.0, 9.0, 9.219544457",
            "5.0, 11.2, 12.26539848",
            "13.5, 16.0, 20.93442142",
            "15.9, 15.9, 22.48599564"
    })
    public void testCalculateDiagonal(double width,double height, double expectedDiagonal){
        Rectangle rectangle = new Rectangle(width,height);
        assertEquals(expectedDiagonal, rectangle.calculateDiagonal(), 1e-6);

    };

//Negative scenarios

    @Test
    @DisplayName("IllegalArgumentException should be thrown when width <= 0")
    public void testSetWidthWithInvalidValues() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(0),
                "IllegalArgumentException should be thrown");
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-5),
                "IllegalArgumentException should be thrown");
    }

    @Test
    @DisplayName("IllegalArgumentException should be thrown when height <= 0")
    public void testSetHeightWithInvalidValues() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(0),
                "IllegalArgumentException should be thrown");
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-10),
                "IllegalArgumentException should be thrown");
    }


}
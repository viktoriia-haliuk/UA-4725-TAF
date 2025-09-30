package com.softserve.academy.HW_09_UnitTesting_task1;

public class RectangleApp {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(15.0,22.0);
        System.out.println("Area: " + rectangle.calculateArea());
        System.out.println("Perimeter: " + rectangle.calculatePerimeter());
        System.out.println("Diagonal: " + rectangle.calculateDiagonal());
    }
}

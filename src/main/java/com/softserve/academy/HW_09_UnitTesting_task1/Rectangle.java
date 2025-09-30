package com.softserve.academy.HW_09_UnitTesting_task1;

public class Rectangle {
    private double width;
    private double height;
    private final double angle = 90.0;

    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if(width<=0){
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height<=0){
            throw new IllegalArgumentException("Height must be > 0");
        }
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public double calculateArea(){
        return this.width * this.height;

    }

    public double calculatePerimeter(){
        return 2*(this.width + this.height);
    }

    public double calculateDiagonal() {
        return Math.sqrt(this.width * this.width + this.height * this.height);
    }

    private void validateDimensions() {
        if (width <= 0 || height <= 0) {
            throw new IllegalStateException("Width and height must be > 0");
        }}

}

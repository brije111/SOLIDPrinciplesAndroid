package com.example.solidprinciplesandroid.ocp;

import java.util.ArrayList;

/*
    This class got code for calculating area according to Shape.
    There are many conditions in the code for getting shape specific area.
    If there is a change in client requirement & now we need to calculate area of Circle shape too.
    Now we need to modify the Area manager code to write logic of calculating area of new shape circle.
    This is clearly a violation of OCP.
 */

public class AreaManager {
    public double calculateArea(ArrayList<Object>... shapes) {
        double area = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectangle) {
                Rectangle rect = (Rectangle)shape;
                area += (rect.length * rect.height);
            } else if (shape instanceof Circle) {
                Circle circle = (Circle)shape;
                area += (circle.radius * circle.radius * Math.PI);
            } else {
                throw new RuntimeException("Shape not supported");
            }
        }
        return area;
    }
    public class Rectangle {
        private double length;
        private double height;
    }
    public class Circle {
        private double radius;
    }
}

/*
    To Correct this problem, we made a new interface Shape, containing a method "getArea()".
    Every shape will implement this interface & the logic of area calculation will be written in getArea() method.
    So in case a new shape will be added in future, there will be no change in AreaManager class.
    Now it is following the OCP.
 */

 interface Shape {
    double getArea();
}

 class Rectangle implements Shape {
    private double length;
    private double height;

    @Override
    public double getArea() {
        return (length * height);
    }
}

 class Circle implements Shape {
    private double radius;

    @Override
    public double getArea() {
        return (radius * radius * Math.PI);
    }
}

 class AreaManagerUpdated {
    public double calculateArea(ArrayList<Shape> shapes) {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }
}

package com.example.solidprinciplesandroid.ocp;

import java.util.ArrayList;

/**
 * Created by 51778499 on 08,April,2019
 * Hcl Technologies,
 * India.
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
        // getters/setters ...
    }
    public class Circle {
        private double radius;
        // getters/setters ...
    }
}

package com.example.graphics.factory;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Shape;
import com.example.graphics.model.Triangle;

/**
 * Factory class for creating different types of shapes.
 * This is an implementation of the Factory Method pattern.
 */
public class ShapeFactory {
    /**
     * Create a circle with the specified parameters
     * @param x the x coordinate of the center
     * @param y the y coordinate of the center
     * @param radius the radius of the circle
     * @return a new Circle object
     */
    public Shape createCircle(int x, int y, int radius) {
        return new Circle(x, y, radius);
    }
    
    /**
     * Create a rectangle with the specified parameters
     * @param x the x coordinate of the top-left corner
     * @param y the y coordinate of the top-left corner
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @return a new Rectangle object
     */
    public Shape createRectangle(int x, int y, int width, int height) {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * Create a line with the specified endpoints
     * @param x1 the x coordinate of the first endpoint
     * @param y1 the y coordinate of the first endpoint
     * @param x2 the x coordinate of the second endpoint
     * @param y2 the y coordinate of the second endpoint
     * @return a new Line object
     */
    public Shape createLine(int x1, int y1, int x2, int y2) {
        return new Line(x1, y1, x2, y2);
    }
    
    /**
     * Create a triangle with the specified vertices
     * @param x1 the x coordinate of the first vertex
     * @param y1 the y coordinate of the first vertex
     * @param x2 the x coordinate of the second vertex
     * @param y2 the y coordinate of the second vertex
     * @param x3 the x coordinate of the third vertex
     * @param y3 the y coordinate of the third vertex
     * @return a new Triangle object
     */
    public Shape createTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        return new Triangle(x1, y1, x2, y2, x3, y3);
    }
} 
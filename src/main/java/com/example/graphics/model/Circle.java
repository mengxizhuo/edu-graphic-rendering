package com.example.graphics.model;

import com.example.graphics.visitor.ShapeVisitor;
import java.io.Serializable;

/**
 * Circle implementation of the Shape interface.
 */
public class Circle implements Shape, Serializable {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private int radius;
    
    /**
     * Constructs a circle with the specified parameters
     * @param x the x coordinate of the center
     * @param y the y coordinate of the center
     * @param radius the radius of the circle
     */
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    @Override
    public int getX() {
        return x;
    }
    
    @Override
    public int getY() {
        return y;
    }
    
    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the radius of the circle
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * Set the radius of the circle
     * @param radius the new radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public Shape clone() {
        return new Circle(this.x, this.y, this.radius);
    }
} 
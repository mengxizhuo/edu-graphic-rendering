package com.example.graphics.model;

import com.example.graphics.visitor.ShapeVisitor;

/**
 * Shape interface representing a graphical shape element.
 * This is part of the Visitor pattern (accepts visitors) and
 * will be instantiated through Factory pattern.
 */
public interface Shape {
    /**
     * Get the x coordinate of the shape
     * @return x coordinate
     */
    int getX();
    
    /**
     * Get the y coordinate of the shape
     * @return y coordinate
     */
    int getY();
    
    /**
     * Set the position of the shape
     * @param x the x coordinate
     * @param y the y coordinate
     */
    void setPosition(int x, int y);
    
    /**
     * Accept method for the Visitor pattern
     * @param visitor the visitor to accept
     */
    void accept(ShapeVisitor visitor);
    
    /**
     * Clone the shape
     * @return a clone of this shape
     */
    Shape clone();
} 
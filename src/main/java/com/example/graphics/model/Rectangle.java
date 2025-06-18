package com.example.graphics.model;

import com.example.graphics.visitor.ShapeVisitor;

/**
 * Rectangle implementation of the Shape interface.
 */
public class Rectangle implements Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    
    /**
     * Constructs a rectangle with the specified parameters
     * @param x the x coordinate of the top-left corner
     * @param y the y coordinate of the top-left corner
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
     * Get the width of the rectangle
     * @return the width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Set the width of the rectangle
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Get the height of the rectangle
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Set the height of the rectangle
     * @param height the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public Shape clone() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
} 
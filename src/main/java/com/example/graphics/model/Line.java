package com.example.graphics.model;

import com.example.graphics.visitor.ShapeVisitor;

/**
 * Line implementation of the Shape interface.
 */
public class Line implements Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    /**
     * Constructs a line with the specified endpoints
     * @param x1 the x coordinate of the first endpoint
     * @param y1 the y coordinate of the first endpoint
     * @param x2 the x coordinate of the second endpoint
     * @param y2 the y coordinate of the second endpoint
     */
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public int getX() {
        return x1; // Return the first endpoint's x as the reference point
    }
    
    @Override
    public int getY() {
        return y1; // Return the first endpoint's y as the reference point
    }
    
    @Override
    public void setPosition(int x, int y) {
        // Calculate the offset and apply it to both endpoints
        int dx = x - x1;
        int dy = y - y1;
        
        x1 = x;
        y1 = y;
        x2 += dx;
        y2 += dy;
    }
    
    /**
     * Get the x coordinate of the first endpoint
     * @return the x1 coordinate
     */
    public int getX1() {
        return x1;
    }
    
    /**
     * Get the y coordinate of the first endpoint
     * @return the y1 coordinate
     */
    public int getY1() {
        return y1;
    }
    
    /**
     * Get the x coordinate of the second endpoint
     * @return the x2 coordinate
     */
    public int getX2() {
        return x2;
    }
    
    /**
     * Get the y coordinate of the second endpoint
     * @return the y2 coordinate
     */
    public int getY2() {
        return y2;
    }
    
    /**
     * Set the coordinates of the first endpoint
     * @param x1 the x coordinate
     * @param y1 the y coordinate
     */
    public void setPoint1(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }
    
    /**
     * Set the coordinates of the second endpoint
     * @param x2 the x coordinate
     * @param y2 the y coordinate
     */
    public void setPoint2(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }
    
    /**
     * Set both endpoints of the line at once
     * @param x1 the x coordinate of the first endpoint
     * @param y1 the y coordinate of the first endpoint
     * @param x2 the x coordinate of the second endpoint
     * @param y2 the y coordinate of the second endpoint
     */
    public void setLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public Shape clone() {
        return new Line(this.x1, this.y1, this.x2, this.y2);
    }
} 
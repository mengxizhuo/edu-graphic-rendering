package com.example.graphics.model;

import com.example.graphics.visitor.ShapeVisitor;
import java.io.Serializable;

/**
 * Triangle implementation of the Shape interface.
 */
public class Triangle implements Shape, Serializable {
    private static final long serialVersionUID = 1L;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    
    /**
     * Constructs a triangle with the specified vertices
     * @param x1 the x coordinate of the first vertex
     * @param y1 the y coordinate of the first vertex
     * @param x2 the x coordinate of the second vertex
     * @param y2 the y coordinate of the second vertex
     * @param x3 the x coordinate of the third vertex
     * @param y3 the y coordinate of the third vertex
     */
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    
    @Override
    public int getX() {
        // 返回三角形的重心作为参考点
//        return (x1 + x2 + x3) / 3;
        return x1;
    }
    
    @Override
    public int getY() {
        // 返回三角形的重心作为参考点
//        return (y1 + y2 + y3) / 3;
        return y1;
    }

    
    @Override
    public void setPosition(int x, int y) {
        // 计算移动的偏移量
        int dx = x - getX();
        int dy = y - getY();
        
        // 移动所有顶点
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        x3 += dx;
        y3 += dy;
    }
    
    /**
     * Get the x coordinate of the first vertex
     * @return the x1 coordinate
     */
    public int getX1() {
        return x1;
    }
    
    /**
     * Get the y coordinate of the first vertex
     * @return the y1 coordinate
     */
    public int getY1() {
        return y1;
    }
    
    /**
     * Get the x coordinate of the second vertex
     * @return the x2 coordinate
     */
    public int getX2() {
        return x2;
    }
    
    /**
     * Get the y coordinate of the second vertex
     * @return the y2 coordinate
     */
    public int getY2() {
        return y2;
    }
    
    /**
     * Get the x coordinate of the third vertex
     * @return the x3 coordinate
     */
    public int getX3() {
        return x3;
    }
    
    /**
     * Get the y coordinate of the third vertex
     * @return the y3 coordinate
     */
    public int getY3() {
        return y3;
    }
    
    /**
     * Set all three vertices of the triangle at once
     * @param x1 the x coordinate of the first vertex
     * @param y1 the y coordinate of the first vertex
     * @param x2 the x coordinate of the second vertex
     * @param y2 the y coordinate of the second vertex
     * @param x3 the x coordinate of the third vertex
     * @param y3 the y coordinate of the third vertex
     */
    public void setTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public Shape clone() {
        return new Triangle(x1, y1, x2, y2, x3, y3);
    }
} 
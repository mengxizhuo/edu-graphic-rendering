package com.example.graphics.visitor;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

/**
 * ShapeVisitor interface for implementing the Visitor pattern.
 * This allows operations to be performed on shape objects without
 * modifying their classes.
 */
public interface ShapeVisitor {
    /**
     * Visit a Circle shape
     * @param circle the circle to visit
     */
    void visit(Circle circle);
    
    /**
     * Visit a Rectangle shape
     * @param rectangle the rectangle to visit
     */
    void visit(Rectangle rectangle);
    
    /**
     * Visit a Line shape
     * @param line the line to visit
     */
    void visit(Line line);
    
    /**
     * Visit a Triangle shape
     * @param triangle the triangle to visit
     */
    void visit(Triangle triangle);
} 
package com.example.graphics.render;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

/**
 * Renderer interface for implementing the Bridge pattern.
 * This interface defines methods for rendering different shapes.
 */
public interface Renderer {
    /**
     * Render a circle
     * @param circle the circle to render
     */
    void renderCircle(Circle circle);
    
    /**
     * Render a rectangle
     * @param rectangle the rectangle to render
     */
    void renderRectangle(Rectangle rectangle);
    
    /**
     * Render a line
     * @param line the line to render
     */
    void renderLine(Line line);
    
    /**
     * Render a triangle
     * @param triangle the triangle to render
     */
    void renderTriangle(Triangle triangle);
    
    /**
     * Clear the rendering surface
     */
    void clear();
    
    /**
     * Display the rendered content
     */
    void display();
} 
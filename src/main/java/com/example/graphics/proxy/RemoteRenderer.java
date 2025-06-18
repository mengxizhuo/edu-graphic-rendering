package com.example.graphics.proxy;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

/**
 * Interface for remote rendering operations.
 * This is part of the Proxy pattern.
 */
public interface RemoteRenderer {
    /**
     * Render a circle remotely
     * @param circle the circle to render
     */
    void renderCircle(Circle circle);
    
    /**
     * Render a rectangle remotely
     * @param rectangle the rectangle to render
     */
    void renderRectangle(Rectangle rectangle);
    
    /**
     * Render a line remotely
     * @param line the line to render
     */
    void renderLine(Line line);
    
    /**
     * Render a triangle remotely
     * @param triangle the triangle to render
     */
    void renderTriangle(Triangle triangle);
    
    /**
     * Clear the remote rendering surface
     */
    void clear();
    
    /**
     * Display the rendered content remotely
     */
    void display();
} 
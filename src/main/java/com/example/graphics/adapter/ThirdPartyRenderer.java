package com.example.graphics.adapter;

/**
 * Mock interface for a third-party rendering library.
 * This represents an external API that we need to adapt.
 */
public interface ThirdPartyRenderer {
    /**
     * Draw a circle in the third-party renderer
     * @param centerX the x coordinate of the center
     * @param centerY the y coordinate of the center
     * @param radius the radius of the circle
     */
    void drawCircle(double centerX, double centerY, double radius);
    
    /**
     * Draw a rectangle in the third-party renderer
     * @param x the x coordinate of the top-left corner
     * @param y the y coordinate of the top-left corner
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    void drawRect(double x, double y, double width, double height);
    
    /**
     * Draw a line in the third-party renderer
     * @param startX the x coordinate of the start point
     * @param startY the y coordinate of the start point
     * @param endX the x coordinate of the end point
     * @param endY the y coordinate of the end point
     */
    void drawLine(double startX, double startY, double endX, double endY);
    
    /**
     * Draw a polygon in the third-party renderer
     * @param xPoints the x coordinates of the vertices
     * @param yPoints the y coordinates of the vertices
     * @param nPoints the number of vertices
     */
    void drawPolygon(int[] xPoints, int[] yPoints, int nPoints);
    
    /**
     * Clear the rendering surface
     */
    void clearSurface();
    
    /**
     * Update the display
     */
    void refreshDisplay();
} 
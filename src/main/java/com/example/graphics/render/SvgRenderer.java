package com.example.graphics.render;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

/**
 * SVG implementation of the Renderer interface.
 * Part of the Bridge pattern.
 */
public class SvgRenderer implements Renderer {
    private StringBuilder svgContent;
    private final int width;
    private final int height;
    
    /**
     * Constructor initializes the SVG renderer with specified dimensions
     * @param width the width of the SVG canvas
     * @param height the height of the SVG canvas
     */
    public SvgRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        clear();
    }
    
    @Override
    public void renderCircle(Circle circle) {
        svgContent.append(String.format(
            "<circle cx=\"%d\" cy=\"%d\" r=\"%d\" fill=\"none\" stroke=\"black\" stroke-width=\"1\" />\n",
            circle.getX(), circle.getY(), circle.getRadius()
        ));
    }
    
    @Override
    public void renderRectangle(Rectangle rectangle) {
        svgContent.append(String.format(
            "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"none\" stroke=\"black\" stroke-width=\"1\" />\n",
            rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight()
        ));
    }
    
    @Override
    public void renderLine(Line line) {
        svgContent.append(String.format(
            "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" stroke-width=\"1\" />\n",
            line.getX1(), line.getY1(), line.getX2(), line.getY2()
        ));
    }
    
    @Override
    public void renderTriangle(Triangle triangle) {
        svgContent.append(String.format(
            "<polygon points=\"%d,%d %d,%d %d,%d\" fill=\"none\" stroke=\"black\" stroke-width=\"1\" />\n",
            triangle.getX1(), triangle.getY1(),
            triangle.getX2(), triangle.getY2(),
            triangle.getX3(), triangle.getY3()
        ));
    }
    
    @Override
    public void clear() {
        svgContent = new StringBuilder();
        svgContent.append(String.format(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">\n",
            width, height
        ));
    }
    
    @Override
    public void display() {
        svgContent.append("</svg>");
        System.out.println("SVG Output:");
        System.out.println(svgContent.toString());
    }
    
    /**
     * Get the SVG content as a string
     * @return the SVG content
     */
    public String getSvgContent() {
        return svgContent.toString() + "</svg>";
    }
} 
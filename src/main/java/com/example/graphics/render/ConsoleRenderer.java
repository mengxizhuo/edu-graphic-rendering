package com.example.graphics.render;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

/**
 * Console text-based implementation of the Renderer interface.
 * Part of the Bridge pattern.
 */
public class ConsoleRenderer implements Renderer {
    private final int width;
    private final int height;
    private char[][] canvas;
    
    /**
     * Constructor initializes the console renderer with specified dimensions
     * @param width the width of the canvas
     * @param height the height of the canvas
     */
    public ConsoleRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        clear();
    }
    
    @Override
    public void renderCircle(Circle circle) {
        int x0 = circle.getX();
        int y0 = circle.getY();
        int radius = circle.getRadius();
        
        // Using Bresenham's circle algorithm
        int x = radius;
        int y = 0;
        int err = 0;
        
        while (x >= y) {
            drawPixel(x0 + x, y0 + y);
            drawPixel(x0 + y, y0 + x);
            drawPixel(x0 - y, y0 + x);
            drawPixel(x0 - x, y0 + y);
            drawPixel(x0 - x, y0 - y);
            drawPixel(x0 - y, y0 - x);
            drawPixel(x0 + y, y0 - x);
            drawPixel(x0 + x, y0 - y);
            
            y += 1;
            if (err <= 0) {
                err += 2 * y + 1;
            }
            if (err > 0) {
                x -= 1;
                err -= 2 * x + 1;
            }
        }
    }
    
    @Override
    public void renderRectangle(Rectangle rectangle) {
        int x = rectangle.getX();
        int y = rectangle.getY();
        int w = rectangle.getWidth();
        int h = rectangle.getHeight();
        
        // Draw horizontal lines
        for (int i = x; i < x + w; i++) {
            drawPixel(i, y);
            drawPixel(i, y + h - 1);
        }
        
        // Draw vertical lines
        for (int i = y; i < y + h; i++) {
            drawPixel(x, i);
            drawPixel(x + w - 1, i);
        }
    }
    
    @Override
    public void renderLine(Line line) {
        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();
        
        // Using Bresenham's line algorithm
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        
        while (true) {
            drawPixel(x1, y1);
            
            if (x1 == x2 && y1 == y2) break;
            
            int e2 = 2 * err;
            if (e2 > -dy) {
                if (x1 == x2) break;
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                if (y1 == y2) break;
                err += dx;
                y1 += sy;
            }
        }
    }
    
    @Override
    public void renderTriangle(Triangle triangle) {
        // 使用三条线来绘制三角形
        int x1 = triangle.getX1();
        int y1 = triangle.getY1();
        int x2 = triangle.getX2();
        int y2 = triangle.getY2();
        int x3 = triangle.getX3();
        int y3 = triangle.getY3();
        
        // 绘制第一条边
        drawLine(x1, y1, x2, y2);
        
        // 绘制第二条边
        drawLine(x2, y2, x3, y3);
        
        // 绘制第三条边
        drawLine(x3, y3, x1, y1);
    }
    
    @Override
    public void clear() {
        canvas = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                canvas[y][x] = ' ';
            }
        }
    }
    
    @Override
    public void display() {
        System.out.println("Console Renderer Output:");
        // Print top border
        System.out.print('+');
        for (int x = 0; x < width; x++) {
            System.out.print('-');
        }
        System.out.println('+');
        
        // Print canvas with borders
        for (int y = 0; y < height; y++) {
            System.out.print('|');
            for (int x = 0; x < width; x++) {
                System.out.print(canvas[y][x]);
            }
            System.out.println('|');
        }
        
        // Print bottom border
        System.out.print('+');
        for (int x = 0; x < width; x++) {
            System.out.print('-');
        }
        System.out.println('+');
    }
    
    /**
     * Draw a pixel at the specified coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    private void drawPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            canvas[y][x] = '*';
        }
    }
    
    /**
     * Draw a line between two points
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     */
    private void drawLine(int x1, int y1, int x2, int y2) {
        // Using Bresenham's line algorithm
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        
        while (true) {
            drawPixel(x1, y1);
            
            if (x1 == x2 && y1 == y2) break;
            
            int e2 = 2 * err;
            if (e2 > -dy) {
                if (x1 == x2) break;
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                if (y1 == y2) break;
                err += dx;
                y1 += sy;
            }
        }
    }
} 
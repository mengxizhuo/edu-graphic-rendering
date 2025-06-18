package com.example.graphics.factory;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Shape;
import com.example.graphics.model.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ShapeFactory class.
 */
public class ShapeFactoryTest {
    
    private ShapeFactory shapeFactory;
    
    @BeforeEach
    public void setUp() {
        shapeFactory = new ShapeFactory();
    }
    
    @Test
    public void testCreateCircle() {
        // Given
        int x = 100;
        int y = 100;
        int radius = 50;
        
        // When
        Shape shape = shapeFactory.createCircle(x, y, radius);
        
        // Then
        assertNotNull(shape);
        assertTrue(shape instanceof Circle);
        Circle circle = (Circle) shape;
        assertEquals(x, circle.getX());
        assertEquals(y, circle.getY());
        assertEquals(radius, circle.getRadius());
    }
    
    @Test
    public void testCreateRectangle() {
        // Given
        int x = 100;
        int y = 100;
        int width = 200;
        int height = 150;
        
        // When
        Shape shape = shapeFactory.createRectangle(x, y, width, height);
        
        // Then
        assertNotNull(shape);
        assertTrue(shape instanceof Rectangle);
        Rectangle rectangle = (Rectangle) shape;
        assertEquals(x, rectangle.getX());
        assertEquals(y, rectangle.getY());
        assertEquals(width, rectangle.getWidth());
        assertEquals(height, rectangle.getHeight());
    }
    
    @Test
    public void testCreateLine() {
        // Given
        int x1 = 100;
        int y1 = 100;
        int x2 = 200;
        int y2 = 200;
        
        // When
        Shape shape = shapeFactory.createLine(x1, y1, x2, y2);
        
        // Then
        assertNotNull(shape);
        assertTrue(shape instanceof Line);
        Line line = (Line) shape;
        assertEquals(x1, line.getX1());
        assertEquals(y1, line.getY1());
        assertEquals(x2, line.getX2());
        assertEquals(y2, line.getY2());
    }
    
    @Test
    public void testCreateTriangle() {
        // Given
        int x1 = 100;
        int y1 = 100;
        int x2 = 200;
        int y2 = 100;
        int x3 = 150;
        int y3 = 50;
        
        // When
        Shape shape = shapeFactory.createTriangle(x1, y1, x2, y2, x3, y3);
        
        // Then
        assertNotNull(shape);
        assertTrue(shape instanceof Triangle);
        Triangle triangle = (Triangle) shape;
        assertEquals(x1, triangle.getX1());
        assertEquals(y1, triangle.getY1());
        assertEquals(x2, triangle.getX2());
        assertEquals(y2, triangle.getY2());
        assertEquals(x3, triangle.getX3());
        assertEquals(y3, triangle.getY3());
    }
} 
package com.example.graphics;

import com.example.graphics.factory.ShapeFactory;
import com.example.graphics.model.Shape;
import com.example.graphics.render.Renderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * JUnit test class for the Drawing class.
 */
public class DrawingTest {
    private Drawing drawing;
    private ShapeFactory shapeFactory;
    private TestRenderer renderer;
    
    /**
     * Test renderer implementation for testing purposes
     */
    private static class TestRenderer implements Renderer {
        private int circleCount = 0;
        private int rectangleCount = 0;
        private int lineCount = 0;
        private int triangleCount = 0;       // 新增
        private boolean cleared = false;
        private boolean displayed = false;

        @Override
        public void renderCircle(com.example.graphics.model.Circle circle) {
            circleCount++;
        }

        @Override
        public void renderRectangle(com.example.graphics.model.Rectangle rectangle) {
            rectangleCount++;
        }

        @Override
        public void renderLine(com.example.graphics.model.Line line) {
            lineCount++;
        }

        // 新增对 renderTriangle 的实现
        @Override
        public void renderTriangle(com.example.graphics.model.Triangle triangle) {
            triangleCount++;
        }

        @Override
        public void clear() {
            cleared = true;
            circleCount = 0;
            rectangleCount = 0;
            lineCount = 0;
            triangleCount = 0;            // 重置
        }

        @Override
        public void display() {
            displayed = true;
        }

        public int getCircleCount() {
            return circleCount;
        }

        public int getRectangleCount() {
            return rectangleCount;
        }

        public int getLineCount() {
            return lineCount;
        }

        public int getTriangleCount() {
            return triangleCount;
        }

        public boolean isCleared() {
            return cleared;
        }

        public boolean isDisplayed() {
            return displayed;
        }
    }


    @BeforeEach
    public void setUp() {
        renderer = new TestRenderer();
        drawing = new Drawing(renderer);
        shapeFactory = new ShapeFactory();
    }
    
    @Test
    public void testAddShape() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        
        // When
        drawing.addShape(circle);
        
        // Then
        List<Shape> shapes = drawing.getShapes();
        assertEquals(1, shapes.size());
        assertSame(circle, shapes.get(0));
    }
    
    @Test
    public void testRemoveShape() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        drawing.addShape(circle);
        
        // When
        drawing.removeShape(circle);
        
        // Then
        List<Shape> shapes = drawing.getShapes();
        assertEquals(0, shapes.size());
    }
    
    @Test
    public void testMoveShape() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        drawing.addShape(circle);
        
        // When
        drawing.moveShape(circle, 200, 200);
        
        // Then
        assertEquals(200, circle.getX());
        assertEquals(200, circle.getY());
    }
    
    @Test
    public void testUndoRedo() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        drawing.addShape(circle);
        
        // When - Move and undo
        drawing.moveShape(circle, 200, 200);
        drawing.undo();
        
        // Then
        assertEquals(100, circle.getX());
        assertEquals(100, circle.getY());
        
        // When - Redo
        drawing.redo();
        
        // Then
        assertEquals(200, circle.getX());
        assertEquals(200, circle.getY());
    }
    
    @Test
    public void testRender() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        Shape rectangle = shapeFactory.createRectangle(200, 200, 150, 100);
        Shape line = shapeFactory.createLine(50, 50, 350, 350);
        
        drawing.addShape(circle);
        drawing.addShape(rectangle);
        drawing.addShape(line);
        
        // When
        drawing.render();
        
        // Then
        assertTrue(renderer.isCleared());
        assertTrue(renderer.isDisplayed());
        assertEquals(1, renderer.getCircleCount());
        assertEquals(1, renderer.getRectangleCount());
        assertEquals(1, renderer.getLineCount());
    }
} 
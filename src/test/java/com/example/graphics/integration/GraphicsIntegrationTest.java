package com.example.graphics.integration;

import com.example.graphics.Drawing;
import com.example.graphics.factory.ShapeFactory;
import com.example.graphics.model.Shape;
import com.example.graphics.render.ConsoleRenderer;
import com.example.graphics.render.Renderer;
import com.example.graphics.visitor.JsonExportVisitor;
import com.example.graphics.visitor.XmlExportVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the Graphics Rendering System.
 * Tests the interaction between multiple components.
 */
public class GraphicsIntegrationTest {
    
    private Drawing drawing;
    private ShapeFactory shapeFactory;
    private TestRenderer renderer;
    
    /**
     * Test renderer implementation for integration testing
     */
    private static class TestRenderer implements Renderer {
        private int shapeCount = 0;
        private boolean cleared = false;
        private boolean displayed = false;
        private int width;
        private int height;
        
        public TestRenderer(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public void renderCircle(com.example.graphics.model.Circle circle) {
            shapeCount++;
        }
        
        @Override
        public void renderRectangle(com.example.graphics.model.Rectangle rectangle) {
            shapeCount++;
        }
        
        @Override
        public void renderLine(com.example.graphics.model.Line line) {
            shapeCount++;
        }
        
        @Override
        public void renderTriangle(com.example.graphics.model.Triangle triangle) {
            shapeCount++;
        }
        
        @Override
        public void clear() {
            cleared = true;
            shapeCount = 0;
        }
        
        @Override
        public void display() {
            displayed = true;
        }
        
        @Override
        public int getWidth() {
            return width;
        }
        
        @Override
        public int getHeight() {
            return height;
        }
        
        public int getShapeCount() {
            return shapeCount;
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
        renderer = new TestRenderer(800, 600);
        drawing = new Drawing(renderer);
        shapeFactory = new ShapeFactory();
    }
    
    @Test
    public void testDrawingWithFactoryAndRenderer() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        Shape rectangle = shapeFactory.createRectangle(200, 200, 150, 100);
        Shape line = shapeFactory.createLine(50, 50, 350, 350);
        Shape triangle = shapeFactory.createTriangle(100, 100, 200, 100, 150, 50);
        
        // When
        drawing.addShape(circle);
        drawing.addShape(rectangle);
        drawing.addShape(line);
        drawing.addShape(triangle);
        drawing.render();
        
        // Then
        assertTrue(renderer.isCleared());
        assertTrue(renderer.isDisplayed());
        assertEquals(4, renderer.getShapeCount());
    }
    
    @Test
    public void testUndoRedoWithMultipleShapes() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        Shape rectangle = shapeFactory.createRectangle(200, 200, 150, 100);
        
        // When - Add shapes
        drawing.addShape(circle);
        drawing.addShape(rectangle);
        drawing.render();
        
        // Then
        assertEquals(2, drawing.getShapes().size());
        assertEquals(2, renderer.getShapeCount());
        
        // When - Undo adding rectangle
        drawing.undo();
        drawing.render();
        
        // Then
        assertEquals(1, drawing.getShapes().size());
        assertEquals(1, renderer.getShapeCount());
        
        // When - Redo adding rectangle
        drawing.redo();
        drawing.render();
        
        // Then
        assertEquals(2, drawing.getShapes().size());
        assertEquals(2, renderer.getShapeCount());
    }
    
    @Test
    public void testVisitorPatternIntegration() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        Shape rectangle = shapeFactory.createRectangle(200, 200, 150, 100);
        drawing.addShape(circle);
        drawing.addShape(rectangle);
        
        JsonExportVisitor jsonVisitor = new JsonExportVisitor();
        XmlExportVisitor xmlVisitor = new XmlExportVisitor();
        
        // When
        drawing.acceptVisitor(jsonVisitor);
        drawing.acceptVisitor(xmlVisitor);
        
        // Then
        String jsonOutput = jsonVisitor.getJsonOutput();
        String xmlOutput = xmlVisitor.getXmlOutput();
        
        assertNotNull(jsonOutput);
        assertNotNull(xmlOutput);
        assertTrue(jsonOutput.contains("\"type\": \"circle\""));
        assertTrue(jsonOutput.contains("\"type\": \"rectangle\""));
        assertTrue(xmlOutput.contains("<circle "));
        assertTrue(xmlOutput.contains("<rectangle "));
    }
    
    @Test
    public void testMoveShapeCommand() {
        // Given
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        drawing.addShape(circle);
        
        // When - Move shape
        drawing.moveShape(circle, 200, 200);
        
        // Then
        assertEquals(200, circle.getX());
        assertEquals(200, circle.getY());
        
        // When - Undo move
        drawing.undo();
        
        // Then
        assertEquals(100, circle.getX());
        assertEquals(100, circle.getY());
    }
} 
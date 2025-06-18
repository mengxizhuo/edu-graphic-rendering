package com.example.graphics.visitor;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ShapeVisitor implementations.
 */
public class ShapeVisitorTest {
    
    private JsonExportVisitor jsonVisitor;
    private XmlExportVisitor xmlVisitor;
    private Circle circle;
    private Rectangle rectangle;
    private Line line;
    private Triangle triangle;
    
    @BeforeEach
    public void setUp() {
        jsonVisitor = new JsonExportVisitor();
        xmlVisitor = new XmlExportVisitor();
        
        circle = new Circle(100, 100, 50);
        rectangle = new Rectangle(200, 200, 150, 100);
        line = new Line(50, 50, 350, 350);
        triangle = new Triangle(100, 100, 200, 100, 150, 50);
    }
    
    @Test
    public void testJsonExportVisitor() {
        // When
        circle.accept(jsonVisitor);
        rectangle.accept(jsonVisitor);
        line.accept(jsonVisitor);
        triangle.accept(jsonVisitor);
        
        // Then
        String jsonOutput = jsonVisitor.getJsonOutput();
        assertNotNull(jsonOutput);
        assertTrue(jsonOutput.contains("\"type\": \"circle\""));
        assertTrue(jsonOutput.contains("\"radius\": 50"));
        assertTrue(jsonOutput.contains("\"type\": \"rectangle\""));
        assertTrue(jsonOutput.contains("\"width\": 150"));
        assertTrue(jsonOutput.contains("\"height\": 100"));
        assertTrue(jsonOutput.contains("\"type\": \"line\""));
        assertTrue(jsonOutput.contains("\"x1\": 50"));
        assertTrue(jsonOutput.contains("\"y2\": 350"));
        assertTrue(jsonOutput.contains("\"type\": \"triangle\""));
        assertTrue(jsonOutput.contains("\"x3\": 150"));
        assertTrue(jsonOutput.contains("\"y3\": 50"));
    }
    
    @Test
    public void testXmlExportVisitor() {
        // When
        circle.accept(xmlVisitor);
        rectangle.accept(xmlVisitor);
        line.accept(xmlVisitor);
        triangle.accept(xmlVisitor);
        
        // Then
        String xmlOutput = xmlVisitor.getXmlOutput();
        assertNotNull(xmlOutput);
        assertTrue(xmlOutput.contains("<circle "));
        assertTrue(xmlOutput.contains("radius=\"50\""));
        assertTrue(xmlOutput.contains("<rectangle "));
        assertTrue(xmlOutput.contains("width=\"150\""));
        assertTrue(xmlOutput.contains("height=\"100\""));
        assertTrue(xmlOutput.contains("<line "));
        assertTrue(xmlOutput.contains("x1=\"50\""));
        assertTrue(xmlOutput.contains("y2=\"350\""));
        assertTrue(xmlOutput.contains("<triangle "));
        assertTrue(xmlOutput.contains("x3=\"150\""));
        assertTrue(xmlOutput.contains("y3=\"50\""));
    }
    
    @Test
    public void testClearJsonVisitor() {
        // Given
        circle.accept(jsonVisitor);
        rectangle.accept(jsonVisitor);
        
        // When
        jsonVisitor.clear();
        line.accept(jsonVisitor);
        
        // Then
        String jsonOutput = jsonVisitor.getJsonOutput();
        assertNotNull(jsonOutput);
        assertFalse(jsonOutput.contains("\"type\": \"circle\""));
        assertFalse(jsonOutput.contains("\"type\": \"rectangle\""));
        assertTrue(jsonOutput.contains("\"type\": \"line\""));
    }
    
    @Test
    public void testClearXmlVisitor() {
        // Given
        circle.accept(xmlVisitor);
        rectangle.accept(xmlVisitor);
        
        // When
        xmlVisitor.clear();
        line.accept(xmlVisitor);
        
        // Then
        String xmlOutput = xmlVisitor.getXmlOutput();
        assertNotNull(xmlOutput);
        assertFalse(xmlOutput.contains("<circle "));
        assertFalse(xmlOutput.contains("<rectangle "));
        assertTrue(xmlOutput.contains("<line "));
    }
} 
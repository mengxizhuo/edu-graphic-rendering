package com.example.graphics.factory;

import com.example.graphics.render.ConsoleRenderer;
import com.example.graphics.render.Renderer;
import com.example.graphics.render.SvgRenderer;
import com.example.graphics.render.SwingRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the RendererFactory implementations.
 */
public class RendererFactoryTest {
    
    @Test
    public void testConsoleRendererFactory() {
        // Given
        RendererFactory factory = new ConsoleRendererFactory();
        int width = 800;
        int height = 600;
        
        // When
        Renderer renderer = factory.createRenderer(width, height);
        
        // Then
        assertNotNull(renderer);
        assertTrue(renderer instanceof ConsoleRenderer);
        assertEquals(width, renderer.getWidth());
        assertEquals(height, renderer.getHeight());
    }
    
    @Test
    public void testSvgRendererFactory() {
        // Given
        RendererFactory factory = new SvgRendererFactory();
        int width = 800;
        int height = 600;
        
        // When
        Renderer renderer = factory.createRenderer(width, height);
        
        // Then
        assertNotNull(renderer);
        assertTrue(renderer instanceof SvgRenderer);
        assertEquals(width, renderer.getWidth());
        assertEquals(height, renderer.getHeight());
    }
    
    @Test
    public void testSwingRendererFactory() {
        // Given
        RendererFactory factory = new SwingRendererFactory();
        int width = 800;
        int height = 600;
        
        // When
        Renderer renderer = factory.createRenderer(width, height);
        
        // Then
        assertNotNull(renderer);
        assertTrue(renderer instanceof SwingRenderer);
        assertEquals(width, renderer.getWidth());
        assertEquals(height, renderer.getHeight());
    }
} 
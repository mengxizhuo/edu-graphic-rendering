package com.example.graphics.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the RenderingConfig singleton class.
 */
public class RenderingConfigTest {
    
    @Test
    public void testSingletonInstance() {
        // When
        RenderingConfig instance1 = RenderingConfig.getInstance();
        RenderingConfig instance2 = RenderingConfig.getInstance();
        
        // Then
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2); // Should be the same instance
    }
    
    @Test
    public void testDefaultValues() {
        // When
        RenderingConfig config = RenderingConfig.getInstance();
        
        // Then
        assertEquals(800, config.getDefaultWidth());
        assertEquals(600, config.getDefaultHeight());
        assertEquals("svg", config.getDefaultRendererType());
        assertFalse(config.isDebugMode());
    }
    
    @Test
    public void testSetValues() {
        // Given
        RenderingConfig config = RenderingConfig.getInstance();
        
        // When
        config.setDefaultWidth(1024);
        config.setDefaultHeight(768);
        config.setDefaultRendererType("swing");
        config.setDebugMode(true);
        
        // Then
        assertEquals(1024, config.getDefaultWidth());
        assertEquals(768, config.getDefaultHeight());
        assertEquals("swing", config.getDefaultRendererType());
        assertTrue(config.isDebugMode());
        
        // Reset for other tests
        config.setDefaultWidth(800);
        config.setDefaultHeight(600);
        config.setDefaultRendererType("svg");
        config.setDebugMode(false);
    }
    
    @Test
    public void testPersistenceAcrossInstances() {
        // Given
        RenderingConfig config1 = RenderingConfig.getInstance();
        config1.setDefaultWidth(1280);
        config1.setDefaultHeight(720);
        
        // When
        RenderingConfig config2 = RenderingConfig.getInstance();
        
        // Then
        assertEquals(1280, config2.getDefaultWidth());
        assertEquals(720, config2.getDefaultHeight());
        
        // Reset for other tests
        config1.setDefaultWidth(800);
        config1.setDefaultHeight(600);
    }
} 
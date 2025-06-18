package com.example.graphics.singleton;

/**
 * Singleton class for managing system-wide rendering configuration.
 * This is an implementation of the Singleton pattern.
 */
public class RenderingConfig {
    // The single instance of RenderingConfig
    private static RenderingConfig instance;
    
    // Configuration properties
    private int defaultWidth;
    private int defaultHeight;
    private String defaultRendererType;
    private boolean debugMode;
    
    /**
     * Private constructor to prevent instantiation from outside
     */
    private RenderingConfig() {
        // Default configuration values
        defaultWidth = 800;
        defaultHeight = 600;
        defaultRendererType = "svg";
        debugMode = false;
    }
    
    /**
     * Get the singleton instance of RenderingConfig
     * @return the singleton instance
     */
    public static synchronized RenderingConfig getInstance() {
        if (instance == null) {
            instance = new RenderingConfig();
        }
        return instance;
    }
    
    /**
     * Get the default width for renderers
     * @return the default width
     */
    public int getDefaultWidth() {
        return defaultWidth;
    }
    
    /**
     * Set the default width for renderers
     * @param defaultWidth the default width to set
     */
    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }
    
    /**
     * Get the default height for renderers
     * @return the default height
     */
    public int getDefaultHeight() {
        return defaultHeight;
    }
    
    /**
     * Set the default height for renderers
     * @param defaultHeight the default height to set
     */
    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
    }
    
    /**
     * Get the default renderer type
     * @return the default renderer type
     */
    public String getDefaultRendererType() {
        return defaultRendererType;
    }
    
    /**
     * Set the default renderer type
     * @param defaultRendererType the default renderer type to set
     */
    public void setDefaultRendererType(String defaultRendererType) {
        this.defaultRendererType = defaultRendererType;
    }
    
    /**
     * Check if debug mode is enabled
     * @return true if debug mode is enabled, false otherwise
     */
    public boolean isDebugMode() {
        return debugMode;
    }
    
    /**
     * Set the debug mode
     * @param debugMode true to enable debug mode, false to disable
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
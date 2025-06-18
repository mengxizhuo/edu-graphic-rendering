package com.example.graphics.factory;

import com.example.graphics.render.Renderer;

/**
 * Abstract Factory interface for creating renderers.
 * This is part of the Abstract Factory pattern.
 */
public interface RendererFactory {
    /**
     * Create a renderer with the specified dimensions
     * @param width the width of the rendering area
     * @param height the height of the rendering area
     * @return a new Renderer instance
     */
    Renderer createRenderer(int width, int height);
} 
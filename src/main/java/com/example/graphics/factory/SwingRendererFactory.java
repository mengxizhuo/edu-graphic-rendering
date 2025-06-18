package com.example.graphics.factory;

import com.example.graphics.render.Renderer;
import com.example.graphics.render.SwingRenderer;

/**
 * Factory for creating Swing-based renderers
 */
public class SwingRendererFactory implements RendererFactory {
    
    @Override
    public Renderer createRenderer(int width, int height) {
        return new SwingRenderer(width, height);
    }
} 
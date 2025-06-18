package com.example.graphics.factory;

import com.example.graphics.render.Renderer;
import com.example.graphics.render.SvgRenderer;

/**
 * Factory for creating SVG renderers.
 * This is a concrete implementation of the Abstract Factory pattern.
 */
public class SvgRendererFactory implements RendererFactory {
    @Override
    public Renderer createRenderer(int width, int height) {
        return new SvgRenderer(width, height);
    }
} 
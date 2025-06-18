package com.example.graphics.factory;

import com.example.graphics.render.ConsoleRenderer;
import com.example.graphics.render.Renderer;

/**
 * Factory for creating Console renderers.
 * This is a concrete implementation of the Abstract Factory pattern.
 */
public class ConsoleRendererFactory implements RendererFactory {
    @Override
    public Renderer createRenderer(int width, int height) {
        return new ConsoleRenderer(width, height);
    }
}
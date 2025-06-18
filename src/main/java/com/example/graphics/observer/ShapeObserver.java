package com.example.graphics.observer;

import com.example.graphics.model.Shape;

/**
 * Observer interface for the Observer pattern.
 * This allows objects to be notified of changes to shapes.
 */
public interface ShapeObserver {
    /**
     * Called when a shape is added
     * @param shape the shape that was added
     */
    void onShapeAdded(Shape shape);
    
    /**
     * Called when a shape is removed
     * @param shape the shape that was removed
     */
    void onShapeRemoved(Shape shape);
    
    /**
     * Called when a shape is modified
     * @param shape the shape that was modified
     */
    void onShapeModified(Shape shape);
} 
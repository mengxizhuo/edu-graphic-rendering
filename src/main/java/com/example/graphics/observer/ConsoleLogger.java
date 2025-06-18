package com.example.graphics.observer;

import com.example.graphics.model.Shape;

/**
 * Console logger implementation of the ShapeObserver interface.
 * This logs shape changes to the console.
 */
public class ConsoleLogger implements ShapeObserver {
    @Override
    public void onShapeAdded(Shape shape) {
        System.out.println("Shape added: " + shape.getClass().getSimpleName() + 
                           " at position (" + shape.getX() + "," + shape.getY() + ")");
    }
    
    @Override
    public void onShapeRemoved(Shape shape) {
        System.out.println("Shape removed: " + shape.getClass().getSimpleName() + 
                           " at position (" + shape.getX() + "," + shape.getY() + ")");
    }
    
    @Override
    public void onShapeModified(Shape shape) {
        System.out.println("Shape modified: " + shape.getClass().getSimpleName() + 
                           " at position (" + shape.getX() + "," + shape.getY() + ")");
    }
} 
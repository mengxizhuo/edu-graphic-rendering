package com.example.graphics.observer;

import com.example.graphics.model.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * Subject class for the Observer pattern.
 * This maintains a list of observers and notifies them of changes.
 */
public class ShapeSubject {
    private final List<ShapeObserver> observers;
    
    /**
     * Constructor initializes the observer list
     */
    public ShapeSubject() {
        observers = new ArrayList<>();
    }
    
    /**
     * Add an observer to be notified of changes
     * @param observer the observer to add
     */
    public void addObserver(ShapeObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    /**
     * Remove an observer from the notification list
     * @param observer the observer to remove
     */
    public void removeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Notify all observers that a shape was added
     * @param shape the shape that was added
     */
    public void notifyShapeAdded(Shape shape) {
        for (ShapeObserver observer : observers) {
            observer.onShapeAdded(shape);
        }
    }
    
    /**
     * Notify all observers that a shape was removed
     * @param shape the shape that was removed
     */
    public void notifyShapeRemoved(Shape shape) {
        for (ShapeObserver observer : observers) {
            observer.onShapeRemoved(shape);
        }
    }
    
    /**
     * Notify all observers that a shape was modified
     * @param shape the shape that was modified
     */
    public void notifyShapeModified(Shape shape) {
        for (ShapeObserver observer : observers) {
            observer.onShapeModified(shape);
        }
    }
} 
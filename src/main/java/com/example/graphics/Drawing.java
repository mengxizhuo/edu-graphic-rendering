package com.example.graphics;

import com.example.graphics.command.AddShapeCommand;
import com.example.graphics.command.CommandManager;
import com.example.graphics.command.MoveShapeCommand;
import com.example.graphics.command.RemoveShapeCommand;
import com.example.graphics.model.Shape;
import com.example.graphics.model.Triangle;
import com.example.graphics.observer.ShapeObserver;
import com.example.graphics.observer.ShapeSubject;
import com.example.graphics.render.Renderer;
import com.example.graphics.visitor.ShapeVisitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Main drawing class that manages shapes and rendering.
 * This class integrates the various design patterns.
 */
public class Drawing implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final List<Shape> shapes;
    private final CommandManager commandManager;
    private final ShapeSubject shapeSubject;
    private transient Renderer renderer;
    
    /**
     * Constructor initializes the drawing
     * @param renderer the renderer to use
     */
    public Drawing(Renderer renderer) {
        this.shapes = new ArrayList<>();
        this.commandManager = new CommandManager();
        this.shapeSubject = new ShapeSubject();
        this.renderer = renderer;
    }
    
    /**
     * Add a shape to the drawing using the Command pattern
     * @param shape the shape to add
     */
    public void addShape(Shape shape) {
        AddShapeCommand command = new AddShapeCommand(shapes, shape);
        commandManager.executeCommand(command);
        shapeSubject.notifyShapeAdded(shape);
    }
    
    /**
     * Remove a shape from the drawing using the Command pattern
     * @param shape the shape to remove
     */
    public void removeShape(Shape shape) {
        RemoveShapeCommand command = new RemoveShapeCommand(shapes, shape);
        commandManager.executeCommand(command);
        shapeSubject.notifyShapeRemoved(shape);
    }
    
    /**
     * Move a shape to a new position using the Command pattern
     * @param shape the shape to move
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void moveShape(Shape shape, int x, int y) {
        MoveShapeCommand command = new MoveShapeCommand(shape, x, y);
        commandManager.executeCommand(command);
        shapeSubject.notifyShapeModified(shape);
    }
    
    /**
     * Undo the last command
     * @return true if a command was undone, false if there are no commands to undo
     */
    public boolean undo() {
        return commandManager.undo();
    }
    
    /**
     * Redo the last undone command
     * @return true if a command was redone, false if there are no commands to redo
     */
    public boolean redo() {
        return commandManager.redo();
    }
    
    /**
     * Add an observer to be notified of shape changes
     * @param observer the observer to add
     */
    public void addObserver(ShapeObserver observer) {
        shapeSubject.addObserver(observer);
    }
    
    /**
     * Remove an observer from the notification list
     * @param observer the observer to remove
     */
    public void removeObserver(ShapeObserver observer) {
        shapeSubject.removeObserver(observer);
    }
    
    /**
     * Set the renderer to use
     * @param renderer the renderer to use
     */
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
    
    /**
     * Get the current renderer
     * @return the current renderer
     */
    public Renderer getRenderer() {
        return renderer;
    }
    
    /**
     * Get the list of shapes
     * @return the list of shapes
     */
    public List<Shape> getShapes() {
        return new ArrayList<>(shapes); // Return a copy to prevent direct modification
    }
    
    /**
     * Render all shapes using the current renderer
     */
    public void render() {
        renderer.clear();
        
        for (Shape shape : shapes) {
            if (shape instanceof com.example.graphics.model.Circle) {
                renderer.renderCircle((com.example.graphics.model.Circle) shape);
            } else if (shape instanceof com.example.graphics.model.Rectangle) {
                renderer.renderRectangle((com.example.graphics.model.Rectangle) shape);
            } else if (shape instanceof com.example.graphics.model.Line) {
                renderer.renderLine((com.example.graphics.model.Line) shape);
            } else if (shape instanceof com.example.graphics.model.Triangle) {
                renderer.renderTriangle((com.example.graphics.model.Triangle) shape);
            }
        }
        
        renderer.display();
    }
    
    /**
     * Apply a visitor to all shapes
     * @param visitor the visitor to apply
     */
    public void acceptVisitor(ShapeVisitor visitor) {
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
} 
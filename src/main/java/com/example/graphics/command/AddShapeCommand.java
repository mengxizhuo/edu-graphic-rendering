package com.example.graphics.command;

import com.example.graphics.model.Shape;
import java.util.List;

/**
 * Command for adding a shape to a collection.
 * This is an implementation of the Command pattern.
 */
public class AddShapeCommand implements Command {
    private final List<Shape> shapes;
    private final Shape shape;
    
    /**
     * Constructor for the AddShapeCommand
     * @param shapes the collection of shapes to modify
     * @param shape the shape to add
     */
    public AddShapeCommand(List<Shape> shapes, Shape shape) {
        this.shapes = shapes;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
        shapes.add(shape);
    }
    
    @Override
    public void undo() {
        shapes.remove(shape);
    }
}
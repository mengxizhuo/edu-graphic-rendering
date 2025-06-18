package com.example.graphics.command;

import com.example.graphics.model.Shape;
import java.util.List;

/**
 * Command for removing a shape from a collection.
 * This is an implementation of the Command pattern.
 */
public class RemoveShapeCommand implements Command {
    private final List<Shape> shapes;
    private final Shape shape;
    private int index;
    
    /**
     * Constructor for the RemoveShapeCommand
     * @param shapes the collection of shapes to modify
     * @param shape the shape to remove
     */
    public RemoveShapeCommand(List<Shape> shapes, Shape shape) {
        this.shapes = shapes;
        this.shape = shape;
    }
    
    @Override
    public void execute() {
        index = shapes.indexOf(shape);
        if (index != -1) {
            shapes.remove(index);
        }
    }
    
    @Override
    public void undo() {
        if (index != -1 && index <= shapes.size()) {
            shapes.add(index, shape);
        } else if (index == -1) {
            shapes.add(shape);
        }
    }
} 
package com.example.graphics.command;

import com.example.graphics.model.Shape;

/**
 * Command for moving a shape to a new position.
 * This is an implementation of the Command pattern.
 */
public class MoveShapeCommand implements Command {
    private final Shape shape;
    private final int newX;
    private final int newY;
    private final int oldX;
    private final int oldY;
    
    /**
     * Constructor for the MoveShapeCommand
     * @param shape the shape to move
     * @param newX the new x coordinate
     * @param newY the new y coordinate
     */
    public MoveShapeCommand(Shape shape, int newX, int newY) {
        this.shape = shape;
        this.newX = newX;
        this.newY = newY;
        this.oldX = shape.getX();
        this.oldY = shape.getY();
    }
    
    @Override
    public void execute() {
        shape.setPosition(newX, newY);
    }
    
    @Override
    public void undo() {
        shape.setPosition(oldX, oldY);
    }
}
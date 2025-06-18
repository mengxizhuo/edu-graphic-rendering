package com.example.graphics.command;

/**
 * Command interface for implementing the Command pattern.
 * This allows operations to be encapsulated as objects.
 */
public interface Command {
    /**
     * Execute the command
     */
    void execute();
    
    /**
     * Undo the command
     */
    void undo();
} 
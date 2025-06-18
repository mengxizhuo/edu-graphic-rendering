package com.example.graphics.command;

import java.util.Stack;

/**
 * Manager for executing, undoing, and redoing commands.
 * This is part of the Command pattern implementation.
 */
public class CommandManager {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    /**
     * Constructor initializes the command stacks
     */
    public CommandManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    /**
     * Execute a command and add it to the undo stack
     * @param command the command to execute
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack when a new command is executed
    }
    
    /**
     * Undo the most recently executed command
     * @return true if a command was undone, false if there are no commands to undo
     */
    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }
        
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
        return true;
    }
    
    /**
     * Redo the most recently undone command
     * @return true if a command was redone, false if there are no commands to redo
     */
    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
        return true;
    }
    
    /**
     * Check if there are commands that can be undone
     * @return true if there are commands that can be undone, false otherwise
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }
    
    /**
     * Check if there are commands that can be redone
     * @return true if there are commands that can be redone, false otherwise
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
    
    /**
     * Clear all command history
     */
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }
} 
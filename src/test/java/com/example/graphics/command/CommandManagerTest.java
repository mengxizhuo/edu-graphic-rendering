package com.example.graphics.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the CommandManager class.
 */
public class CommandManagerTest {
    
    private CommandManager commandManager;
    private Command mockCommand;
    
    @BeforeEach
    public void setUp() {
        commandManager = new CommandManager();
        mockCommand = Mockito.mock(Command.class);
    }
    
    @Test
    public void testExecuteCommand() {
        // When
        commandManager.executeCommand(mockCommand);
        
        // Then
        verify(mockCommand, times(1)).execute();
        assertTrue(commandManager.canUndo());
        assertFalse(commandManager.canRedo());
    }
    
    @Test
    public void testUndo() {
        // Given
        commandManager.executeCommand(mockCommand);
        reset(mockCommand); // Reset mock to clear the execute() call
        
        // When
        boolean result = commandManager.undo();
        
        // Then
        assertTrue(result);
        verify(mockCommand, times(1)).undo();
        assertFalse(commandManager.canUndo());
        assertTrue(commandManager.canRedo());
    }
    
    @Test
    public void testUndoEmptyStack() {
        // When
        boolean result = commandManager.undo();
        
        // Then
        assertFalse(result);
        verify(mockCommand, never()).undo();
    }
    
    @Test
    public void testRedo() {
        // Given
        commandManager.executeCommand(mockCommand);
        commandManager.undo();
        reset(mockCommand); // Reset mock to clear previous calls
        
        // When
        boolean result = commandManager.redo();
        
        // Then
        assertTrue(result);
        verify(mockCommand, times(1)).execute();
        assertTrue(commandManager.canUndo());
        assertFalse(commandManager.canRedo());
    }
    
    @Test
    public void testRedoEmptyStack() {
        // When
        boolean result = commandManager.redo();
        
        // Then
        assertFalse(result);
        verify(mockCommand, never()).execute();
    }
    
    @Test
    public void testExecuteAfterUndo() {
        // Given
        Command firstCommand = Mockito.mock(Command.class);
        Command secondCommand = Mockito.mock(Command.class);
        
        commandManager.executeCommand(firstCommand);
        commandManager.undo();
        
        // When
        commandManager.executeCommand(secondCommand);
        
        // Then
        assertTrue(commandManager.canUndo());
        assertFalse(commandManager.canRedo()); // Redo stack should be cleared
    }
    
    @Test
    public void testClearHistory() {
        // Given
        commandManager.executeCommand(mockCommand);
        commandManager.undo();
        
        // When
        commandManager.clearHistory();
        
        // Then
        assertFalse(commandManager.canUndo());
        assertFalse(commandManager.canRedo());
    }
} 
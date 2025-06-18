package com.example.graphics.proxy;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;
import com.example.graphics.render.Renderer;
import com.example.graphics.render.SwingRenderer;

/**
 * Proxy for remote rendering operations.
 * This implements both the RemoteRenderer interface and our local Renderer interface.
 * Implementation of the Proxy pattern.
 */
public class RemoteRendererProxy implements RemoteRenderer, Renderer {
    private RemoteRenderer remoteRenderer;
    private SwingRenderer localRenderer;
    private boolean connected;
    
    /**
     * Constructor initializes the proxy
     */
    public RemoteRendererProxy() {
        this.connected = false;
    }
    
    /**
     * 设置本地渲染器，用于在远程渲染的同时在本地GUI显示
     * @param localRenderer 本地渲染器
     */
    public void setLocalRenderer(SwingRenderer localRenderer) {
        this.localRenderer = localRenderer;
    }
    
    /**
     * Connect to the remote renderer
     * @return true if connection was successful, false otherwise
     */
    public boolean connect() {
        if (!connected) {
            try {
                System.out.println("Connecting to remote rendering service...");
                // Simulate connection setup
                Thread.sleep(500);
                remoteRenderer = new RemoteRendererImpl();
                connected = true;
                System.out.println("Connected to remote rendering service.");
                return true;
            } catch (Exception e) {
                System.err.println("Failed to connect to remote rendering service: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
    
    /**
     * Disconnect from the remote renderer
     */
    public void disconnect() {
        if (connected) {
            System.out.println("Disconnecting from remote rendering service...");
            // Simulate disconnection
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            remoteRenderer = null;
            connected = false;
            System.out.println("Disconnected from remote rendering service.");
        }
    }
    
    /**
     * Check if the proxy is connected to the remote renderer
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Ensure connection before performing operations
     * @throws IllegalStateException if not connected
     */
    private void ensureConnected() {
        if (!connected) {
            throw new IllegalStateException("Not connected to remote rendering service");
        }
    }
    
    @Override
    public void renderCircle(Circle circle) {
        ensureConnected();
        remoteRenderer.renderCircle(circle);
        
        // 同时在本地GUI显示
        if (localRenderer != null) {
            localRenderer.renderCircle(circle);
        }
    }
    
    @Override
    public void renderRectangle(Rectangle rectangle) {
        ensureConnected();
        remoteRenderer.renderRectangle(rectangle);
        
        // 同时在本地GUI显示
        if (localRenderer != null) {
            localRenderer.renderRectangle(rectangle);
        }
    }
    
    @Override
    public void renderLine(Line line) {
        ensureConnected();
        remoteRenderer.renderLine(line);
        
        // 同时在本地GUI显示
        if (localRenderer != null) {
            localRenderer.renderLine(line);
        }
    }
    
    @Override
    public void renderTriangle(Triangle triangle) {
        ensureConnected();
        remoteRenderer.renderTriangle(triangle);
        
        // 同时在本地GUI显示
        if (localRenderer != null) {
            localRenderer.renderTriangle(triangle);
        }
    }
    
    @Override
    public void clear() {
        ensureConnected();
        remoteRenderer.clear();
        
        // 同时在本地GUI清除
        if (localRenderer != null) {
            localRenderer.clear();
        }
    }
    
    @Override
    public void display() {
        ensureConnected();
        remoteRenderer.display();
        
        // 同时在本地GUI显示
        if (localRenderer != null) {
            localRenderer.display();
        }
    }
} 
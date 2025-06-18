package com.example.graphics.proxy;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

/**
 * Implementation of the RemoteRenderer interface.
 * This simulates a remote rendering service.
 */
public class RemoteRendererImpl implements RemoteRenderer {
    @Override
    public void renderCircle(Circle circle) {
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("======== 远程渲染服务 ========");
        System.out.println("远程: 渲染圆形 - 位置(" + circle.getX() + "," + 
                           circle.getY() + "), 半径: " + circle.getRadius());
        System.out.println("============================");
    }
    
    @Override
    public void renderRectangle(Rectangle rectangle) {
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("======== 远程渲染服务 ========");
        System.out.println("远程: 渲染矩形 - 位置(" + rectangle.getX() + "," + 
                           rectangle.getY() + "), 宽: " + rectangle.getWidth() + 
                           ", 高: " + rectangle.getHeight());
        System.out.println("============================");
    }
    
    @Override
    public void renderLine(Line line) {
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("======== 远程渲染服务 ========");
        System.out.println("远程: 渲染直线 - 从(" + line.getX1() + "," + 
                           line.getY1() + ")到(" + line.getX2() + "," + line.getY2() + ")");
        System.out.println("============================");
    }
    
    @Override
    public void renderTriangle(Triangle triangle) {
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("======== 远程渲染服务 ========");
        System.out.println("远程: 渲染三角形 - 顶点1(" + 
                           triangle.getX1() + "," + triangle.getY1() + "), 顶点2(" + 
                           triangle.getX2() + "," + triangle.getY2() + "), 顶点3(" + 
                           triangle.getX3() + "," + triangle.getY3() + ")");
        System.out.println("============================");
    }
    
    @Override
    public void clear() {
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("======== 远程渲染服务 ========");
        System.out.println("远程: 清除渲染区域");
        System.out.println("============================");
    }
    
    @Override
    public void display() {
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("======== 远程渲染服务 ========");
        System.out.println("远程: 显示渲染内容");
        System.out.println("============================");
    }
    
    /**
     * Simulate network delay for remote operations
     */
    private void simulateNetworkDelay() {
        try {
            // Simulate a delay between 100-300ms
            Thread.sleep((long) (100 + Math.random() * 200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 
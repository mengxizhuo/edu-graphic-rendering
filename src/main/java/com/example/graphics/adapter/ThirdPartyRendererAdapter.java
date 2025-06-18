package com.example.graphics.adapter;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;
import com.example.graphics.render.Renderer;

/**
 * Adapter for the ThirdPartyRenderer interface.
 * This adapts the third-party renderer to our Renderer interface.
 * Implementation of the Adapter pattern.
 */
public class ThirdPartyRendererAdapter implements Renderer {
    private final ThirdPartyRenderer thirdPartyRenderer;
    
    /**
     * Constructor takes the third-party renderer to adapt
     * @param thirdPartyRenderer the third-party renderer to adapt
     */
    public ThirdPartyRendererAdapter(ThirdPartyRenderer thirdPartyRenderer) {
        this.thirdPartyRenderer = thirdPartyRenderer;
    }
    
    @Override
    public void renderCircle(Circle circle) {
        thirdPartyRenderer.drawCircle(circle.getX(), circle.getY(), circle.getRadius());
    }
    
    @Override
    public void renderRectangle(Rectangle rectangle) {
        thirdPartyRenderer.drawRect(rectangle.getX(), rectangle.getY(), 
                                   rectangle.getWidth(), rectangle.getHeight());
    }
    
    @Override
    public void renderLine(Line line) {
        thirdPartyRenderer.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
    
    @Override
    public void renderTriangle(Triangle triangle) {
        // 使用第三方渲染器的多边形绘制功能来绘制三角形
        int[] xPoints = {triangle.getX1(), triangle.getX2(), triangle.getX3()};
        int[] yPoints = {triangle.getY1(), triangle.getY2(), triangle.getY3()};
        thirdPartyRenderer.drawPolygon(xPoints, yPoints, 3);
    }
    
    @Override
    public void clear() {
        thirdPartyRenderer.clearSurface();
    }
    
    @Override
    public void display() {
        thirdPartyRenderer.refreshDisplay();
    }
} 
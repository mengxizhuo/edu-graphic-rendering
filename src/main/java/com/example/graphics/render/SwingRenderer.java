package com.example.graphics.render;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing-based renderer implementation that renders shapes on a JPanel
 */
public class SwingRenderer implements Renderer {
    private final int width;
    private final int height;
    private final List<java.awt.Shape> shapes;
    private final List<Color> shapeColors;
    private JPanel renderPanel;

    public SwingRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        this.shapes = new ArrayList<>();
        this.shapeColors = new ArrayList<>();
    }

    @Override
    public void renderCircle(Circle circle) {
        shapes.add(new Ellipse2D.Double(
            circle.getX() - circle.getRadius(),
            circle.getY() - circle.getRadius(),
            circle.getRadius() * 2,
            circle.getRadius() * 2
        ));
        shapeColors.add(Color.BLUE);
        
        // 添加一个小点表示圆心
        shapes.add(new Ellipse2D.Double(
            circle.getX() - 2,
            circle.getY() - 2,
            4,
            4
        ));
        shapeColors.add(Color.RED);
    }

    @Override
    public void renderRectangle(Rectangle rectangle) {
        shapes.add(new java.awt.Rectangle(
            rectangle.getX(),
            rectangle.getY(),
            rectangle.getWidth(),
            rectangle.getHeight()
        ));
        shapeColors.add(Color.GREEN);
    }

    @Override
    public void renderLine(Line line) {
        shapes.add(new Line2D.Double(
            line.getX1(),
            line.getY1(),
            line.getX2(),
            line.getY2()
        ));
        shapeColors.add(Color.BLACK);
        
        // 添加两个小点表示线的端点
        shapes.add(new Ellipse2D.Double(
            line.getX1() - 3,
            line.getY1() - 3,
            6,
            6
        ));
        shapeColors.add(Color.RED);
        
        shapes.add(new Ellipse2D.Double(
            line.getX2() - 3,
            line.getY2() - 3,
            6,
            6
        ));
        shapeColors.add(Color.RED);
    }
    
    @Override
    public void renderTriangle(Triangle triangle) {
        // 创建三角形路径
        Path2D path = new Path2D.Double();
        path.moveTo(triangle.getX1(), triangle.getY1());
        path.lineTo(triangle.getX2(), triangle.getY2());
        path.lineTo(triangle.getX3(), triangle.getY3());
        path.closePath();
        
        shapes.add(path);
        shapeColors.add(Color.ORANGE);
        
        // 添加三个小点表示三角形的顶点
        shapes.add(new Ellipse2D.Double(
            triangle.getX1() - 3,
            triangle.getY1() - 3,
            6,
            6
        ));
        shapeColors.add(Color.RED);
        
        shapes.add(new Ellipse2D.Double(
            triangle.getX2() - 3,
            triangle.getY2() - 3,
            6,
            6
        ));
        shapeColors.add(Color.RED);
        
        shapes.add(new Ellipse2D.Double(
            triangle.getX3() - 3,
            triangle.getY3() - 3,
            6,
            6
        ));
        shapeColors.add(Color.RED);
    }

    @Override
    public void clear() {
        shapes.clear();
        shapeColors.clear();
        if (renderPanel != null) {
            renderPanel.repaint();
        }
    }

    @Override
    public void display() {
        if (renderPanel != null) {
            renderPanel.repaint();
        }
    }

    /**
     * Get the JPanel that displays the rendered shapes
     * @return the render panel
     */
    public JPanel getRenderPanel() {
        if (renderPanel == null) {
            renderPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // 绘制背景网格
                    drawGrid(g2d);
                    
                    // 绘制所有形状
                    for (int i = 0; i < shapes.size(); i++) {
                        g2d.setColor(shapeColors.get(i));
                        g2d.setStroke(new BasicStroke(2));
                        g2d.draw(shapes.get(i));
                    }
                }
                
                private void drawGrid(Graphics2D g2d) {
                    g2d.setColor(new Color(240, 240, 240));
                    g2d.setStroke(new BasicStroke(1));
                    
                    // 绘制水平线
                    for (int y = 0; y < height; y += 20) {
                        g2d.drawLine(0, y, width, y);
                    }
                    
                    // 绘制垂直线
                    for (int x = 0; x < width; x += 20) {
                        g2d.drawLine(x, 0, x, height);
                    }
                }
            };
            renderPanel.setPreferredSize(new Dimension(width, height));
            renderPanel.setBackground(Color.WHITE);
        }
        return renderPanel;
    }
} 
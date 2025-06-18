package com.example.graphics;

import com.example.graphics.factory.ShapeFactory;
import com.example.graphics.factory.SwingRendererFactory;
import com.example.graphics.model.Line;
import com.example.graphics.model.Shape;
import com.example.graphics.observer.ConsoleLogger;
import com.example.graphics.render.Renderer;
import com.example.graphics.render.SwingRenderer;
import com.example.graphics.singleton.RenderingConfig;
import com.example.graphics.util.FileManager;
import com.example.graphics.visitor.JsonExportVisitor;
import com.example.graphics.visitor.XmlExportVisitor;
import com.example.graphics.proxy.RemoteRendererProxy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Swing GUI application for the graphics rendering system
 */
public class SwingGraphicsApp extends JFrame {
    private final Drawing drawing;
    private final SwingRenderer localRenderer;
    private final RemoteRendererProxy remoteRenderer;
    private final ShapeFactory shapeFactory;
    private final FileManager fileManager;
    
    private Shape selectedShape;
    private String currentShapeType = "Circle";
    private Point dragStart;
    private boolean isDragging = false;
    private boolean isDrawingLine = false;
    private boolean isDrawingTriangle = false;
    private int triangleStage = 0;
    private int x1, y1, x2, y2;  // 用于存储线条和三角形的点
    
    private JLabel statusLabel;
    private JToggleButton remoteRenderingToggle;
    private boolean isRemoteRenderingEnabled = false;

    public SwingGraphicsApp() {
        super("图形渲染系统");
        
        // 获取配置
        RenderingConfig config = RenderingConfig.getInstance();
        
        // 创建工厂和渲染器
        SwingRendererFactory rendererFactory = new SwingRendererFactory();
        localRenderer = (SwingRenderer) rendererFactory.createRenderer(800, 600);
        
        // 创建远程渲染器代理
        remoteRenderer = new RemoteRendererProxy();
        remoteRenderer.setLocalRenderer(localRenderer); // 设置本地渲染器引用
        
        // 创建绘图对象，默认使用本地渲染器
        drawing = new Drawing(localRenderer);
        drawing.addObserver(new ConsoleLogger());
        
        // 创建形状工厂
        shapeFactory = new ShapeFactory();
        
        // 创建文件管理器
        fileManager = new FileManager();
        
        // 设置UI
        setupUI();
        
        // 设置窗口属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // 创建菜单栏
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        
        // 创建工具栏
        JToolBar toolBar = createToolBar();
        mainPanel.add(toolBar, BorderLayout.NORTH);
        
        // 创建渲染面板
        JPanel renderPanel = localRenderer.getRenderPanel();
        renderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        setupRenderPanelListeners(renderPanel);
        
        // 创建滚动面板
        JScrollPane scrollPane = new JScrollPane(renderPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 创建状态栏
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusLabel = new JLabel("就绪");
        statusBar.add(statusLabel);
        mainPanel.add(statusBar, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        
        // 添加一些初始形状作为示例
        addSampleShapes();
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // 文件菜单
        JMenu fileMenu = new JMenu("文件");
        
        JMenuItem newItem = new JMenuItem("新建");
        newItem.addActionListener(e -> newDrawing());
        fileMenu.add(newItem);
        
        JMenuItem openItem = new JMenuItem("打开...");
        openItem.addActionListener(e -> openDrawing());
        fileMenu.add(openItem);
        
        fileMenu.addSeparator();
        
        JMenuItem saveItem = new JMenuItem("保存...");
        saveItem.addActionListener(e -> saveDrawing());
        fileMenu.add(saveItem);
        
        JMenuItem saveJsonItem = new JMenuItem("导出为JSON...");
        saveJsonItem.addActionListener(e -> exportToJson());
        fileMenu.add(saveJsonItem);
        
        JMenuItem saveXmlItem = new JMenuItem("导出为XML...");
        saveXmlItem.addActionListener(e -> exportToXml());
        fileMenu.add(saveXmlItem);
        
        fileMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        
        menuBar.add(fileMenu);
        
        // 编辑菜单
        JMenu editMenu = new JMenu("编辑");
        
        JMenuItem undoItem = new JMenuItem("撤销");
        undoItem.addActionListener(e -> {
            if (drawing.undo()) {
                drawing.render();
                statusLabel.setText("已撤销操作");
            } else {
                statusLabel.setText("没有操作可撤销");
            }
        });
        editMenu.add(undoItem);
        
        JMenuItem redoItem = new JMenuItem("重做");
        redoItem.addActionListener(e -> {
            if (drawing.redo()) {
                drawing.render();
                statusLabel.setText("已重做操作");
            } else {
                statusLabel.setText("没有操作可重做");
            }
        });
        editMenu.add(redoItem);
        
        editMenu.addSeparator();
        
        JMenuItem clearItem = new JMenuItem("清除所有");
        clearItem.addActionListener(e -> {
            clearDrawing();
        });
        editMenu.add(clearItem);
        
        menuBar.add(editMenu);
        
        // 渲染菜单
        JMenu renderMenu = new JMenu("渲染");
        
        JMenuItem localRenderItem = new JMenuItem("本地渲染");
        localRenderItem.addActionListener(e -> switchToLocalRendering());
        renderMenu.add(localRenderItem);
        
        JMenuItem remoteRenderItem = new JMenuItem("远程渲染");
        remoteRenderItem.addActionListener(e -> switchToRemoteRendering());
        renderMenu.add(remoteRenderItem);
        
        menuBar.add(renderMenu);
        
        // 帮助菜单
        JMenu helpMenu = new JMenu("帮助");
        
        JMenuItem aboutItem = new JMenuItem("关于");
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "图形渲染系统\n版本 1.0\n\n使用Java Swing开发的图形渲染应用\n支持多种设计模式\nsxizhuo.cn\nmengxizhuo@gmail.com",
                "关于图形渲染系统",
                JOptionPane.INFORMATION_MESSAGE);
        });
        helpMenu.add(aboutItem);
        
        menuBar.add(helpMenu);
        
        return menuBar;
    }
    
    private void addSampleShapes() {
        // 添加一个圆形
        Shape circle = shapeFactory.createCircle(200, 150, 50);
        drawing.addShape(circle);
        
        // 添加一个矩形
        Shape rectangle = shapeFactory.createRectangle(400, 200, 150, 100);
        drawing.addShape(rectangle);
        
        // 添加一条线
        Shape line = shapeFactory.createLine(100, 300, 500, 400);
        drawing.addShape(line);
        
        // 添加一个三角形
        Shape triangle = shapeFactory.createTriangle(300, 100, 350, 200, 250, 200);
        drawing.addShape(triangle);
        
        // 刷新显示
        drawing.render();
        
        statusLabel.setText("已添加示例图形");
    }
    
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        // 形状选择按钮
        String[] shapeTypes = {"Circle", "Rectangle", "Line", "Triangle"};
        ButtonGroup shapeGroup = new ButtonGroup();
        
        for (String type : shapeTypes) {
            JToggleButton button = new JToggleButton(type);
            button.addActionListener(e -> {
                currentShapeType = type;
                statusLabel.setText("已选择: " + type);
                
                // 重置绘制状态
                isDrawingLine = false;
                isDrawingTriangle = false;
                triangleStage = 0;
            });
            shapeGroup.add(button);
            toolBar.add(button);
            
            // 默认选择圆形
            if ("Circle".equals(type)) {
                button.setSelected(true);
            }
        }
        
        toolBar.addSeparator();
        
        // 清除按钮
        JButton clearButton = new JButton("清除");
        clearButton.addActionListener(e -> clearDrawing());
        toolBar.add(clearButton);

        toolBar.addSeparator();
        
        // 渲染模式切换按钮
        remoteRenderingToggle = new JToggleButton("远程渲染");
        remoteRenderingToggle.addActionListener(e -> {
            if (remoteRenderingToggle.isSelected()) {
                switchToRemoteRendering();
            } else {
                switchToLocalRendering();
            }
        });
        toolBar.add(remoteRenderingToggle);
        
        return toolBar;
    }
    
    // 切换到本地渲染
    private void switchToLocalRendering() {
        if (isRemoteRenderingEnabled) {
            // 如果当前是远程渲染，则断开连接
            remoteRenderer.disconnect();
            
            // 切换到本地渲染器
            drawing.setRenderer(localRenderer);
            
            // 更新状态
            isRemoteRenderingEnabled = false;
            remoteRenderingToggle.setSelected(false);
            statusLabel.setText("【本地渲染模式】已切换到本地渲染");
            
            // 重新渲染
            drawing.render();
        }
    }
    
    // 切换到远程渲染
    private void switchToRemoteRendering() {
        if (!isRemoteRenderingEnabled) {
            // 尝试连接到远程渲染器
            boolean connected = remoteRenderer.connect();
            
            if (connected) {
                // 切换到远程渲染器
                drawing.setRenderer(remoteRenderer);
                
                // 更新状态
                isRemoteRenderingEnabled = true;
                remoteRenderingToggle.setSelected(true);
                statusLabel.setText("【远程渲染模式】已连接到远程渲染服务");
                
                // 重新渲染
                drawing.render();
            } else {
                // 连接失败，保持本地渲染
                remoteRenderingToggle.setSelected(false);
                statusLabel.setText("远程渲染连接失败，保持本地渲染模式");
                JOptionPane.showMessageDialog(this,
                    "无法连接到远程渲染服务",
                    "连接错误",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void setupRenderPanelListeners(JPanel renderPanel) {
        renderPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
                
                // 检查是否点击了现有形状
                for (Shape shape : drawing.getShapes()) {
                    if (isPointOnShape(shape, e.getPoint())) {
                        selectedShape = shape;
                        isDragging = true;
                        statusLabel.setText("已选中图形: " + getShapeTypeName(shape));
                        return;
                    }
                }
                
                // 如果没有点击现有形状，处理新形状创建
                if (currentShapeType.equals("Line")) {
                    if (!isDrawingLine) {
                        // 开始绘制线条
                        isDrawingLine = true;
                        x1 = e.getX();
                        y1 = e.getY();
                        statusLabel.setText("绘制线条: 已设置起点，请点击终点");
                    } else {
                        // 完成线条绘制
                        x2 = e.getX();
                        y2 = e.getY();
                        Shape line = shapeFactory.createLine(x1, y1, x2, y2);
                        drawing.addShape(line);
                        drawing.render();
                        isDrawingLine = false;
                        statusLabel.setText("已添加线条");
                    }
                } else if (currentShapeType.equals("Triangle")) {
                    if (triangleStage == 0) {
                        // 第一个点
                        x1 = e.getX();
                        y1 = e.getY();
                        triangleStage = 1;
                        isDrawingTriangle = true;
                        statusLabel.setText("绘制三角形: 已设置第一个顶点，请点击第二个顶点");
                    } else if (triangleStage == 1) {
                        // 第二个点
                        x2 = e.getX();
                        y2 = e.getY();
                        triangleStage = 2;
                        statusLabel.setText("绘制三角形: 已设置第二个顶点，请点击第三个顶点");
                    } else if (triangleStage == 2) {
                        // 第三个点，完成三角形
                        Shape triangle = shapeFactory.createTriangle(x1, y1, x2, y2, e.getX(), e.getY());
                        drawing.addShape(triangle);
                        drawing.render();
                        triangleStage = 0;
                        isDrawingTriangle = false;
                        statusLabel.setText("已添加三角形");
                    }
                } else {
                    // 创建其他形状
                    createNewShape(e.getPoint());
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                if (selectedShape != null) {
                    statusLabel.setText("已移动图形: " + getShapeTypeName(selectedShape));
                }
                selectedShape = null;
            }
        });
        
        renderPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && selectedShape != null) {
                    drawing.moveShape(selectedShape, e.getX(), e.getY());
                    statusLabel.setText("正在移动图形: " + getShapeTypeName(selectedShape));
                }
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {
                // 显示鼠标坐标
                statusLabel.setText("坐标: (" + e.getX() + ", " + e.getY() + ")");
            }
        });
    }
    
    private String getShapeTypeName(Shape shape) {
        if (shape instanceof com.example.graphics.model.Circle) {
            return "圆形";
        } else if (shape instanceof com.example.graphics.model.Rectangle) {
            return "矩形";
        } else if (shape instanceof com.example.graphics.model.Line) {
            return "直线";
        } else if (shape instanceof com.example.graphics.model.Triangle) {
            return "三角形";
        }
        return "图形";
    }
    
    private boolean isPointOnShape(Shape shape, Point point) {
        if (shape instanceof com.example.graphics.model.Circle) {
            com.example.graphics.model.Circle circle = (com.example.graphics.model.Circle) shape;
            double distance = Math.sqrt(
                Math.pow(point.x - circle.getX(), 2) + 
                Math.pow(point.y - circle.getY(), 2)
            );
            return distance <= circle.getRadius();
        } else if (shape instanceof com.example.graphics.model.Rectangle) {
            com.example.graphics.model.Rectangle rect = (com.example.graphics.model.Rectangle) shape;
            return point.x >= rect.getX() && point.x <= rect.getX() + rect.getWidth() &&
                   point.y >= rect.getY() && point.y <= rect.getY() + rect.getHeight();
        } else if (shape instanceof com.example.graphics.model.Line) {
            com.example.graphics.model.Line line = (com.example.graphics.model.Line) shape;
            double lineLength = Math.sqrt(
                Math.pow(line.getX2() - line.getX1(), 2) + 
                Math.pow(line.getY2() - line.getY1(), 2)
            );
            
            double d1 = Math.sqrt(
                Math.pow(point.x - line.getX1(), 2) + 
                Math.pow(point.y - line.getY1(), 2)
            );
            
            double d2 = Math.sqrt(
                Math.pow(point.x - line.getX2(), 2) + 
                Math.pow(point.y - line.getY2(), 2)
            );
            
            // 允许5个像素的误差
            return Math.abs(d1 + d2 - lineLength) <= 5;
        } else if (shape instanceof com.example.graphics.model.Triangle) {
            com.example.graphics.model.Triangle triangle = (com.example.graphics.model.Triangle) shape;
            
            // 使用重心坐标法判断点是否在三角形内
            int x1 = triangle.getX1();
            int y1 = triangle.getY1();
            int x2 = triangle.getX2();
            int y2 = triangle.getY2();
            int x3 = triangle.getX3();
            int y3 = triangle.getY3();
            
            double denominator = ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3));
            double a = ((y2 - y3) * (point.x - x3) + (x3 - x2) * (point.y - y3)) / denominator;
            double b = ((y3 - y1) * (point.x - x3) + (x1 - x3) * (point.y - y3)) / denominator;
            double c = 1 - a - b;
            
            // 如果重心坐标都在0到1之间，则点在三角形内
            return a >= 0 && a <= 1 && b >= 0 && b <= 1 && c >= 0 && c <= 1;
        }
        return false;
    }
    
    private void createNewShape(Point point) {
        Shape newShape = null;
        
        switch (currentShapeType) {
            case "Circle":
                newShape = shapeFactory.createCircle(point.x, point.y, 50);
                statusLabel.setText("已添加圆形");
                break;
            case "Rectangle":
                newShape = shapeFactory.createRectangle(point.x, point.y, 100, 80);
                statusLabel.setText("已添加矩形");
                break;
            // Line和Triangle在mousePressed中处理
        }
        
        if (newShape != null) {
            drawing.addShape(newShape);
            drawing.render();
        }
    }
    
    private void clearDrawing() {
        // 清除所有形状
        for (Shape shape : drawing.getShapes().toArray(new Shape[0])) {
            drawing.removeShape(shape);
        }
        drawing.render();
        statusLabel.setText("已清除所有图形");
    }
    
    private void newDrawing() {
        if (!drawing.getShapes().isEmpty()) {
            int option = JOptionPane.showConfirmDialog(this,
                "是否保存当前图形？",
                "新建",
                JOptionPane.YES_NO_CANCEL_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                saveDrawing();
            } else if (option == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        
        clearDrawing();
    }
    
    private void openDrawing() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("打开图形文件");
        
        // 添加文件过滤器
        FileNameExtensionFilter binFilter = new FileNameExtensionFilter("二进制文件 (*.bin)", "bin");
        FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("JSON文件 (*.json)", "json");
        fileChooser.addChoosableFileFilter(binFilter);
        fileChooser.addChoosableFileFilter(jsonFilter);
        fileChooser.setFileFilter(binFilter);  // 默认选择二进制文件
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            
            try {
                List<Shape> shapes;
                
                if (filePath.toLowerCase().endsWith(".json")) {
                    shapes = fileManager.loadDrawingJson(filePath);
                } else {
                    // 默认为二进制文件
                    shapes = fileManager.loadDrawingBinary(filePath);
                }
                
                // 清除当前图形
                clearDrawing();
                
                // 添加加载的图形
                for (Shape shape : shapes) {
                    drawing.addShape(shape);
                }
                
                drawing.render();
                statusLabel.setText("已加载图形文件: " + file.getName());
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "加载文件失败: " + e.getMessage(),
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("加载文件失败");
            }
        }
    }
    
    private void saveDrawing() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("保存图形文件");
        
        // 添加文件过滤器
        FileNameExtensionFilter binFilter = new FileNameExtensionFilter("二进制文件 (*.bin)", "bin");
        fileChooser.addChoosableFileFilter(binFilter);
        fileChooser.setFileFilter(binFilter);
        
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            
            // 确保文件有正确的扩展名
            if (!filePath.toLowerCase().endsWith(".bin")) {
                filePath += ".bin";
            }
            
            try {
                fileManager.saveDrawingBinary(drawing, filePath);
                statusLabel.setText("已保存图形文件: " + new File(filePath).getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                    "保存文件失败: " + e.getMessage(),
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("保存文件失败");
            }
        }
    }
    
    private void exportToJson() {
        JsonExportVisitor visitor = new JsonExportVisitor();
        drawing.acceptVisitor(visitor);
        saveToFile(visitor.getJsonOutput(), "json");
    }
    
    private void exportToXml() {
        XmlExportVisitor visitor = new XmlExportVisitor();
        drawing.acceptVisitor(visitor);
        saveToFile(visitor.getXmlOutput(), "xml");
    }
    
    private void saveToFile(String content, String extension) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("保存为" + extension.toUpperCase() + "文件");
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            
            // 确保文件有正确的扩展名
            if (!filePath.toLowerCase().endsWith("." + extension)) {
                filePath += "." + extension;
            }
            
            try {
                if (extension.equals("json")) {
                    fileManager.saveDrawingJson(content, filePath);
                } else if (extension.equals("xml")) {
                    fileManager.saveDrawingXml(content, filePath);
                } else {
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(content);
                    }
                }
                
                JOptionPane.showMessageDialog(this, 
                    "文件已保存至：" + filePath,
                    "保存成功", JOptionPane.INFORMATION_MESSAGE);
                statusLabel.setText("已导出为" + extension.toUpperCase() + "文件");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                    "保存文件失败：" + e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("导出失败");
            }
        }
    }
    
    public static void main(String[] args) {
        // 使用Swing线程
        SwingUtilities.invokeLater(SwingGraphicsApp::new);
    }
} 
package com.example.graphics.util;

import com.example.graphics.Drawing;
import com.example.graphics.factory.ShapeFactory;
import com.example.graphics.model.Shape;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理器类，用于保存和加载图形
 */
public class FileManager {
    private final ShapeFactory shapeFactory;
    
    /**
     * 构造函数
     */
    public FileManager() {
        this.shapeFactory = new ShapeFactory();
    }
    
    /**
     * 将图形保存为二进制文件
     * @param drawing 要保存的图形
     * @param filePath 文件路径
     * @throws IOException 如果保存失败
     */
    public void saveDrawingBinary(Drawing drawing, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // 创建一个新的列表，只包含形状
            List<Shape> shapes = new ArrayList<>(drawing.getShapes());
            oos.writeObject(shapes);
        }
    }
    
    /**
     * 从二进制文件加载图形
     * @param filePath 文件路径
     * @return 加载的形状列表
     * @throws IOException 如果加载失败
     * @throws ClassNotFoundException 如果类找不到
     */
    @SuppressWarnings("unchecked")
    public List<Shape> loadDrawingBinary(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Shape>) ois.readObject();
        }
    }
    
    /**
     * 将图形保存为JSON文件
     * @param jsonData JSON数据
     * @param filePath 文件路径
     * @throws IOException 如果保存失败
     */
    public void saveDrawingJson(String jsonData, String filePath) throws IOException {
        Files.write(Paths.get(filePath), jsonData.getBytes());
    }
    
    /**
     * 从JSON文件加载图形
     * @param filePath 文件路径
     * @return 加载的形状列表
     * @throws IOException 如果加载失败
     */
    public List<Shape> loadDrawingJson(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray shapesArray = jsonObject.getJSONArray("shapes");
        
        List<Shape> shapes = new ArrayList<>();
        
        for (int i = 0; i < shapesArray.length(); i++) {
            JSONObject shapeJson = shapesArray.getJSONObject(i);
            String type = shapeJson.getString("type");
            
            Shape shape = null;
            switch (type) {
                case "circle":
                    shape = shapeFactory.createCircle(
                        shapeJson.getInt("x"),
                        shapeJson.getInt("y"),
                        shapeJson.getInt("radius")
                    );
                    break;
                case "rectangle":
                    shape = shapeFactory.createRectangle(
                        shapeJson.getInt("x"),
                        shapeJson.getInt("y"),
                        shapeJson.getInt("width"),
                        shapeJson.getInt("height")
                    );
                    break;
                case "line":
                    shape = shapeFactory.createLine(
                        shapeJson.getInt("x1"),
                        shapeJson.getInt("y1"),
                        shapeJson.getInt("x2"),
                        shapeJson.getInt("y2")
                    );
                    break;
                case "triangle":
                    shape = shapeFactory.createTriangle(
                        shapeJson.getInt("x1"),
                        shapeJson.getInt("y1"),
                        shapeJson.getInt("x2"),
                        shapeJson.getInt("y2"),
                        shapeJson.getInt("x3"),
                        shapeJson.getInt("y3")
                    );
                    break;
            }
            
            if (shape != null) {
                shapes.add(shape);
            }
        }
        
        return shapes;
    }
    
    /**
     * 将图形保存为XML文件
     * @param xmlData XML数据
     * @param filePath 文件路径
     * @throws IOException 如果保存失败
     */
    public void saveDrawingXml(String xmlData, String filePath) throws IOException {
        Files.write(Paths.get(filePath), xmlData.getBytes());
    }
} 
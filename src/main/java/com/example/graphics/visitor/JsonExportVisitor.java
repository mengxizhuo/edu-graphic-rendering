package com.example.graphics.visitor;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Visitor implementation that exports shapes to JSON format.
 * This is an implementation of the Visitor pattern.
 */
public class JsonExportVisitor implements ShapeVisitor {
    private JSONArray shapes;
    
    /**
     * Constructor initializes the JSON array
     */
    public JsonExportVisitor() {
        shapes = new JSONArray();
    }
    
    @Override
    public void visit(Circle circle) {
        JSONObject circleJson = new JSONObject();
        circleJson.put("type", "circle");
        circleJson.put("x", circle.getX());
        circleJson.put("y", circle.getY());
        circleJson.put("radius", circle.getRadius());
        shapes.put(circleJson);
    }
    
    @Override
    public void visit(Rectangle rectangle) {
        JSONObject rectJson = new JSONObject();
        rectJson.put("type", "rectangle");
        rectJson.put("x", rectangle.getX());
        rectJson.put("y", rectangle.getY());
        rectJson.put("width", rectangle.getWidth());
        rectJson.put("height", rectangle.getHeight());
        shapes.put(rectJson);
    }
    
    @Override
    public void visit(Line line) {
        JSONObject lineJson = new JSONObject();
        lineJson.put("type", "line");
        lineJson.put("x1", line.getX1());
        lineJson.put("y1", line.getY1());
        lineJson.put("x2", line.getX2());
        lineJson.put("y2", line.getY2());
        shapes.put(lineJson);
    }
    
    @Override
    public void visit(Triangle triangle) {
        JSONObject triangleJson = new JSONObject();
        triangleJson.put("type", "triangle");
        triangleJson.put("x1", triangle.getX1());
        triangleJson.put("y1", triangle.getY1());
        triangleJson.put("x2", triangle.getX2());
        triangleJson.put("y2", triangle.getY2());
        triangleJson.put("x3", triangle.getX3());
        triangleJson.put("y3", triangle.getY3());
        shapes.put(triangleJson);
    }
    
    /**
     * Get the JSON representation of all visited shapes
     * @return a JSON string representing all shapes
     */
    public String getJsonOutput() {
        JSONObject result = new JSONObject();
        result.put("shapes", shapes);
        return result.toString(2);  // Pretty print with 2-space indentation
    }
    
    /**
     * Clear all shapes from the visitor
     */
    public void clear() {
        shapes = new JSONArray();
    }
}
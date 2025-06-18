package com.example.graphics.visitor;

import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Triangle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

/**
 * Visitor implementation that exports shapes to XML format.
 * This is an implementation of the Visitor pattern.
 */
public class XmlExportVisitor implements ShapeVisitor {
    private Document document;
    private Element rootElement;
    
    /**
     * Constructor initializes the XML document
     */
    public XmlExportVisitor() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // Root elements
            document = docBuilder.newDocument();
            rootElement = document.createElement("shapes");
            document.appendChild(rootElement);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error initializing XML document", e);
        }
    }
    
    @Override
    public void visit(Circle circle) {
        Element circleElement = document.createElement("circle");
        circleElement.setAttribute("x", String.valueOf(circle.getX()));
        circleElement.setAttribute("y", String.valueOf(circle.getY()));
        circleElement.setAttribute("radius", String.valueOf(circle.getRadius()));
        rootElement.appendChild(circleElement);
    }
    
    @Override
    public void visit(Rectangle rectangle) {
        Element rectElement = document.createElement("rectangle");
        rectElement.setAttribute("x", String.valueOf(rectangle.getX()));
        rectElement.setAttribute("y", String.valueOf(rectangle.getY()));
        rectElement.setAttribute("width", String.valueOf(rectangle.getWidth()));
        rectElement.setAttribute("height", String.valueOf(rectangle.getHeight()));
        rootElement.appendChild(rectElement);
    }
    
    @Override
    public void visit(Line line) {
        Element lineElement = document.createElement("line");
        lineElement.setAttribute("x1", String.valueOf(line.getX1()));
        lineElement.setAttribute("y1", String.valueOf(line.getY1()));
        lineElement.setAttribute("x2", String.valueOf(line.getX2()));
        lineElement.setAttribute("y2", String.valueOf(line.getY2()));
        rootElement.appendChild(lineElement);
    }
    
    @Override
    public void visit(Triangle triangle) {
        Element triangleElement = document.createElement("triangle");
        triangleElement.setAttribute("x1", String.valueOf(triangle.getX1()));
        triangleElement.setAttribute("y1", String.valueOf(triangle.getY1()));
        triangleElement.setAttribute("x2", String.valueOf(triangle.getX2()));
        triangleElement.setAttribute("y2", String.valueOf(triangle.getY2()));
        triangleElement.setAttribute("x3", String.valueOf(triangle.getX3()));
        triangleElement.setAttribute("y3", String.valueOf(triangle.getY3()));
        rootElement.appendChild(triangleElement);
    }
    
    /**
     * Get the XML representation of all visited shapes
     * @return an XML string representing all shapes
     */
    public String getXmlOutput() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.toString();
        } catch (TransformerException e) {
            throw new RuntimeException("Error transforming XML document", e);
        }
    }
    
    /**
     * Clear all shapes and reset the visitor
     */
    public void clear() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // Root elements
            document = docBuilder.newDocument();
            rootElement = document.createElement("shapes");
            document.appendChild(rootElement);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error resetting XML document", e);
        }
    }
}
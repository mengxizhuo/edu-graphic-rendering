package com.example.graphics;

import com.example.graphics.adapter.MockThirdPartyRenderer;
import com.example.graphics.adapter.ThirdPartyRendererAdapter;
import com.example.graphics.factory.ConsoleRendererFactory;
import com.example.graphics.factory.RendererFactory;
import com.example.graphics.factory.ShapeFactory;
import com.example.graphics.factory.SvgRendererFactory;
import com.example.graphics.model.Circle;
import com.example.graphics.model.Line;
import com.example.graphics.model.Rectangle;
import com.example.graphics.model.Shape;
import com.example.graphics.observer.ConsoleLogger;
import com.example.graphics.proxy.RemoteRendererProxy;
import com.example.graphics.render.Renderer;
import com.example.graphics.singleton.RenderingConfig;
import com.example.graphics.visitor.JsonExportVisitor;
import com.example.graphics.visitor.XmlExportVisitor;

/**
 * Main class that demonstrates the graphics rendering system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Graphics Rendering System Demonstration");
        System.out.println("======================================");
        
        // Get the singleton configuration
        RenderingConfig config = RenderingConfig.getInstance();
        System.out.println("Using configuration: " + config.getDefaultWidth() + "x" + 
                          config.getDefaultHeight() + ", renderer: " + 
                          config.getDefaultRendererType());
        
        // Create factories (Abstract Factory pattern)
        RendererFactory svgFactory = new SvgRendererFactory();
        RendererFactory consoleFactory = new ConsoleRendererFactory();
        
        // Create a renderer using the factory
        Renderer svgRenderer = svgFactory.createRenderer(config.getDefaultWidth(), 
                                                       config.getDefaultHeight());
        
        // Create a drawing with the SVG renderer
        Drawing drawing = new Drawing(svgRenderer);
        
        // Add a console logger observer (Observer pattern)
        drawing.addObserver(new ConsoleLogger());
        
        // Create shapes using the factory (Factory pattern)
        ShapeFactory shapeFactory = new ShapeFactory();
        
        System.out.println("\nCreating and adding shapes...");
        Shape circle = shapeFactory.createCircle(100, 100, 50);
        Shape rectangle = shapeFactory.createRectangle(200, 200, 150, 100);
        Shape line = shapeFactory.createLine(50, 50, 350, 350);
        
        // Add shapes using commands (Command pattern)
        drawing.addShape(circle);
        drawing.addShape(rectangle);
        drawing.addShape(line);
        
        // Render the drawing
        System.out.println("\nRendering with SVG renderer:");
        drawing.render();
        
        // Switch to console renderer (Bridge pattern)
        System.out.println("\nSwitching to console renderer...");
        drawing.setRenderer(consoleFactory.createRenderer(40, 20));
        drawing.render();
        
        // Use the adapter (Adapter pattern)
        System.out.println("\nSwitching to third-party renderer via adapter...");
        MockThirdPartyRenderer thirdPartyRenderer = new MockThirdPartyRenderer();
        Renderer adapterRenderer = new ThirdPartyRendererAdapter(thirdPartyRenderer);
        drawing.setRenderer(adapterRenderer);
        drawing.render();
        
        // Use the proxy (Proxy pattern)
        System.out.println("\nSwitching to remote renderer via proxy...");
        RemoteRendererProxy remoteProxy = new RemoteRendererProxy();
        if (remoteProxy.connect()) {
            drawing.setRenderer(remoteProxy);
            drawing.render();
            remoteProxy.disconnect();
        }
        
        // Demonstrate command operations
        System.out.println("\nMoving the circle...");
        drawing.moveShape(circle, 150, 150);
        
        // Switch back to SVG renderer
        drawing.setRenderer(svgRenderer);
        drawing.render();
        
        System.out.println("\nUndoing the move...");
        drawing.undo();
        drawing.render();
        
        System.out.println("\nRedoing the move...");
        drawing.redo();
        drawing.render();
        
        // Use visitors to export (Visitor pattern)
        System.out.println("\nExporting to JSON:");
        JsonExportVisitor jsonVisitor = new JsonExportVisitor();
        drawing.acceptVisitor(jsonVisitor);
        System.out.println(jsonVisitor.getJsonOutput());
        
        System.out.println("\nExporting to XML:");
        XmlExportVisitor xmlVisitor = new XmlExportVisitor();
        drawing.acceptVisitor(xmlVisitor);
        System.out.println(xmlVisitor.getXmlOutput());
        
        System.out.println("\nDemonstration complete.");
    }
} 
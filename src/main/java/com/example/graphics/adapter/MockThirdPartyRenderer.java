package com.example.graphics.adapter;

/**
 * Mock implementation of the ThirdPartyRenderer interface.
 * This simulates a third-party rendering library.
 */
public class MockThirdPartyRenderer implements ThirdPartyRenderer {
    @Override
    public void drawCircle(double centerX, double centerY, double radius) {
        System.out.println("ThirdParty: Drawing circle at (" + centerX + "," + centerY + 
                           ") with radius " + radius);
    }
    
    @Override
    public void drawRect(double x, double y, double width, double height) {
        System.out.println("ThirdParty: Drawing rectangle at (" + x + "," + y + 
                           ") with width " + width + " and height " + height);
    }
    
    @Override
    public void drawLine(double startX, double startY, double endX, double endY) {
        System.out.println("ThirdParty: Drawing line from (" + startX + "," + startY + 
                           ") to (" + endX + "," + endY + ")");
    }
    
    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        System.out.print("ThirdParty: Drawing polygon with points: ");
        for (int i = 0; i < nPoints; i++) {
            System.out.print("(" + xPoints[i] + "," + yPoints[i] + ")");
            if (i < nPoints - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    @Override
    public void clearSurface() {
        System.out.println("ThirdParty: Clearing surface");
    }
    
    @Override
    public void refreshDisplay() {
        System.out.println("ThirdParty: Refreshing display");
    }
} 
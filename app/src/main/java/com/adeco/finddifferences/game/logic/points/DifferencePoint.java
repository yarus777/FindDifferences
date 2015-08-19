package com.adeco.finddifferences.game.logic.points;


public class DifferencePoint {
    private int x;
    private int y;
    private int radius;

    public DifferencePoint(double x, double y, double radius) {
        this.x = (int) x;
        this.y = (int) y;
        this.radius = (int) radius;
    }

    public boolean Check(int x, int y) {
        return GetDistance(x, y, this.x, this.y) <= radius;
    }

    private static double GetDistance(double x, double y, double x1, double y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    public DifferencePoint scale(double scaleFactor) {
        return new DifferencePoint(x * scaleFactor, y * scaleFactor, radius * scaleFactor);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}

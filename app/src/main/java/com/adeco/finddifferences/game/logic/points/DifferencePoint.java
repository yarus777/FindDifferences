package com.adeco.finddifferences.game.logic.points;


public class DifferencePoint {
    private int x;
    private int y;
    private int radius;

    public DifferencePoint(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean Check(int x, int y) {
        return GetDistance(x, y, this.x, this.y) <= radius;
    }

    private static double GetDistance(int x, int y, int x1, int y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    public DifferencePoint scale(double scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        radius *= scaleFactor;
        return this;
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

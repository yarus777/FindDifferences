package com.adeco.finddifferences.game.logic.points;


import android.graphics.Canvas;
import android.graphics.Point;


public abstract class AbstractPoint {
    private int x;
    private int y;

    public AbstractPoint(int x, int y) {
        this.x = x;
        this.y = y;

    }

    protected int getX() {return x;}
    protected int getY() {return y;}


    public abstract void draw(Canvas canvas, Point offset);

    public boolean match(DifferencePoint point) {
        return x == point.getX() && y == point.getY();
    }
}

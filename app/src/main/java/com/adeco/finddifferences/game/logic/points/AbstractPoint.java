package com.adeco.finddifferences.game.logic.points;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.Game;


public abstract class AbstractPoint {
    private int x;
    private int y;

    public AbstractPoint(int x, int y) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = paint;

    }

    protected int getX() {return x;}
    protected int getY() {return y;}


    public abstract void draw(Canvas canvas, Point offset);

    public boolean match(DifferencePoint point) {
        return x == point.getX() && y == point.getY();
    }
}

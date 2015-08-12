package com.adeco.finddifferences.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by agorbach on 12.08.2015.
 */
public class DifferencePoint implements Drawable {
    private int x;
    private int y;
    private int radius;
    private boolean isFound;
    private Paint mPaint;

    public DifferencePoint(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isFound = false;
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    public boolean Find(int x, int y) {
        if (GetDistance(x, y, this.x, this.y) <= radius) {
            isFound = true;
        }
        return isFound;
    }

    private static double GetDistance(int x, int y, int x1, int y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    @Override
    public void draw(Canvas canvas) {
        if (!isFound) {
            return;
        }
        canvas.drawCircle(x, y, radius, mPaint);
    }

    public DifferencePoint scale(double scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        radius *= scaleFactor;
        return this;
    }
}

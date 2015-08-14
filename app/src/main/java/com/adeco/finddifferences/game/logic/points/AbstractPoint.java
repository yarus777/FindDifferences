package com.adeco.finddifferences.game.logic.points;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by agorbach on 14.08.2015.
 */
public abstract class AbstractPoint {
    private int x;
    private int y;
    private int radius;
    private Paint paint;

    public AbstractPoint(int x, int y, int radius, Paint paint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = paint;
    }

    public void draw(Canvas canvas, Point offset) {
        canvas.drawCircle(x + offset.x, y + offset.y, radius, paint);
    }

    public boolean match(DifferencePoint point) {
        return x == point.getX() && y == point.getY();
    }
}

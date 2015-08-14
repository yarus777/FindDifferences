package com.adeco.finddifferences.game.logic.points;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.adeco.finddifferences.game.Drawable;

/**
 * Created by agorbach on 14.08.2015.
 */
public abstract class AbstractPoint implements Drawable {
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

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public AbstractPoint scale(double scaleFactor) {
        x *= scaleFactor;
        y *= scaleFactor;
        radius *= scaleFactor;
        return this;
    }

    public boolean match(DifferencePoint point) {
        return x == point.getX() && y == point.getY();
    }
}

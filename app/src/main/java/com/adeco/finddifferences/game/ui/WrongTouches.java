package com.adeco.finddifferences.game.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.adeco.finddifferences.game.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agorbach on 12.08.2015.
 */
public class WrongTouches implements Drawable {
    private int radius;
    private List<Point> points;
    private Paint paint;

    public WrongTouches(int radius) {
        points = new ArrayList<>();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        this.radius = radius;
    }

    public void add(int x, int y) {
        points.add(new Point(x, y));
    }

    @Override
    public void draw(Canvas canvas) {
        for (Point p : points) {
            canvas.drawCircle(p.x, p.y, radius, paint);
        }
    }
}

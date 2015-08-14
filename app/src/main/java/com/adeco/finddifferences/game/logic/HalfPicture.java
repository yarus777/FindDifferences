package com.adeco.finddifferences.game.logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.logic.points.RightPoint;

import java.util.List;


public class HalfPicture implements TouchHandler {
    private Point startPoint;
    private Bitmap back;
    private Paint paint;

    public HalfPicture(Point startPoint, Bitmap back, Paint paint) {
        this.startPoint = startPoint;
        this.back = back;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas, List<AbstractPoint> diffs) {
        canvas.drawBitmap(back, startPoint.x, startPoint.y, paint);
        for (AbstractPoint point : diffs) {
            point.draw(canvas, startPoint);
        }
    }

    @Override
    public AbstractPoint getDifference(DifferencePoint[] points, int realX, int realY) {
        int x = realX - startPoint.x;
        int y = realY - startPoint.y;
        for (DifferencePoint point : points) {
            if (point.Check(x, y)) {
                return new RightPoint(point);
            }
        }
        return null;
    }
}

package com.adeco.finddifferences.game.logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.Touchable;

/**
 * Created by agorbach on 14.08.2015.
 */
public class HalfPicture implements Drawable, Touchable {
    private Point startPoint;
    private Bitmap back;
    private DifferenceLayer difLayer;
    private Paint paint;

    public HalfPicture(Point startPoint, Bitmap back, DifferenceLayer difLayer, Paint paint) {
        this.startPoint = startPoint;
        this.back = back;
        this.paint = paint;
        this.difLayer = difLayer;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(back, startPoint.x, startPoint.y, paint);
    }

    @Override
    public void onTouch(MotionEvent event) {
        int x = (int) event.getX() - startPoint.x;
        int y = (int) event.getY() - startPoint.y;
        difLayer.touch(x, y);
    }

    public boolean touched(int x, int y) {
        return x >= startPoint.x && x <= startPoint.x + back.getWidth() &&
                y >= startPoint.y && y <= startPoint.y + back.getHeight();
    }
}

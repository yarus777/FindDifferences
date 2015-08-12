package com.adeco.finddifferences.game.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.Touchable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agorbach on 12.08.2015.
 */
public class DifferenceLayer implements Touchable, Drawable {
    private List<Point> touches;
    private Paint mPaint;

    public DifferenceLayer() {
        touches = new ArrayList<>();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Point p : touches) {
            canvas.drawCircle(p.x, p.y, 40, mPaint);
        }
    }

    @Override
    public void onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touches.add(new Point((int) event.getX(), (int) event.getY()));
        }
    }
}

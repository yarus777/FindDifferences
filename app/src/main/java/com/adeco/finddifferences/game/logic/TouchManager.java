package com.adeco.finddifferences.game.logic;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.Touchable;
import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;

import java.util.ArrayList;
import java.util.List;


public class TouchManager implements Touchable, Drawable {
    private TouchHandler top;
    private TouchHandler bottom;
    private DifferencePoint[] points;

    private List<AbstractPoint> diffs;

    public TouchManager(DifferencePoint[] points, TouchHandler top, TouchHandler bottom) {
        this.top = top;
        this.bottom = bottom;
        diffs = new ArrayList<>();
        this.points = points;
    }

    @Override
    public void onTouch(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                AbstractPoint touch = top.getDifference(points, x, y);
                if (touch == null) {
                    touch = bottom.getDifference(points, x, y);
                }
                if (touch != null) {
                    diffs.add(touch);
                }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        top.draw(canvas, diffs);
        bottom.draw(canvas, diffs);
    }
}

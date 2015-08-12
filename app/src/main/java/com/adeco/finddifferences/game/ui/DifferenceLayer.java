package com.adeco.finddifferences.game.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.DifferencePoint;
import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.Touchable;

/**
 * Created by agorbach on 12.08.2015.
 */
public class DifferenceLayer implements Touchable, Drawable {
    private DifferencePoint[] diffs;


    public DifferenceLayer(DifferencePoint[] diffs, double scaleFactor) {
        this.diffs = new DifferencePoint[diffs.length];
        for(int i=0; i<diffs.length; i++){
            this.diffs[i] = diffs[i].scale(scaleFactor);
        }
        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        for (DifferencePoint dif : diffs) {
            dif.draw(canvas);
        }
    }

    @Override
    public void onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (DifferencePoint dif : diffs) {
                    dif.Find((int) event.getX(), (int) event.getY());
                }
        }
    }
}

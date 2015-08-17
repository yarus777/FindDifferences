package com.adeco.finddifferences.game.logic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.Touchable;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.statistics.StatisticHandler;


public class PictureLayer implements Drawable, Touchable {
    private TouchManager touchManager;

    public PictureLayer(Bitmap img1, Bitmap img2, DifferencePoint[] diffs, StatisticHandler statisticHandler) {
        Paint paint = new Paint();
        TouchHandler top = new HalfPicture(new Point(0, 0), img1, paint);
        TouchHandler bottom = new HalfPicture(new Point(0, img1.getHeight()), img2, paint);
        touchManager = new TouchManager(diffs, top, bottom, statisticHandler);
    }

    @Override
    public void draw(Canvas canvas) {
        touchManager.draw(canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {
        touchManager.onTouch(event);
    }
}
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
public class PictureLayer implements Drawable, Touchable {
    private HalfPicture top;
    private HalfPicture bottom;

    public PictureLayer(Bitmap img1, Bitmap img2, DifferenceLayer difLayer) {
        Paint paint = new Paint();
        this.top = new HalfPicture(new Point(0, 0), img1, difLayer, paint);
        this.bottom = new HalfPicture(new Point(0, img1.getHeight()), img2, difLayer, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        top.draw(canvas);
        bottom.draw(canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                top.onTouch(event);
                bottom.onTouch(event);
        }
    }
}

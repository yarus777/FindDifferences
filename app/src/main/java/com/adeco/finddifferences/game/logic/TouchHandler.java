package com.adeco.finddifferences.game.logic;

import android.view.MotionEvent;

import com.adeco.finddifferences.game.Touchable;

/**
 * Created by agorbach on 14.08.2015.
 */
public class TouchHandler implements Touchable {
    private HalfPicture top;
    private HalfPicture bottom;

    public TouchHandler(HalfPicture top, HalfPicture bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}

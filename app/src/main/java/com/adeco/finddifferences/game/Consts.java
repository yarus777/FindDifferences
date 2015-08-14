package com.adeco.finddifferences.game;

import android.graphics.Color;
import android.graphics.Paint;


public class Consts {
    public final static Paint difPaint;
    public final static Paint wrongPaint;
    public static final int wrongRadius = 30;

    static {
        difPaint = new Paint();
        difPaint.setColor(Color.GREEN);
        difPaint.setStyle(Paint.Style.STROKE);
        difPaint.setStrokeWidth(2);

        wrongPaint = new Paint();
        wrongPaint.setColor(Color.RED);
        wrongPaint.setStyle(Paint.Style.STROKE);
        wrongPaint.setStrokeWidth(2);
    }
}

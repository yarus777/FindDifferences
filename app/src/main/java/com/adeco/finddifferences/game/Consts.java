package com.adeco.finddifferences.game;

import android.graphics.Color;
import android.graphics.Paint;


public class Consts {
    public final static Paint difPaint;
    public final static Paint wrongPaint;
    private static final int wrongRadius = 28;

    static {
        difPaint = new Paint();
        difPaint.setColor(Color.GREEN);
        difPaint.setStyle(Paint.Style.STROKE);
        difPaint.setStrokeWidth(4);

        wrongPaint = new Paint();
        wrongPaint.setColor(Color.RED);
        wrongPaint.setStyle(Paint.Style.STROKE);
        wrongPaint.setStrokeWidth(4);
    }

    private static double scalefactor = 1;

    public static void applyScale(double scaleFactor) {
        scalefactor = scaleFactor;
    }

    public static int getWrongRadius() {
        return (int) (scalefactor*wrongRadius);
    }
}

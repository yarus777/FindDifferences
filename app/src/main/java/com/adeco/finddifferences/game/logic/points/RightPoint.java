package com.adeco.finddifferences.game.logic.points;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.adeco.finddifferences.game.Consts;
import com.adeco.finddifferences.game.Game;


public class RightPoint extends BitmapPoint {
	private Bitmap bitmap;
	private int width;
	private int height;
	private Paint paint;

    public RightPoint(DifferencePoint dif) {
        super(dif.getX(), dif.getY(), dif.getRadius(), Game.getInstance().getDifImg(), Consts.difPaint);
    }

}

package com.adeco.finddifferences.game.logic.points;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.adeco.finddifferences.game.Consts;
import com.adeco.finddifferences.game.Game;


public class WrongPoint extends AbstractPoint {
    public WrongPoint(int x, int y) {
        super(x, y, Consts.wrongRadius, Consts.wrongPaint);
    }

    public void draw(Canvas canvas, Point offset) {


         Bitmap dif_img = Bitmap.createScaledBitmap(Game.getInstance().getWrongImg(), Consts.wrongRadius*2, Consts.wrongRadius*2,false);
        int dif_img_width = dif_img.getWidth();
        int dif_img_height = dif_img.getHeight();
        Consts.wrongPaint.setAlpha(70);
        canvas.drawBitmap(dif_img, getX() - dif_img_width / 2 + offset.x, getY() - dif_img_height / 2 + offset.y, Consts.wrongPaint);
    }



}

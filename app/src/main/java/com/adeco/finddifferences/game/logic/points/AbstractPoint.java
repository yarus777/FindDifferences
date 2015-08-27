package com.adeco.finddifferences.game.logic.points;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.Game;


public abstract class AbstractPoint {
    private int x;
    private int y;
    private int radius;
    private Paint paint;

    public AbstractPoint(int x, int y, int radius, Paint paint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = paint;

    }


    public void draw(Canvas canvas, Point offset) {
        //canvas.drawCircle(x + offset.x, y + offset.y, radius, paint);
        Bitmap dif_img = Bitmap.createScaledBitmap(Game.getInstance().getDifImg(),radius*2,radius*2,false);
        int dif_img_width = dif_img.getWidth();
        int dif_img_height = dif_img.getHeight();
        canvas.drawBitmap(dif_img, x-dif_img_width/2 + offset.x, y-dif_img_height/2 + offset.y, paint);
    }

    public boolean match(DifferencePoint point) {
        return x == point.getX() && y == point.getY();
    }

}

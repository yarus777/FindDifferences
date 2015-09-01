package com.adeco.finddifferences.game.logic.points;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.adeco.finddifferences.game.Consts;
import com.adeco.finddifferences.game.Game;


public class WrongPoint extends BitmapPoint {
    private static final int START_ALPHA = 255;
    private static final int MAX_ALPHA = 0;

    private int alpha;
    public WrongPoint(int x, int y) {
        super(x, y, Consts.wrongRadius, Game.getInstance().getWrongImg(), new Paint());
        alpha = START_ALPHA;
        paint.setAlpha(alpha);
    }

    public void draw(Canvas canvas, Point offset) {
        super.draw(canvas, offset);
        if (alpha>MAX_ALPHA) {
            alpha--;
            paint.setAlpha(alpha);
        }
    }
}

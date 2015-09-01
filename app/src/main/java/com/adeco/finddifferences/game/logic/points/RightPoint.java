package com.adeco.finddifferences.game.logic.points;

import com.adeco.finddifferences.game.Consts;


public class RightPoint extends AbstractPoint {
	private Bitmap bitmap;
	private int width;
	private int height;
	private Paint paint;

    public RightPoint(DifferencePoint dif) {
        super(dif.getX(), dif.getY(), dif.getRadius(), Game.getInstance().getDifImg(), Consts.difPaint);
    }
}

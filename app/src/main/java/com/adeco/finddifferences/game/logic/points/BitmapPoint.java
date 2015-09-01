package com.adeco.finddifferences.game.logic.points;

import com.adeco.finddifferences.game.Consts;


public abstract class BitmapPoint extends AbstractPoint {
	private Bitmap bitmap;
	private int width;
	private int height;
	protected Paint paint;

    public BitmapPoint(int x, int y, int radius, Bitmap img, Paint paint) {
        super(x, y);
        this.paint = paint;
        this.bitmap = Bitmap.createScaledBitmap(img, radius*2, radius*2, false);
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
    }

	@Override
    public void draw(Canvas canvas, Point offset){
        canvas.drawBitmap(bitmap, getX() - width/2 + offset.x, getY() - height/2 + offset.y, paint);
    }
}
package com.adeco.finddifferences.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.adeco.finddifferences.game.levels.Level;
import com.adeco.finddifferences.game.levels.LevelStorage;
import com.adeco.finddifferences.game.ui.DifferenceLayer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by agorbach on 12.08.2015.
 */
public class Game implements Drawable, Touchable {
    private LevelStorage levelStorage;
    private DifferenceLayer difLayer;

    private Paint mPaint;
    private Bitmap scaledBitmap, scaledBitmap2, img1, img2;
    private int scrwidth;
    private int scaledHeight;
    private int width;
    private int height;

    public Game(Context context) {
        AssetManager assetManager = context.getAssets();
        levelStorage = new LevelStorage(assetManager);
        difLayer = new DifferenceLayer();
        Level level = levelStorage.GetCurrentLevel();
        mPaint = new Paint();

        img1 = getBitmapFromAsset(assetManager, level.getImg1());
        img2 = getBitmapFromAsset(assetManager, level.getImg2());
        width = img1.getWidth();
        height = img1.getHeight();


        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        scrwidth = display.getWidth();
        scaledHeight = scrwidth * height / width;
        scaledBitmap = Bitmap.createScaledBitmap(img1, scrwidth, scaledHeight, false);
        scaledBitmap2 = Bitmap.createScaledBitmap(img2, scrwidth, scaledHeight, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(scaledBitmap, 0, 0, mPaint);
        canvas.drawBitmap(scaledBitmap2, 0, scaledHeight, mPaint);
        difLayer.draw(canvas);
    }

    public static Bitmap getBitmapFromAsset(AssetManager mgr, String filePath) {
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = mgr.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            System.out.println(e);
        }

        return bitmap;
    }

    @Override
    public void onTouch(MotionEvent event) {
        difLayer.onTouch(event);
    }
}

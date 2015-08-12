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
    private Bitmap img1;
    private Bitmap img2;
    private int width;
    private int height;

    public Game(Context context) {
        AssetManager assetManager = context.getAssets();
        levelStorage = new LevelStorage(assetManager);
        Level level = levelStorage.GetCurrentLevel();

        mPaint = new Paint();

        Bitmap img1raw = getBitmapFromAsset(assetManager, level.getImg1());
        Bitmap img2raw = getBitmapFromAsset(assetManager, level.getImg2());
        int imgWidth = img1raw.getWidth();
        int imgHeight = img1raw.getHeight();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        width = display.getWidth();
        double scaleFactor = (double) width / imgWidth;
        height = (int) (scaleFactor * imgHeight);
        img1 = Bitmap.createScaledBitmap(img1raw, width, height, false);
        img2 = Bitmap.createScaledBitmap(img2raw, width, height, false);
        difLayer = new DifferenceLayer(level.getDiffs(), scaleFactor);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(img1, 0, 0, mPaint);
        canvas.drawBitmap(img2, 0, height, mPaint);
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

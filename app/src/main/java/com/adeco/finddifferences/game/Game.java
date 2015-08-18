package com.adeco.finddifferences.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.adeco.finddifferences.game.levels.Level;
import com.adeco.finddifferences.game.levels.LevelStorage;
import com.adeco.finddifferences.game.logic.PictureLayer;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.popups.Popups;
import com.adeco.finddifferences.game.states.StateController;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

import java.io.IOException;
import java.io.InputStream;

public class Game implements Drawable, Touchable {

    private static Game instance;

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private LevelStorage levelStorage;

    private Bitmap img1;
    private Bitmap img2;

    private PictureLayer pictureLayer;
    private StateController stateController;

    public void init(Context context, StatisticHandler statisticHandler, DifferenceFoundHandler differenceFoundHandler, Popups popupsController) {
        AssetManager assetManager = context.getAssets();
        levelStorage = new LevelStorage(assetManager);
        Level level = levelStorage.GetCurrentLevel();

        Bitmap img1raw = getBitmapFromAsset(assetManager, level.getImg1());
        Bitmap img2raw = getBitmapFromAsset(assetManager, level.getImg2());
        int imgWidth = img1raw.getWidth();
        int imgHeight = img1raw.getHeight();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        double scaleFactor = (double) width / imgWidth;
        int height = (int) (scaleFactor * imgHeight);
        img1 = Bitmap.createScaledBitmap(img1raw, width, height, false);
        img2 = Bitmap.createScaledBitmap(img2raw, width, height, false);

        DifferencePoint[] diffs = level.getDiffs();
        DifferencePoint[] scaledDiffs = new DifferencePoint[diffs.length];
        for (int i = 0; i < diffs.length; i++) {
            scaledDiffs[i] = diffs[i].scale(scaleFactor);
        }
        stateController = new StateController();
        stateController.addHandler(popupsController);
        pictureLayer = new PictureLayer(img1, img2, scaledDiffs, new StatisticHandler[]{statisticHandler, stateController}, new DifferenceFoundHandler[]{differenceFoundHandler});
    }

    public void draw(Canvas canvas) {
        pictureLayer.draw(canvas);
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
        pictureLayer.onTouch(event);
    }

    public LevelStorage getLevelStorage() {
        return levelStorage;
    }
}

package com.adeco.finddifferences.game;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.adeco.finddifferences.GameActivity;
import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.interfaces.Destroyable;
import com.adeco.finddifferences.game.interfaces.DifferenceFoundHandler;
import com.adeco.finddifferences.game.interfaces.Drawable;
import com.adeco.finddifferences.game.interfaces.Touchable;
import com.adeco.finddifferences.game.levels.Level;
import com.adeco.finddifferences.game.levels.LevelStorage;
import com.adeco.finddifferences.game.logic.PictureLayer;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.popups.PopupController;
import com.adeco.finddifferences.game.score.ScoreController;
import com.adeco.finddifferences.game.settings.Settings;
import com.adeco.finddifferences.game.startedlevel.LevelStarted;
import com.adeco.finddifferences.game.states.StateController;
import com.adeco.finddifferences.game.statistics.StatisticHandler;
import com.adeco.finddifferences.utils.Graphics;

import java.io.IOException;
import java.io.InputStream;

public class Game extends Application implements Drawable, Touchable, Destroyable {
    public static final String PREFS_NAME = "shared_prefs";
    private AssetManager assetManager;
    private LevelStorage levelStorage;
    private SharedPreferences preferences;

   @Override
   public  void onCreate() {
        assetManager = getAssets();
        preferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Log.d("MY_TAG", "a " + assetManager + "p " + preferences);
        levelStorage = new LevelStorage(assetManager, preferences);
        settings = new Settings();
        levelStarted = new LevelStarted();
        Graphics.init(assetManager);
    }

   /* public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }*/

    private Settings settings;
    private PopupController popupController;
    private LevelStarted levelStarted;
    private ScoreController scoreController;

    private Bitmap img1;
    private Bitmap img2;

    private PictureLayer pictureLayer;

    public void start(StatisticHandler statisticHandler, DifferenceFoundHandler differenceFoundHandler, PopupController popupController, GameActivity activity) {
        this.popupController = popupController;
        Context context = getApplicationContext();

        AssetManager assetManager = getAssets();

        levelStarted.init(this);

        DifferenceFoundHandler[] differenceHandlers = new DifferenceFoundHandler[]{differenceFoundHandler};

        Level level = levelStorage.getCurrentLevel();

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


        StateController stateController = new StateController(levelStorage);
        stateController.addHandler(popupController);

        scoreController = new ScoreController(activity);
        levelStorage.setScoreController(scoreController);

        pictureLayer = new PictureLayer(img1, img2, scaledDiffs, new StatisticHandler[]{statisticHandler, scoreController, stateController}, differenceHandlers);
        stateController.addHandler(levelStorage);

        stateController.addHandler(pictureLayer);

        stateController.addHandler(levelStarted);
        stateController.addHandler(activity);

        stateController.start();
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

    public Settings getSettings() {
        return settings;
    }

    @Override
    public void onTouch(MotionEvent event) {
        pictureLayer.onTouch(event);
    }

    public LevelStorage getLevelStorage() {
        return levelStorage;
    }

    @Override
    public void onDestroy() {
        levelStorage.onDestroy();
    }

    public PopupController getPopupController() {
        return popupController;
    }

    public ScoreController getScoreController() {
        return scoreController;
    }
}

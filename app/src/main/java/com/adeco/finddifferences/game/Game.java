package com.adeco.finddifferences.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.adeco.finddifferences.GameActivity;
import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.interfaces.Destroyable;
import com.adeco.finddifferences.game.interfaces.DifferenceFoundHandler;
import com.adeco.finddifferences.game.interfaces.Drawable;
import com.adeco.finddifferences.game.interfaces.TimeCounter;
import com.adeco.finddifferences.game.interfaces.Touchable;
import com.adeco.finddifferences.game.levels.Level;
import com.adeco.finddifferences.game.levels.LevelStorage;
import com.adeco.finddifferences.game.logic.PictureLayer;
import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.logic.points.RightPoint;
import com.adeco.finddifferences.game.popups.PopupController;
import com.adeco.finddifferences.game.score.ScoreController;
import com.adeco.finddifferences.game.score.ScoreHandler;
import com.adeco.finddifferences.game.settings.Settings;
import com.adeco.finddifferences.game.startedlevel.LevelStarted;
import com.adeco.finddifferences.game.states.StateController;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

import java.io.IOException;
import java.io.InputStream;

public class Game implements Drawable, Touchable, Destroyable {

    private static Game instance;

    private Game() {
        levelStorage = new LevelStorage();
        settings = new Settings();
        levelStarted = new LevelStarted();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }


    private LevelStorage levelStorage;
    private SharedPreferences preferences;
    private Settings settings;
    private PopupController popupController;
    private LevelStarted levelStarted;
    private ScoreController scoreController;


    private Bitmap img1;
    private Bitmap img2;
    private Bitmap dif_img;

    private PictureLayer pictureLayer;

    public void start(Context context, SharedPreferences preferences, StatisticHandler statisticHandler, DifferenceFoundHandler differenceFoundHandler, PopupController popupController, GameActivity activity) {
        this.preferences = preferences;
        this.popupController = popupController;

        AssetManager assetManager = context.getAssets();
        levelStorage.load(assetManager, preferences);
        levelStarted.setContext(context);

        DifferenceFoundHandler[] differenceHandlers = new DifferenceFoundHandler[]{differenceFoundHandler};

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

        Resources res = context.getResources();
        dif_img = BitmapFactory.decodeResource(res, R.drawable.circle);

        DifferencePoint[] diffs = level.getDiffs();
        DifferencePoint[] scaledDiffs = new DifferencePoint[diffs.length];
        for (int i = 0; i < diffs.length; i++) {
            scaledDiffs[i] = diffs[i].scale(scaleFactor);
        }


        StateController stateController = new StateController();
        stateController.addHandler(popupController);

        scoreController = new ScoreController(activity);

        pictureLayer = new PictureLayer(img1, img2, scaledDiffs, new StatisticHandler[]{statisticHandler, scoreController, stateController}, differenceHandlers);

        stateController.addHandler(pictureLayer);

        stateController.addHandler(levelStarted);
        stateController.addHandler(activity);

        stateController.start();
    }

    public void draw(Canvas canvas) {
        pictureLayer.draw(canvas);
    }

    private static Bitmap getBitmapFromAsset(AssetManager mgr, String filePath) {
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

    public Bitmap getDifImg() {
        return dif_img;
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

    public SharedPreferences getPreferences() {
        return preferences;
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

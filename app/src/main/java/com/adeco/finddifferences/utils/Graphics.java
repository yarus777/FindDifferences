package com.adeco.finddifferences.utils;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

import com.adeco.finddifferences.game.Game;

public class Graphics {
    private static final String WRONG_IMG_PATH = "ui/miss.png";
    private static final String DIFF_IMG_PATH = "ui/circle.png";

    public static Bitmap wrongImg;
    public static Bitmap difImg;

    public static void init(AssetManager assetManager) {

        wrongImg = Game.getBitmapFromAsset(assetManager, WRONG_IMG_PATH);
        difImg = Game.getBitmapFromAsset(assetManager, DIFF_IMG_PATH);
    }
}

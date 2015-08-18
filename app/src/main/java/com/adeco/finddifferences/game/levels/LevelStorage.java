package com.adeco.finddifferences.game.levels;

import android.content.res.AssetManager;

import com.adeco.finddifferences.utils.LevelParser;


public class LevelStorage {
    private final static String DATA_FILE = "levels.xml";
    private Level[] levels;
    private int currentLevel;

    private boolean loaded = false;

    public void load(AssetManager assetManager) {
        if (loaded) {
            return;
        }
        levels = LevelParser.GetLevels(assetManager, DATA_FILE);
        loaded = true;
    }

    public Level GetCurrentLevel() {
        return levels[currentLevel];
    }

    public void goToNextLevel() {
        currentLevel++;
    }
}

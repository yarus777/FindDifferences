package com.adeco.finddifferences.game.levels;

import android.content.res.AssetManager;

import com.adeco.finddifferences.utils.LevelParser;

/**
 * Created by agorbach on 12.08.2015.
 */
public class LevelStorage {
    private final static String DATA_FILE = "levels.xml";
    private Level[] levels;
    private int currentLevel;

    public LevelStorage(AssetManager assetManager) {
        levels = LevelParser.GetLevels(assetManager, DATA_FILE);
    }

    public Level GetCurrentLevel() {
        return levels[currentLevel];
    }
}

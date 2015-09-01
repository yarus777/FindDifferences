package com.adeco.finddifferences.game.levels;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.interfaces.Destroyable;
import com.adeco.finddifferences.utils.JsonLevelParser;
import com.adeco.finddifferences.utils.XmlLevelParser;
import com.adeco.finddifferences.utils.LevelParser;


public class LevelStorage implements Destroyable {
    private final static String DATA_FILE = "levels.json";
    private final static String LEVEL_KEY = "current_level";
    public Level[] levels;
    private int currentLevel;
    private LevelParser levelParser;
    private SharedPreferences prefs;

    public LevelStorage(AssetManager assets, SharedPreferences prefs) {
        levelParser = new JsonLevelParser();
        this.prefs = prefs;
        Log.d("MY_TAG", prefs + "");
        load(assets);
    }

    private boolean loaded = false;

    private void load(AssetManager assetManager) {
        if (loaded) {
            return;
        }
        levels = levelParser.GetLevels(assetManager, DATA_FILE);
        currentLevel = prefs.getInt(LEVEL_KEY, 0);
        loaded = true;
    }

    public Level getCurrentLevel() {
        return levels[currentLevel];
    }

    public boolean goToNextLevel() {
        if (currentLevel < levels.length - 1) {
            currentLevel++;
            return true;
        }
        return false;
    }

    public void resetLevel() {
        currentLevel = 0;
    }

    public void restartLevel() {
        currentLevel = currentLevel - 1;
    }

    @Override
    public void onDestroy() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(LEVEL_KEY, currentLevel);
        editor.commit();
    }
}

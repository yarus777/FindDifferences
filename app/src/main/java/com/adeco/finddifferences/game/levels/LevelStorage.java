package com.adeco.finddifferences.game.levels;

import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.interfaces.Destroyable;
import com.adeco.finddifferences.utils.LevelParser;


public class LevelStorage implements Destroyable {
    private final static String DATA_FILE = "levels.xml";
    private final static String LEVEL_KEY = "current_level";
    public Level[] levels;
    private int currentLevel;

    private boolean loaded = false;

    public void load(AssetManager assetManager, SharedPreferences prefs) {
        if (loaded) {
            return;
        }
        levels = LevelParser.GetLevels(assetManager, DATA_FILE);
        currentLevel = prefs.getInt(LEVEL_KEY, 0);
        loaded = true;
    }

    public Level GetCurrentLevel() {
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

    @Override
    public void onDestroy() {
        SharedPreferences.Editor editor = Game.getInstance().getPreferences().edit();
        editor.putInt(LEVEL_KEY, currentLevel);
        editor.commit();
    }
}

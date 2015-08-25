package com.adeco.finddifferences.utils;

import android.content.res.AssetManager;

import com.adeco.finddifferences.game.levels.Level;

/**
 * Created by agorbach on 25.08.2015.
 */
public interface LevelParser {
    Level[] GetLevels(AssetManager assetManager, String filename);
}

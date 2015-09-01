package com.adeco.finddifferences.utils;

import android.content.res.AssetManager;

import com.adeco.finddifferences.game.levels.Level;

public interface LevelParser {
    Level[] GetLevels(AssetManager assetManager, String filename);
}

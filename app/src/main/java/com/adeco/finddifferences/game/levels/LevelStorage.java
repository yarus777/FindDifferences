package com.adeco.finddifferences.game.levels;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.interfaces.Destroyable;
import com.adeco.finddifferences.game.score.ScoreController;
import com.adeco.finddifferences.game.states.GameStateHandler;
import com.adeco.finddifferences.game.states.StateController;
import com.adeco.finddifferences.utils.JsonLevelParser;
import com.adeco.finddifferences.utils.XmlLevelParser;
import com.adeco.finddifferences.utils.LevelParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;


public class LevelStorage implements Destroyable, GameStateHandler{
    private final static String DATA_FILE = "levels.json";
    private final static String LEVEL_KEY = "current_level";
    private Level[] levels;
    private int currentLevel;
    private LevelParser levelParser;
    private SharedPreferences prefs;
    private ScoreController scoreController;

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
        Arrays.sort(levels, new Comparator<Level>() {
            @Override
            public int compare(Level level, Level level2) {
                return level.getNumber().compareTo(level2.getNumber());
            }
        });
        currentLevel = prefs.getInt(LEVEL_KEY, 0);

        String json_str = prefs.getString("Progress", "");
        try {
            JSONObject obj = new JSONObject(json_str);
            JSONArray levelsArray = obj.getJSONArray("levels");
            for(int i=0; i<levelsArray.length(); i++){
                JSONObject levelItem = levelsArray.getJSONObject(i);
                int number = levelItem.getInt("number");
                int stars = levelItem.getInt("stars");
                getLevel(number).setStarsNum(stars);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        loaded = true;


    }

    public Level getCurrentLevel() {
        return levels[currentLevel];
    }

    public void setCurrentLevel(int lvlnumber) {
        currentLevel = lvlnumber;
    }

    public boolean goToNextLevel() {
        if (isNextLevelExists()) {
            currentLevel++;
            return true;
        }
        return false;
    }

    public boolean isNextLevelExists(){
        return currentLevel < levels.length - 1;
    }

    /*public void resetLevel() {
        currentLevel = 0;
    }*/

    @Override
    public void onDestroy() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(LEVEL_KEY, currentLevel);

        JSONArray array = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            for (Level level: this.levels) {
                JSONObject stars = new JSONObject();
                stars.put("number", level.getNumber());
                stars.put("stars",  level.getStarsNum());
                array.put(stars);
            }
            res.put("levels",array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("MY_TAG", "JSON"+res.toString());
        editor.putString("Progress", res.toString());
        editor.commit();
    }

    @Override
    public void onGameStateChanged(StateController.GameState state) {
        if (state == StateController.GameState.Win || state == StateController.GameState.Completed) {
            getCurrentLevel().setStarsNum(scoreController.getStarsCount());
            //Log.d("MY_TAG", "stars"+getCurrentLevel().getStarsNum()+"level"+currentLevel);
        }
    }

    public void setScoreController(ScoreController scoreController) {
        this.scoreController = scoreController;
    }

    public int getLevelsCount(){
        return levels.length;
    }

    public Level getLevel(int i) {
        return levels[i];
    }

}

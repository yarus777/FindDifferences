package com.adeco.finddifferences.utils;

import android.content.res.AssetManager;

import com.adeco.finddifferences.game.levels.Level;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonLevelParser implements LevelParser {
    private final static String JSON_FOLDER = "json/";

    public Level[] GetLevels(AssetManager assets, String fileName) {
        String fullPath = JSON_FOLDER + fileName;
        Level[] levels;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(assets.open(fullPath)));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }

            JSONObject json = new JSONObject(stringBuffer.toString());
            JSONArray levelsArray = json.getJSONArray("levels");
            levels = new Level[levelsArray.length()];
            for (int i = 0; i < levelsArray.length(); i++) {
                JSONObject levelJson = levelsArray.getJSONObject(i);
                String image1 = levelJson.getString("image1");
                String image2 = levelJson.getString("image2");
                JSONArray difArray = levelJson.getJSONArray("differences");
                DifferencePoint[] diffs = new DifferencePoint[difArray.length()];
                for (int j = 0; j < difArray.length(); j++) {
                    JSONObject difJson = difArray.getJSONObject(j);
                    int x = difJson.getInt("x");
                    int y = difJson.getInt("y");
                    int radius = difJson.getInt("radius");
                    diffs[j] = new DifferencePoint(x, y, radius);
                }
                levels[i] = new Level(image1, image2, diffs);
            }
            return levels;
        } catch (Exception e) {
            System.out.print("Parsing error: " + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

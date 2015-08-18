package com.adeco.finddifferences.utils.state;

import android.content.SharedPreferences;

/**
 * Created by agorbach on 18.08.2015.
 */
public class StateSaver {
    private static StateSaver instance;

    public static StateSaver getInstance() {
        if (instance == null) {
            instance = new StateSaver();
        }
        return instance;
    }

    private StateSaver() {

    }

    private SharedPreferences prefs;

    public void init(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public void save(StateSaving item) {
        SharedPreferences.Editor editor = prefs.edit();
    }

    public void load(StateSaving item) {

    }
}

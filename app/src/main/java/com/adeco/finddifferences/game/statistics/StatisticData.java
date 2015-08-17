package com.adeco.finddifferences.game.statistics;

/**
 * Created by agorbach on 14.08.2015.
 */
public class StatisticData {
    private int differencesFound;
    private int movesTaken;

    public int getDifferencesFound() {
        return differencesFound;
    }

    public int getMovesTaken() {
        return movesTaken;
    }

    public void onMove() {
        movesTaken++;
    }

    public void onDifferenceFound() {
        differencesFound++;
    }
}

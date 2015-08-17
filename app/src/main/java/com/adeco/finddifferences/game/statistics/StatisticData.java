package com.adeco.finddifferences.game.statistics;


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

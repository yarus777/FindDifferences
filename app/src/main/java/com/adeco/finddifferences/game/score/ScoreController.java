package com.adeco.finddifferences.game.score;

import android.util.Log;

import com.adeco.finddifferences.game.interfaces.TimeCounter;
import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

public class ScoreController implements StatisticHandler{

    private ScoreHandler[] handlers;
    private int tries;
    private TimeCounter timeCounter;

    public ScoreController(TimeCounter timeCounter) {
        this.timeCounter = timeCounter;
    }


    @Override
    public void handleStatistics(StatisticData data) {
        tries = data.getMovesTaken();
    }

    public int getStarsCount() {
        int time = timeCounter.getTimeSinceStart();
        Log.d("MyTag", "Time "+ time+"tries "+tries);
        if (time <=20 && tries == 0)
            return 3;
        else if (time <=40 && tries <=2)
            return 2;
        else return 1;
    }
}

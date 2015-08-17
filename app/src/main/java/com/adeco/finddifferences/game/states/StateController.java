package com.adeco.finddifferences.game.states;

import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

public class StateController implements StatisticHandler {
    @Override
    public void handleStatistics(StatisticData data) {

        if (data.getDifferencesFound() < 5  && data.getMovesTaken() == 10) {

        }
    }
}

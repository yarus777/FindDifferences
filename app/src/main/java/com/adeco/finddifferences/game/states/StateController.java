package com.adeco.finddifferences.game.states;

import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

/**
 * Created by agorbach on 17.08.2015.
 */
public class StateController implements StatisticHandler {
    @Override
    public void handleStatistics(StatisticData data) {
        // здесь можно проверять на выигрыш-проигрыш, например
        if (data.getDifferencesFound() == 5) {
            // что-то делаем
        }
    }
}

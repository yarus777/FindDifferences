package com.adeco.finddifferences.game.states;

import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

import java.util.ArrayList;
import java.util.List;

public class StateController implements StatisticHandler {

    public enum GameState {Win, Lose, InProgress}

    private List<GameStateHandler> handlers = new ArrayList<>();

    @Override
    public void handleStatistics(StatisticData data) {

        if (data.getDifferencesFound() < 5 && data.getMovesTaken() == 10) {
            onStateChanged(GameState.Lose);
        }
    }

    public void addHandler(GameStateHandler handler) {
        handlers.add(handler);
    }

    private void onStateChanged(GameState state) {
        for (GameStateHandler handler : handlers) {
            handler.onGameStateChanged(state);
        }
    }
}

package com.adeco.finddifferences.game.states;


import com.adeco.finddifferences.game.levels.LevelStorage;
import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

import java.util.ArrayList;
import java.util.List;

public class StateController implements StatisticHandler {

    private LevelStorage levelStorage;

    public StateController(LevelStorage levelStorage) {
        this.levelStorage = levelStorage;
    }

    public enum GameState {Win, Lose, InProgress, Completed}

    private List<GameStateHandler> handlers = new ArrayList<>();

    @Override
    public void handleStatistics(StatisticData data) {

        if (data.getDifferencesFound() < 5 && data.getMovesTaken() == 5) {
            onStateChanged(GameState.Lose);
        }
        else if (data.getDifferencesFound()==5 && data.getMovesTaken()<=5) {
            if (levelStorage.isNextLevelExists()) {
                onStateChanged(GameState.Win);
            }
            else onStateChanged(GameState.Completed);
        }
    }

    public void addHandler(GameStateHandler handler) {
        handlers.add(handler);
    }

    public void start() {
        onStateChanged(GameState.InProgress);
    }

    private void onStateChanged(GameState state) {
        for (GameStateHandler handler : handlers) {
            handler.onGameStateChanged(state);
        }
    }
}

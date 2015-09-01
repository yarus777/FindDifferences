package com.adeco.finddifferences.game.startedlevel;

import android.content.Context;

import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.states.GameStateHandler;
import com.adeco.finddifferences.game.states.StateController;

public class LevelStarted implements GameStateHandler {
    private int levelsleft;
    private Game game;
    private int fullVersionCount;

    @Override
    public void onGameStateChanged(StateController.GameState state) {
        if (state == StateController.GameState.Win) {
            levelsleft++;
            if (levelsleft == fullVersionCount) {
                levelsleft = 0;
                if (game.getSettings().ShowFullAppDialog) {
                    game.getPopupController().showFullVersionPopup();
                }
            }
        }

    }

    public void init(Game game) {
        fullVersionCount = game.getResources().getInteger(R.integer.full_version_count);
        this.game = game;
    }
}

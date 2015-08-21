package com.adeco.finddifferences.game.startedlevel;

import android.content.Context;

import com.adeco.finddifferences.R;
import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.states.GameStateHandler;
import com.adeco.finddifferences.game.states.StateController;

public class LevelStarted implements GameStateHandler {
    private int levelsleft;
    private Context context;
    private int fullVersionCount;

    @Override
    public void onGameStateChanged(StateController.GameState state) {
        if (state == StateController.GameState.InProgress) {
            levelsleft++;
            if (levelsleft == fullVersionCount) {
                levelsleft = 0;
               Game.getInstance().getPopupController().showFullVersionPopup();
            }
        }
    }

    public LevelStarted(Context context) {
        this.context = context;
        fullVersionCount = context.getResources().getInteger(R.integer.full_version_count);
    }
}

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
            if (levelsleft == fullVersionCount) {
                levelsleft = 0;
                if (Game.getInstance().getSettings().ShowFullAppDialog) {
                    Game.getInstance().getPopupController().showFullVersionPopup();
                }
            }
            levelsleft++;
        }

    }

    public void setContext(Context context) {
        fullVersionCount = context.getResources().getInteger(R.integer.full_version_count);
    }
}

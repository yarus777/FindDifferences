package com.adeco.finddifferences.game.popups;

import com.adeco.finddifferences.game.states.GameStateHandler;

public interface PopupController extends GameStateHandler {
    public void showLosePopup();

    public void showWinPopup();

    public void showFullVersionPopup();

    public void showCompletedPopup();
}

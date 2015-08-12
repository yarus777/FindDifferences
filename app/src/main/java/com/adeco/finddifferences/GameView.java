package com.adeco.finddifferences;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.adeco.finddifferences.game.Game;

public class GameView extends View {
    private Game game;

    public GameView(Context context) {
        super(context);
        game = new Game(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        game.draw(canvas);
    }
}

package com.adeco.finddifferences;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.adeco.finddifferences.game.Game;

public class GameView extends View implements View.OnTouchListener {
    private Game game;

    public GameView(Context context) {
        super(context);
        game = new Game(context);
        this.setOnTouchListener(this);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        game.draw(canvas);
    }

    public boolean onTouch(View view, MotionEvent event) {
        game.onTouch(event);
        invalidate();
        return true;
    }
}

package com.adeco.finddifferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.interfaces.DifferenceFoundHandler;
import com.adeco.finddifferences.game.popups.Popups;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

public class GameView extends View implements View.OnTouchListener {
    private Game game;
    private Context context;

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.setOnTouchListener(this);
        game = (Game) context.getApplicationContext();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        game.draw(canvas);
        invalidate();
    }

    public boolean onTouch(View view, MotionEvent event) {
        game.onTouch(event);
        //invalidate();
        return true;
    }

    public void init(StatisticHandler statisticHandler, DifferenceFoundHandler differenceFoundHandler, Popups losePopup, GameActivity activity) {
        game.start(statisticHandler, differenceFoundHandler, losePopup, activity);
    }
}

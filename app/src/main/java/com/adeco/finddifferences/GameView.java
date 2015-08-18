package com.adeco.finddifferences;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.adeco.finddifferences.game.DifferenceFoundHandler;
import com.adeco.finddifferences.game.Game;
import com.adeco.finddifferences.game.popups.Popups;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

public class GameView extends View implements View.OnTouchListener {
    private Game game = Game.getInstance();
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

    public void init(StatisticHandler statisticHandler, DifferenceFoundHandler differenceFoundHandler, Popups losePopup) {
        game.init(context, statisticHandler, differenceFoundHandler, losePopup);
    }
}

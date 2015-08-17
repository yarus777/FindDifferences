package com.adeco.finddifferences.game.logic;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.Touchable;
import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agorbach on 14.08.2015.
 */
public class TouchManager implements Touchable, Drawable {
    private TouchHandler top;
    private TouchHandler bottom;
    private DifferencePoint[] points;

    private List<AbstractPoint> diffs;
    private StatisticHandler statisticHandler;
    private StatisticData statistics;

    public TouchManager(DifferencePoint[] points, TouchHandler top, TouchHandler bottom, StatisticHandler statisticHandler) {
        this.top = top;
        this.bottom = bottom;
        diffs = new ArrayList<>();
        this.points = points;
        this.statisticHandler = statisticHandler;
        statistics = new StatisticData();
    }

    @Override
    public void onTouch(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                AbstractPoint touch = top.getDifference(points, x, y);
                if (touch == null) {
                    touch = bottom.getDifference(points, x, y);
                }
                if (touch != null) {
                    diffs.add(touch);
                    statistics.onDifferenceFound();
                }
                statistics.onMove();
                statisticHandler.handleStatistics(statistics);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        top.draw(canvas, diffs);
        bottom.draw(canvas, diffs);
    }
}

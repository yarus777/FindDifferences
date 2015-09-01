package com.adeco.finddifferences.game.logic;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.adeco.finddifferences.game.interfaces.DifferenceFoundHandler;
import com.adeco.finddifferences.game.interfaces.Drawable;
import com.adeco.finddifferences.game.interfaces.Touchable;
import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.logic.points.WrongPoint;
import com.adeco.finddifferences.game.statistics.StatisticData;
import com.adeco.finddifferences.game.statistics.StatisticHandler;

import java.util.ArrayList;
import java.util.List;


public class TouchManager implements Touchable, Drawable {
    private TouchHandler top;
    private TouchHandler bottom;
    private DifferencePoint[] points;

    private List<AbstractPoint> diffs;
    private List<WrongPoint> misses;
    private StatisticHandler[] statisticHandlers;
    private StatisticData statistics;
    private DifferenceFoundHandler[] differenceFoundHandlers;

    public TouchManager(DifferencePoint[] points, TouchHandler top, TouchHandler bottom, StatisticHandler[] statisticHandler, DifferenceFoundHandler[] differenceFoundHandlers) {
        this.top = top;
        this.bottom = bottom;
        diffs = new ArrayList<>();
        misses = new ArrayList<>();
        this.points = points;
        this.statisticHandlers = statisticHandler;
        this.differenceFoundHandlers = differenceFoundHandlers;
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
                    for(DifferenceFoundHandler handler: differenceFoundHandlers)
                        handler.onDifferenceFound();

                }
                else {
                    WrongPoint wrongPoint = new WrongPoint(x,y);
                    misses.add(wrongPoint);
                    for(DifferenceFoundHandler handler: differenceFoundHandlers)
                        handler.onWrongTouch();
                }
                statistics.onMove();
                for (StatisticHandler handler : statisticHandlers) {
                    handler.handleStatistics(statistics);
                }

        }
    }

    @Override
    public void draw(Canvas canvas) {
        top.draw(canvas, diffs);
        bottom.draw(canvas, diffs);
        for(WrongPoint point: misses){
            point.draw(canvas);
        }
    }
}

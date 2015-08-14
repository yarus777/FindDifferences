package com.adeco.finddifferences.game.logic;

import android.graphics.Canvas;

import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;

import java.util.List;

/**
 * Created by agorbach on 14.08.2015.
 */
public interface TouchHandler {
    AbstractPoint getDifference(DifferencePoint[] points, int x, int y);

    void draw(Canvas canvas, List<AbstractPoint> diffs);
}

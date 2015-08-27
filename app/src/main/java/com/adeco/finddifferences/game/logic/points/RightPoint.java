package com.adeco.finddifferences.game.logic.points;

import android.content.Context;

import com.adeco.finddifferences.game.Consts;


public class RightPoint extends AbstractPoint {
    public RightPoint(DifferencePoint dif) {
        super(dif.getX(), dif.getY(), dif.getRadius(), Consts.difPaint);
    }
}

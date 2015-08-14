package com.adeco.finddifferences.game.logic.points;

import com.adeco.finddifferences.game.Consts;

/**
 * Created by agorbach on 14.08.2015.
 */
public class RightPoint extends AbstractPoint {
    public RightPoint(DifferencePoint dif) {
        super(dif.getX(), dif.getY(), dif.getRadius(), Consts.difPaint);
    }
}

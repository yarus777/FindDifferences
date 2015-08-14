package com.adeco.finddifferences.game.logic.points;

import com.adeco.finddifferences.game.Consts;

/**
 * Created by agorbach on 14.08.2015.
 */
public class WrongPoint extends AbstractPoint {
    public WrongPoint(int x, int y) {
        super(x, y, Consts.wrongRadius, Consts.wrongPaint);
    }
}

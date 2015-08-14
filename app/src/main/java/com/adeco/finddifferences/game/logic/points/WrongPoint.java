package com.adeco.finddifferences.game.logic.points;

import com.adeco.finddifferences.game.Consts;


public class WrongPoint extends AbstractPoint {
    public WrongPoint(int x, int y) {
        super(x, y, Consts.wrongRadius, Consts.wrongPaint);
    }
}

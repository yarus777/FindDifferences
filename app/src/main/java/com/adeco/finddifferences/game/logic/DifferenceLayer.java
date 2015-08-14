package com.adeco.finddifferences.game.logic;

import android.graphics.Canvas;

import com.adeco.finddifferences.game.Drawable;
import com.adeco.finddifferences.game.logic.points.AbstractPoint;
import com.adeco.finddifferences.game.logic.points.DifferencePoint;
import com.adeco.finddifferences.game.logic.points.RightPoint;
import com.adeco.finddifferences.game.logic.points.WrongPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agorbach on 12.08.2015.
 */
public class DifferenceLayer implements Drawable {
    private DifferencePoint[] diffs;
    private List<AbstractPoint> touches;

    public DifferenceLayer(DifferencePoint[] diffs, double scaleFactor) {
        this.diffs = new DifferencePoint[diffs.length];
        this.touches = new ArrayList<>();
        for (int i = 0; i < diffs.length; i++) {
            this.diffs[i] = diffs[i].scale(scaleFactor);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (AbstractPoint dif : touches) {
            dif.draw(canvas);
        }
    }

    public void touch(int x, int y) {
        boolean found = false;
        for (DifferencePoint dif : diffs) {
            if (dif.Check(x, y) && !alreadyFound(dif)) {
                touches.add(new RightPoint(dif));
                found = true;
                break;
            }
        }
        if (!found) {
            touches.add(new WrongPoint(x, y));
        }

    }

    private boolean alreadyFound(DifferencePoint point) {
        for (AbstractPoint p : touches) {
            if (p.match(point)) {
                return true;
            }
        }
        return false;
    }
}

package com.adeco.finddifferences.game.levels;

import com.adeco.finddifferences.game.DifferencePoint;

/**
 * Created by agorbach on 12.08.2015.
 */
public class Level {
    private String img1;
    private String img2;
    private DifferencePoint[] diffs;

    public Level(String img1, String img2, DifferencePoint[] diffs) {
        this.img1 = img1;
        this.img2 = img2;
        this.diffs = diffs;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }

    public DifferencePoint[] getDiffs() {
        return diffs;
    }
}

package com.adeco.finddifferences.game.levels;

import com.adeco.finddifferences.game.logic.points.DifferencePoint;


public class Level {
    private String img1;
    private String img2;
    private DifferencePoint[] diffs;
    private int number;
    private int starsNum;

    public Level(int number, String img1, String img2, DifferencePoint[] diffs) {
        this.number = number;
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

    public int getNumber(){
        return number;
    }

    public int getStarsNum() {return starsNum;}

    public void setStarsNum(int starsNum) {
        this.starsNum = starsNum;
    }
}

package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * CLASS ADDED BY TEAM CRAIG
 */
public class Goose extends Character {

    public Goose(Sprite sprite, Vector2 spawn, float speedMult, Level currentLevel) {
        super(sprite, spawn, currentLevel);
        this.speed = Constant.GOOSESPEED * speedMult;
    }
}

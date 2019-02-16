package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ZombieMedic extends Zombie {

    public ZombieMedic(Sprite sprite, Vector2 zombieSpawn, Level currentLevel) {
        super(sprite, zombieSpawn, currentLevel);
        this.speed = Constant.ZOMBIESPEED;
        this.speed = Constant.ZOMBIEHEALTHYMAXHP;
    }
}

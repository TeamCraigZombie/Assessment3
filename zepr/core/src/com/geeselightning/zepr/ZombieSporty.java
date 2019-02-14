package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ZombieSporty extends Zombie {

    public ZombieSporty(Sprite sprite, Vector2 zombieSpawn, Level currentLevel) {
        super(sprite, zombieSpawn, currentLevel);
        this.speed = Constant.ZOMBIESPORTYSPEED;
        this.health = Constant.ZOMBIEMAXHP;
    }
}



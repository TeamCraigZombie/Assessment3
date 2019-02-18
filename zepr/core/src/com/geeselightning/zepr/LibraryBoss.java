package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * CLASS ADDED BY TEAM CRAIG
 */
public class LibraryBoss extends Zombie {

    public LibraryBoss(Sprite sprite, Vector2 zombieSpawn, Level currentLevel) {
        super(sprite, zombieSpawn, currentLevel);
        this.speed = Constant.BOSSSPEED;
        this.health = Constant.BOSSHEALTH;
    }
}

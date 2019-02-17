package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CourtyardBoss extends Zombie {

    public CourtyardBoss(Sprite sprite, Vector2 zombieSpawn, Level currentLevel) {
        super(sprite, zombieSpawn, currentLevel);
        this.speed = Constant.BOSSSPEED;
        this.health = Constant.BOSSHEALTH;
    }
}

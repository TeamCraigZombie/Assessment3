package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

/**
 * CLASS ADDED BY TEAM CRAIG
 */
public class PowerUpInstaKill extends PowerUp {
    public float timeRemaining = Constant.INSTAKILLTIME;

    public PowerUpInstaKill(Level currentLevel) {
        super(4, new Texture("insta.png"), currentLevel);
    }

    /**
     * Activates power up and changes corresponding player attribute
     */
    @Override
    public void activate() {
        super.activate();
        super.player.attackDamage = Constant.INSTAKILLDMG;
        this.getTexture().dispose();
    }

    /**
     * Deactivates power up and returns player attribute to normal
     */
    @Override
    public void deactivate() {
        super.deactivate();
        super.player.attackDamage = Constant.PLAYERDMG;
    }

    /**
     * decrements timer
     * checks if timer is up and calls deactivate if so
     * @param delta time
     */
    @Override
    public void update(float delta) {
        if (active) {
            timeRemaining -= delta;
        }
        if (timeRemaining < 0) {
            deactivate();
        }
    }
}

package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

/**
 * CLASS ADDED BY TEAM CRAIG
 */
public class PowerUpNuke extends PowerUp {
    private float timeRemaining = Constant.NUKETIME;
    private float nukeDelay = Constant.NUKEDELAY;
    private boolean fired = false;



    public PowerUpNuke(Level currentLevel) {
        super(5, new Texture("nuke.png"), currentLevel);
    }

    @Override
    public void activate() {
        super.activate();
        this.getTexture().dispose();
    }

    @Override
    public void deactivate() {
        currentLevel.zombiesRemaining = 0;
        super.deactivate();
    }

    /**
     * Kills wave of zombies after slight delay
     * Calls deactivate after a further cool down period
     * @param delta time
     */
    @Override
    public void update(float delta) {
        if (active) {
            timeRemaining -= delta;
            nukeDelay -= delta;
        }
        if(nukeDelay < 0 && !fired){
            currentLevel.killWave();
            fired = true;
        }
        if (timeRemaining < 0) {
            deactivate();
        }
    }
}

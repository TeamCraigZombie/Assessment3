package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

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

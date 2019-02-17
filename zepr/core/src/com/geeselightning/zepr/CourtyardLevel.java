package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

public class CourtyardLevel extends Level {

    private static final String mapLocation = "maps/courtyard.tmx";
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    //TEAM CRAIG:
    private boolean bossRound = false;
    private float timer = 0;

    // Defining possible zombie spawn locations on this map
    public static final ArrayList<Vector2> zombieSpawnPoints = new ArrayList<Vector2>(
            Arrays.asList(new Vector2(120,100), new Vector2(630,600),
                    new Vector2(630,100), new Vector2(120,500))
    );

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{7, 12, 17};

    public CourtyardLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSpawn);
    }

    @Override
    public void complete() {
        //TEAM CRAIG:
        if (!bossRound){
            isPaused = false;
            currentPowerUp = new PowerUpHeal(this);
            bossRound = true;
            zombie = (new CourtyardBoss(new Sprite(new Texture("boss01.png")), zombieSpawnPoints.get(0), this) );
            aliveZombies.add(zombie);
            zombiesRemaining = 1;
            timer = Constant.BOSSSSPAWNTIME;
        }

        // Update progress
        else if (parent.progress == parent.COURTYARD) {
            parent.progress = parent.CSBUILDING;
            parent.setScreen(new TextScreen(parent, "Level completed."));
        }
        // The stage is being replayed
    }

    /**
     * TEAM CRAIG:
     * Handles rendering for boss round.
     * @param delta time
     */
    @Override
    public void render(float delta){
        super.render(delta);

        if (bossRound) {
            if (timer > 0 && timer <= Constant.BOSSSSPAWNTIME) {
                timer -= delta;
            } else if (timer <= 0) {
                timer = Constant.BOSSSSPAWNTIME + 1;
                zombiesRemaining += 5;
                zombiesToSpawn = zombiesRemaining - 1;
            }

            if (zombiesRemaining == 1 && timer > Constant.BOSSSSPAWNTIME) {
                timer = Constant.BOSSSSPAWNTIME;
            }
        }
    }
}

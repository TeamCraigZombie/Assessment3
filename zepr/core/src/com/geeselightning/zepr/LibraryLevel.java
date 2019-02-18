package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * CLASS ADDED BY TEAM CRAIG
 */
public class LibraryLevel extends Level {

    private static final String mapLocation = "maps/library.tmx";
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    //TEAM CRAIG: ADDED
    private boolean bossRound = false;
    private float timer = 0;

    // Defining possible zombie spawn locations on this map
    public static final ArrayList<Vector2> zombieSpawnPoints = new ArrayList<Vector2>(
            Arrays.asList(new Vector2(120,130), new Vector2(460,680),
                    new Vector2(490,50), new Vector2(120,500))
    );

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{10, 15, 20};

    public LibraryLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSpawn);
    }

    @Override
    public void complete() {
        //TEAM CRAIG: ADDED
        if (!bossRound){
            isPaused = false;
            currentPowerUp = new PowerUpHeal(this);
            bossRound = true;
            zombie = (new LibraryBoss(new Sprite(new Texture("boss02.png")), zombieSpawnPoints.get(0), this) );
            aliveZombies.add(zombie);
            timer = Constant.BOSSSSPAWNTIME;
            zombiesRemaining = 1;
        }

        // Update progress
        else if (parent.progress == parent.LIBRARY) {
            parent.progress = parent.COMPLETE;
            parent.setScreen(new TextScreen(parent, "Game completed."));
        }
        // The stage is being replayed
    }

    /**
     * TEAM CRAIG: ADDED
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
                zombiesRemaining += 5;
                zombiesToSpawn = zombiesRemaining - 1;
                timer = Constant.BOSSSSPAWNTIME + 1;
            }

            if (zombiesRemaining == 1 && timer > Constant.BOSSSSPAWNTIME) {
                timer = Constant.BOSSSSPAWNTIME;
            }
        }
    }

}

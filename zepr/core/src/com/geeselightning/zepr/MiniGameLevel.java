package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CLASS ADDED BY TEAM CRAIG
 */
public class MiniGameLevel extends Level{

    private static final String mapLocation = "maps/MiniGame.tmx";
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    private Label pointsLabel = new Label("", skin);
    private Label ammoLabel = new Label("", skin);
    private int ammo, points = 0;
    private Goose goose = null;
    private float speedMult = 1;

    // Defining possible zombie spawn locations on this map
    public static final ArrayList<Vector2> zombieSpawnPoints = new ArrayList<Vector2>(
            Arrays.asList(new Vector2(120,100), new Vector2(630,600),
                    new Vector2(630,100), new Vector2(120,500))
    );

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{0, 0, 0};

    public MiniGameLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSpawn);
        spawnGoose();
        ammo = 5;
    }

    @Override
    public void render(float delta) {
        if (isPaused) {
            this.pause();
        } else {
            // Clears the screen to black.
            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            table.clear();

            //set camera to the center of the screen
            camera.position.set(720, 450, 0);
            camera.update();
            renderer.setView(camera);
            renderer.render();

            //Render characters in the scene
            renderer.getBatch().begin();
            goose.draw(renderer.getBatch());
            renderer.getBatch().end();

            //Call shoot method is player has clicked
            if (player.miniGameAttack) {
                shoot();
                player.miniGameAttack = false;
            }

            //Check if goose has crossed screen without being shot
            isGooseMissed();

            //Check if player is out of ammo and call gameOver if so
            if (ammo <= 0) {gameOver();}

            renderText();
        }
    }

    /**
     * Renders all the text on the screen(point/ammo)
     */
    private void renderText(){
        String pointsString = ("Points: " + Integer.toString(points));
        String ammoString = ("Bullets Remaining: " + Integer.toString(ammo));
        ammoLabel.setText(ammoString);
        pointsLabel.setText(pointsString);

        table.top().left();
        table.add(pointsLabel).pad(10).left();
        table.row().pad(10);
        table.add(ammoLabel).pad(10).left();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Creates a new instance of a goose at a random height in the sky.
     * Goose is spawned either to the left or to the right of the screen
     */
    private void spawnGoose() {
        int rand = (int )(Math.random() * 2 + 1);
        int y = (int )(Math.random() * 300 + 451);

        if (rand == 1) {
            goose = (new Goose(new Sprite(new Texture("Goose2.png")), new Vector2(-50, y), speedMult, this));
            goose.velocity.x = goose.speed;
        }
        else {
            goose = (new Goose(new Sprite(new Texture("Goose.png")), new Vector2(1490, y), speedMult, this));
            goose.velocity.x = -goose.speed;
        }
    }

    /**
     * Checks to see if the goose has crossed the screen without being shot
     * If so a new goose is spawned and the points are decremented
     */
    private void isGooseMissed(){
        if (goose.getX() < -50 || goose.getX() > 1490){
            goose = null;
            spawnGoose();
            points --;
        }
    }

    /**
     * Called when left mouse pressed
     * Calculates if cursor intercepts goose
     * If so new goose is spawned and points incremented
     * Otherwise ammo is decremented
     */
    private void shoot(){
        float diameter = goose.getHeight();
        Vector2 cursor = this.getMouseWorldCoordinates();
        double distanceBetweenCenters = (Math.pow(cursor.x - goose.getCenter().x, 2)
                + Math.pow(cursor.y - goose.getCenter().y, 2));

        if (0 <= distanceBetweenCenters && distanceBetweenCenters <= Math.pow(diameter, 2)){
            speedMult += 0.1;
            goose = null;
            spawnGoose();
            points ++;
        }
        else {ammo--;}
    }

    /**
     * Changes to end screen showing the points scored
     */
    @Override
    public void gameOver() {
        isPaused = true;
        parent.setScreen(new TextScreen(parent, " Game Over" + (char)(9) + "Points: " + Integer.toString(points)));
    }

}

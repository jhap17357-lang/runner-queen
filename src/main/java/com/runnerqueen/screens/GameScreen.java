package com.runnerqueen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.Environment;
import com.badlogic.gdx.math.Vector3;
import com.runnerqueen.RunnerQueenGame;
import com.runnerqueen.entities.Player;
import com.runnerqueen.managers.GameManager;

/**
 * Main gameplay screen
 */
public class GameScreen implements Screen {
    private final RunnerQueenGame game;
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private Environment environment;
    
    private GameManager gameManager;
    private Player player;
    
    private boolean isPaused = false;
    private float score = 0;
    private float distance = 0;

    public GameScreen(RunnerQueenGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Setup camera
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 2, 5);
        camera.lookAt(0, 1, 0);
        camera.near = 0.1f;
        camera.far = 300f;
        camera.update();
        
        // Setup 3D rendering
        modelBatch = new ModelBatch();
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        
        // Setup lighting
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        // Initialize game manager and player
        gameManager = new GameManager();
        player = new Player();
    }

    @Override
    public void render(float delta) {
        handleInput();
        
        if (!isPaused) {
            update(delta);
        }
        
        render3D();
        render2D();
    }

    private void handleInput() {
        // Player movement
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft(Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight(Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.jump();
        }
        
        // Pause
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isPaused = !isPaused;
        }
    }

    private void update(float delta) {
        player.update(delta);
        distance += RunnerQueenGame.GAME_SPEED * delta;
        score = (int) distance / 10;
        
        gameManager.update(delta, player);
        
        // Update camera to follow player
        Vector3 playerPos = player.getPosition();
        camera.position.set(playerPos.x, playerPos.y + 2, playerPos.z + 5);
        camera.lookAt(playerPos.x, playerPos.y + 1, playerPos.z - 5);
        camera.update();
    }

    private void render3D() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1f);
        
        modelBatch.begin(camera);
        
        // Render player
        player.render(modelBatch);
        
        // Render game objects
        gameManager.render(modelBatch);
        
        modelBatch.end();
    }

    private void render2D() {
        spriteBatch.begin();
        
        // Draw UI
        font.getData().setScale(1.5f);
        font.draw(spriteBatch, "Score: " + (int) score, 20, Gdx.graphics.getHeight() - 20);
        font.draw(spriteBatch, "Distance: " + (int) distance + "m", 20, Gdx.graphics.getHeight() - 60);
        
        if (isPaused) {
            font.getData().setScale(2f);
            font.draw(spriteBatch, "PAUSED", 
                (Gdx.graphics.getWidth() - font.getBounds("PAUSED").width) / 2f,
                Gdx.graphics.getHeight() / 2f);
        }
        
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        if (modelBatch != null) modelBatch.dispose();
        if (spriteBatch != null) spriteBatch.dispose();
        if (font != null) font.dispose();
        if (player != null) player.dispose();
    }
}

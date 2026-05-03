package com.runnerqueen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.runnerqueen.screens.MenuScreen;

/**
 * Main game class for Runner Queen - A 3D endless runner game
 */
public class RunnerQueenGame extends Game {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final String GAME_TITLE = "Runner Queen";
    public static final float GAME_SPEED = 15f;

    private MenuScreen menuScreen;

    @Override
    public void create() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1f);
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        if (screen != null) {
            screen.dispose();
        }
        if (menuScreen != null) {
            menuScreen.dispose();
        }
    }

    public static void main(String[] args) {
        new RunnerQueenGame();
    }
}

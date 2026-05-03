package com.runnerqueen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.runnerqueen.RunnerQueenGame;

/**
 * Main menu screen
 */
public class MenuScreen implements Screen {
    private final RunnerQueenGame game;
    private SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont titleFont;
    private float buttonX, buttonY, buttonWidth, buttonHeight;

    public MenuScreen(RunnerQueenGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3f);
        
        buttonWidth = 200;
        buttonHeight = 60;
        buttonX = (RunnerQueenGame.SCREEN_WIDTH - buttonWidth) / 2f;
        buttonY = RunnerQueenGame.SCREEN_HEIGHT / 2f - 50;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        batch.begin();
        
        // Draw title
        titleFont.draw(batch, "RUNNER QUEEN", 
            (RunnerQueenGame.SCREEN_WIDTH - titleFont.getBounds("RUNNER QUEEN").width) / 2f,
            RunnerQueenGame.SCREEN_HEIGHT - 100);
        
        // Draw subtitle
        font.draw(batch, "An Endless Running Adventure",
            (RunnerQueenGame.SCREEN_WIDTH - font.getBounds("An Endless Running Adventure").width) / 2f,
            RunnerQueenGame.SCREEN_HEIGHT - 150);
        
        // Draw play button
        drawButton("PLAY GAME", buttonX, buttonY);
        
        // Draw instructions
        font.getData().setScale(1f);
        font.draw(batch, "Press SPACE or click to start",
            (RunnerQueenGame.SCREEN_WIDTH - font.getBounds("Press SPACE or click to start").width) / 2f,
            200);
        
        batch.end();
        
        handleInput();
    }

    private void drawButton(String text, float x, float y) {
        // Draw button background
        batch.flush();
        Gdx.gl20.glLineWidth(2);
        // Simple rectangle outline (you can enhance with textures later)
        font.draw(batch, text, x + 20, y + 35);
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || 
            Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            startGame();
        }
        
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = RunnerQueenGame.SCREEN_HEIGHT - Gdx.input.getY();
            
            if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
                mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
                startGame();
            }
        }
    }

    private void startGame() {
        game.setScreen(new GameScreen(game));
        dispose();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (font != null) font.dispose();
        if (titleFont != null) titleFont.dispose();
    }
}

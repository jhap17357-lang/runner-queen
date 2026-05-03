package com.runnerqueen.utils;

/**
 * Game constants and configuration
 */
public class Constants {
    // Screen
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    
    // Game speed
    public static final float BASE_GAME_SPEED = 15f;
    
    // Player
    public static final float PLAYER_MOVE_SPEED = 8f;
    public static final float PLAYER_JUMP_FORCE = 15f;
    public static final float PLAYER_GRAVITY = -20f;
    
    // World
    public static final float GROUND_LEVEL = 0.9f;
    public static final float SPAWN_DISTANCE = -20f;
    
    // Spawning
    public static final float INITIAL_OBSTACLE_INTERVAL = 8f;
    public static final float INITIAL_COIN_INTERVAL = 3f;
    public static final float INITIAL_POWERUP_INTERVAL = 25f;
    
    // Scoring
    public static final int COIN_VALUE = 10;
    public static final int DISTANCE_MULTIPLIER = 5;
}

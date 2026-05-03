package com.runnerqueen.managers;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.runnerqueen.entities.*;
import java.util.Random;

/**
 * Manages game state, spawning obstacles, coins, and power-ups
 */
public class GameManager {
    private Array<Obstacle> obstacles;
    private Array<Coin> coins;
    private Array<PowerUp> powerUps;
    private CollisionManager collisionManager;
    
    private Random random;
    private float spawnDistance = 0f;
    private float lastObstacleZ = -10f;
    private float lastCoinZ = -10f;
    private float lastPowerUpZ = -30f;
    private float obstacleSpawnInterval = 8f;
    private float coinSpawnInterval = 3f;
    private float powerUpSpawnInterval = 25f;
    private int difficulty = 1;

    public GameManager() {
        obstacles = new Array<>();
        coins = new Array<>();
        powerUps = new Array<>();
        collisionManager = new CollisionManager();
        random = new Random();
    }

    public void update(float delta, Player player) {
        spawnDistance += 15 * delta; // Game speed
        
        // Spawn obstacles
        if (spawnDistance - lastObstacleZ > obstacleSpawnInterval) {
            spawnObstacle();
            lastObstacleZ = spawnDistance;
        }
        
        // Spawn coins
        if (spawnDistance - lastCoinZ > coinSpawnInterval) {
            spawnCoin();
            lastCoinZ = spawnDistance;
        }
        
        // Spawn power-ups (less frequently)
        if (spawnDistance - lastPowerUpZ > powerUpSpawnInterval) {
            spawnPowerUp();
            lastPowerUpZ = spawnDistance;
        }
        
        // Update entities
        for (Obstacle obs : obstacles) {
            obs.update(delta);
        }
        for (Coin coin : coins) {
            coin.update(delta);
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.update(delta);
        }
        
        // Remove off-screen obstacles
        for (int i = obstacles.size - 1; i >= 0; i--) {
            if (obstacles.get(i).getPosition().z > 10) {
                obstacles.removeIndex(i);
            }
        }
        
        // Remove collected coins
        for (int i = coins.size - 1; i >= 0; i--) {
            if (coins.get(i).isCollected()) {
                coins.removeIndex(i);
            }
        }
        
        // Remove collected power-ups
        for (int i = powerUps.size - 1; i >= 0; i--) {
            if (powerUps.get(i).isCollected()) {
                powerUps.removeIndex(i);
            }
        }
        
        // Collision detection
        collisionManager.checkCollisions(player, obstacles, coins, powerUps);
        
        // Increase difficulty over time
        difficulty = (int) (1 + spawnDistance / 100);
        obstacleSpawnInterval = Math.max(3f, 8f - difficulty * 0.2f);
    }

    private void spawnObstacle() {
        float x = random.nextFloat() * 3f - 1.5f; // Random lane
        float z = -20f; // Spawn far ahead
        Vector3 position = new Vector3(x, 0.4f, z);
        
        Obstacle.ObstacleType[] types = Obstacle.ObstacleType.values();
        Obstacle.ObstacleType type = types[random.nextInt(types.length)];
        obstacles.add(new Obstacle(position, type));
    }

    private void spawnCoin() {
        float x = random.nextFloat() * 3f - 1.5f;
        float y = 1f + random.nextFloat() * 1f;
        float z = -20f;
        coins.add(new Coin(new Vector3(x, y, z)));
    }

    private void spawnPowerUp() {
        float x = random.nextFloat() * 3f - 1.5f;
        float y = 1f;
        float z = -25f;
        PowerUp.PowerUpType[] types = PowerUp.PowerUpType.values();
        PowerUp.PowerUpType type = types[random.nextInt(types.length)];
        powerUps.add(new PowerUp(new Vector3(x, y, z), type));
    }

    public void render(ModelBatch batch) {
        for (Obstacle obs : obstacles) {
            obs.render(batch);
        }
        for (Coin coin : coins) {
            coin.render(batch);
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.render(batch);
        }
    }

    public Array<Obstacle> getObstacles() { return obstacles; }
    public Array<Coin> getCoins() { return coins; }
    public Array<PowerUp> getPowerUps() { return powerUps; }
    public int getDifficulty() { return difficulty; }
    public float getSpawnDistance() { return spawnDistance; }
}

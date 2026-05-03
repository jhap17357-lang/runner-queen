package com.runnerqueen.managers;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.runnerqueen.entities.*;

/**
 * Handles collision detection between player and game objects
 */
public class CollisionManager {
    
    public void checkCollisions(Player player, Array<Obstacle> obstacles, 
                                Array<Coin> coins, Array<PowerUp> powerUps) {
        // Check obstacle collisions
        for (Obstacle obs : obstacles) {
            if (checkBoundingBoxCollision(player, obs)) {
                // Player hit obstacle - game over or lose shield
                // This will be handled in GameScreen
            }
        }
        
        // Check coin collisions
        for (Coin coin : coins) {
            if (checkSphereCollision(player, coin)) {
                coin.collect();
                // Add score
            }
        }
        
        // Check power-up collisions
        for (PowerUp powerUp : powerUps) {
            if (checkSphereCollision(player, powerUp)) {
                powerUp.collect();
                // Apply power-up effect
            }
        }
    }
    
    private boolean checkBoundingBoxCollision(Player player, Obstacle obstacle) {
        Vector3 playerMin = player.getBoundsMin();
        Vector3 playerMax = player.getBoundsMax();
        Vector3 obsMin = obstacle.getBoundsMin();
        Vector3 obsMax = obstacle.getBoundsMax();
        
        return (playerMin.x < obsMax.x && playerMax.x > obsMin.x) &&
               (playerMin.y < obsMax.y && playerMax.y > obsMin.y) &&
               (playerMin.z < obsMax.z && playerMax.z > obsMin.z);
    }
    
    private boolean checkSphereCollision(Player player, Coin coin) {
        Vector3 playerPos = player.getPosition();
        Vector3 coinPos = coin.getPosition();
        float distance = playerPos.dst(coinPos);
        float minDistance = (player.getWidth() / 2f) + coin.getRadius();
        return distance < minDistance;
    }
    
    private boolean checkSphereCollision(Player player, PowerUp powerUp) {
        Vector3 playerPos = player.getPosition();
        Vector3 powerUpPos = powerUp.getPosition();
        float distance = playerPos.dst(powerUpPos);
        float minDistance = (player.getWidth() / 2f) + powerUp.getRadius();
        return distance < minDistance;
    }
}

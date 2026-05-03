package com.runnerqueen.entities;;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Player character - the queen runner
 */
public class Player {
    private Vector3 position;
    private Vector3 velocity;
    private Vector3 acceleration;
    
    private float moveSpeed = 8f;
    private float maxMoveSpeed = 8f;
    private float jumpForce = 15f;
    private float gravity = -20f;
    private float width = 0.8f;
    private float height = 1.8f;
    private float depth = 0.6f;
    
    private boolean isJumping = false;
    private boolean isGrounded = true;
    private float groundLevel = 0f;
    
    private float laneX = 0f; // 0 = center, -1 = left, 1 = right
    private float laneMoveSpeed = 10f;
    private float targetLaneX = 0f;
    
    private float animationTime = 0f;

    public Player() {
        this.position = new Vector3(0, 0, 0);
        this.velocity = new Vector3(0, 0, 0);
        this.acceleration = new Vector3(0, 0, 0);
        this.groundLevel = height / 2f;
    }

    public void update(float delta) {
        // Smooth lane movement
        if (laneX != targetLaneX) {
            float diff = targetLaneX - laneX;
            laneX += Math.signum(diff) * laneMoveSpeed * delta;
            if (Math.abs(laneX - targetLaneX) < 0.1f) {
                laneX = targetLaneX;
            }
        }
        position.x = laneX;
        
        // Apply gravity
        if (!isGrounded) {
            velocity.y += gravity * delta;
        }
        
        // Update position
        position.add(velocity.x * delta, velocity.y * delta, velocity.z * delta);
        
        // Ground collision
        if (position.y <= groundLevel) {
            position.y = groundLevel;
            velocity.y = 0;
            isJumping = false;
            isGrounded = true;
        } else {
            isGrounded = false;
        }
        
        // Keep in bounds
        if (position.x < -1.5f) position.x = -1.5f;
        if (position.x > 1.5f) position.x = 1.5f;
        
        // Animation
        animationTime += delta;
    }

    public void moveLeft(float delta) {
        if (!isJumping) {
            targetLaneX = Math.max(targetLaneX - 1.5f, -1.5f);
        }
    }

    public void moveRight(float delta) {
        if (!isJumping) {
            targetLaneX = Math.min(targetLaneX + 1.5f, 1.5f);
        }
    }

    public void jump() {
        if (isGrounded && !isJumping) {
            velocity.y = jumpForce;
            isJumping = true;
            isGrounded = false;
        }
    }

    public void render(ModelBatch batch) {
        // Simple cube rendering for the player
        // In a production game, you'd load an actual 3D model
        // For now, we'll just indicate the player's position
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getBoundsMin() {
        return new Vector3(
            position.x - width / 2f,
            position.y,
            position.z - depth / 2f
        );
    }

    public Vector3 getBoundsMax() {
        return new Vector3(
            position.x + width / 2f,
            position.y + height,
            position.z + depth / 2f
        );
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getDepth() { return depth; }
    public boolean isGrounded() { return isGrounded; }
    public float getLaneX() { return laneX; }
    public float getAnimationTime() { return animationTime; }

    public void dispose() {
        // Cleanup resources
    }
}

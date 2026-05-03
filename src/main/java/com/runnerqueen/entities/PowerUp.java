package com.runnerqueen.entities;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Power-up entities
 */
public class PowerUp {
    public enum PowerUpType {
        SHIELD,      // Protects from one obstacle
        SPEED_BOOST, // Increases movement speed temporarily
        MAGNET,      // Attracts nearby coins
        INVINCIBLE   // Temporary invulnerability
    }
    
    private Vector3 position;
    private PowerUpType type;
    private float duration = 5f; // Duration in seconds
    private float timeRemaining;
    private float rotationAngle = 0f;
    private boolean collected = false;
    private float bobOffset = 0f;

    public PowerUp(Vector3 position, PowerUpType type) {
        this.position = position.cpy();
        this.type = type;
        this.timeRemaining = duration;
    }

    public void update(float delta) {
        rotationAngle += 240f * delta; // Rotate faster than coins
        if (rotationAngle > 360f) rotationAngle -= 360f;
        
        bobOffset = (float) Math.sin(bobOffset + 4f * delta) * 0.4f;
    }

    public void render(ModelBatch batch) {
        // Render power-up model with glow effect
    }

    public Vector3 getPosition() {
        Vector3 displayPos = position.cpy();
        displayPos.y += bobOffset;
        return displayPos;
    }

    public PowerUpType getType() { return type; }
    public float getRadius() { return 0.3f; }
    public boolean isCollected() { return collected; }
    public void collect() { collected = true; }
    public float getRotationAngle() { return rotationAngle; }
    public float getDuration() { return duration; }
}

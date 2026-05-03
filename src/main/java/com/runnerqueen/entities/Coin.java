package com.runnerqueen.entities;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Collectible coin entities
 */
public class Coin {
    private Vector3 position;
    private float radius = 0.2f;
    private boolean collected = false;
    private float rotationAngle = 0f;
    private float bobOffset = 0f;
    private static final float BOB_SPEED = 3f;
    private static final float BOB_HEIGHT = 0.3f;

    public Coin(Vector3 position) {
        this.position = position.cpy();
    }

    public void update(float delta) {
        // Coin rotates and bobs up and down
        rotationAngle += 180f * delta; // Rotate 180 degrees per second
        if (rotationAngle > 360f) rotationAngle -= 360f;
        
        bobOffset = (float) Math.sin(bobOffset + BOB_SPEED * delta) * BOB_HEIGHT;
    }

    public void render(ModelBatch batch) {
        // Render coin model
    }

    public Vector3 getPosition() {
        Vector3 displayPos = position.cpy();
        displayPos.y += bobOffset;
        return displayPos;
    }

    public float getRadius() { return radius; }
    public boolean isCollected() { return collected; }
    public void collect() { collected = true; }
    public float getRotationAngle() { return rotationAngle; }
}

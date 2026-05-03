package com.runnerqueen.entities;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Obstacle entities that the player must avoid
 */
public class Obstacle {
    public enum ObstacleType {
        BOX, WALL, TRAIN
    }
    
    private Vector3 position;
    private Vector3 scale;
    private ObstacleType type;
    private float width;
    private float height;
    private float depth;
    private boolean active = true;

    public Obstacle(Vector3 position, ObstacleType type) {
        this.position = position;
        this.type = type;
        
        switch (type) {
            case BOX:
                this.width = 0.8f;
                this.height = 0.8f;
                this.depth = 0.8f;
                break;
            case WALL:
                this.width = 3f;
                this.height = 2f;
                this.depth = 0.2f;
                break;
            case TRAIN:
                this.width = 2f;
                this.height = 1.5f;
                this.depth = 0.5f;
                break;
        }
        
        this.scale = new Vector3(width, height, depth);
    }

    public void update(float delta) {
        // Obstacles move backwards (towards the camera)
        // Movement is handled by GameManager
    }

    public void render(ModelBatch batch) {
        // Render obstacle model
        // Implementation depends on 3D model loading
    }

    public Vector3 getPosition() { return position; }
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
    public ObstacleType getType() { return type; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

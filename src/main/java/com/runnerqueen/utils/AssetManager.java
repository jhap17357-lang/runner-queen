package com.runnerqueen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.UBJsonReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages loading and caching of game assets
 */
public class AssetManager {
    private static AssetManager instance;
    private Map<String, Texture> textures;
    private Map<String, Model> models;
    
    private AssetManager() {
        textures = new HashMap<>();
        models = new HashMap<>();
    }
    
    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }
    
    public void loadAssets() {
        // Load textures
        // loadTexture("player", "textures/player.png");
        // loadTexture("coin", "textures/coin.png");
        // loadTexture("obstacle", "textures/obstacle.png");
        
        // Load 3D models
        // loadModel("player", "models/player.g3db");
        // loadModel("coin", "models/coin.g3db");
        // loadModel("obstacle", "models/obstacle.g3db");
    }
    
    public Texture getTexture(String name) {
        return textures.getOrDefault(name, null);
    }
    
    public Model getModel(String name) {
        return models.getOrDefault(name, null);
    }
    
    public void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        for (Model model : models.values()) {
            model.dispose();
        }
        textures.clear();
        models.clear();
    }
}

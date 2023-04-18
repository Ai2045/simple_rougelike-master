package game.entity;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Entity {
    protected final int tileSize = Game.tileSize;
    protected PVector pos;
    protected final HashMap<String, PImage> assets;
    protected int frame;
    protected int orderAssets;
    protected float energy;


    public Entity(PVector pos, HashMap<String, PImage> assets) {
        this.pos = pos;
        this.assets = assets;
        energy = 100.0f;
    }

    public PVector getPos() {
        return pos;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public HashMap<String, PImage> getAssets() {
        return assets;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public abstract void draw(PGraphics gfx);

    public void resetFrame() {
        if (frame == Game.MaxFrame) {
            frame = 0;
            orderAssets++;
        }
    }
    public void resetOderAssets() {
        if (orderAssets > 3) orderAssets = 0;
    }
    public void move(float x, float y) {
        pos.x = x;
        pos.y = y;
    }
}

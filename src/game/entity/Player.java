package game.entity;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public class Player extends Entity {

    private boolean haveKey;
    public Player(PVector pos , HashMap<String, PImage> assets) {
        super(pos, assets);
        haveKey = false;
        frame = 0;
        orderAssets = 0;
    }

    @Override
    public void draw(PGraphics gfx) {
        resetFrame();
        resetOderAssets();
        gfx.image(assets.get("knight"+ orderAssets), pos.x * tileSize, pos.y * tileSize - tileSize / 2);
        frame++;
    }
    public void useBuffPotion() {
        energy += 10;
    }

    public void useDebuffPotion() {
        energy -=  energy*0.1;
    }

    public void isAttacked() {
        if (energy < 10) {
            energy --;
        }
        energy -= energy*0.01;

    }

    public void setKey() {
        haveKey = true;
    }

}

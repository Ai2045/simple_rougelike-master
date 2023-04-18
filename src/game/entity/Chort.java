package game.entity;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public class Chort extends Enemy{
    public Chort(PVector pos, HashMap<String, PImage> assets) {
        super(pos, assets);
    }

    @Override
    public void draw(PGraphics gfx) {
        resetFrame();
        resetOderAssets();
        if (orderAssets > 3) orderAssets = 0;
        gfx.image(assets.get("chort"+orderAssets), pos.x*tileSize - tileSize/2, pos.y*tileSize-tileSize/2);
        frame++;
    }
}

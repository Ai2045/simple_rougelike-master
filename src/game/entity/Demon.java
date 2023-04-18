package game.entity;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public class Demon extends Enemy{
    public Demon(PVector pos, HashMap<String, PImage> assets) {
        super(pos, assets);


    }

    @Override
    public void draw(PGraphics gfx) {
        resetFrame();
        resetOderAssets();
        gfx.image(assets.get("demon"+orderAssets), pos.x*tileSize - tileSize/2, pos.y*tileSize-tileSize/2);
        frame++;
    }


}

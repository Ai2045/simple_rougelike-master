package game.map;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class FloorTile extends Tile{

    private final PImage floorImage;

    public FloorTile(PGraphics gfx, PImage floorImage) {
        super(gfx);
        this.floorImage = floorImage;
        setPassable(true);
    }

    public void draw(int row, int col) {
        gfx.image(floorImage, col*tileSize, row*tileSize);
    }

}

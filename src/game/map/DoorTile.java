package game.map;

import processing.core.PGraphics;
import processing.core.PImage;

public class DoorTile extends Tile{

    private final PImage doorImage;

    public DoorTile(PGraphics gfx, PImage doorImage) {
        super(gfx);
        this.doorImage = doorImage;
        setPassable(false);
    }

    @Override
    public void draw(int row, int col) {
        doorImage.resize(tileSize, tileSize);
        gfx.image(doorImage, col*tileSize, row*tileSize);
    }
}

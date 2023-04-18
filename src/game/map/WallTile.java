package game.map;

import processing.core.PGraphics;
import processing.core.PImage;

public class WallTile extends Tile{

    private final PImage wallImage;

    public WallTile(PGraphics gfx, PImage wallImage) {
        super(gfx);
        this.wallImage = wallImage;
        setPassable(false);
    }

    @Override
    public void draw(int row, int col) {
        gfx.image(wallImage, col*tileSize, row*tileSize);
    }
}

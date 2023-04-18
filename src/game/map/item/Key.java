package game.map.item;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;

public class Key extends Item{
    private final PImage keyImage;

    public Key(PGraphics gfx, PImage keyImage) {
        super(gfx);
        this.keyImage = keyImage;
    }

    @Override
    public void draw(int row, int col) {
        gfx.image(keyImage, col* Game.tileSize, row*Game.tileSize);
    }
}

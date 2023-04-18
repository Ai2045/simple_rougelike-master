package game.map.item;

import game.Game;
import game.map.item.Item;
import processing.core.PGraphics;
import processing.core.PImage;


public class BuffPotion extends Item {
    private PImage potionImage;

    public BuffPotion(PGraphics gfx, PImage potionImage) {
        super(gfx);
        this.potionImage = potionImage;
    }

    @Override
    public void draw(int row, int col) {
        gfx.image(potionImage, col* Game.tileSize, row*Game.tileSize);
    }
}

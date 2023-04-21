package game.map;


import game.Game;
import game.map.item.Item;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Tile {
    protected boolean passable;
    protected final PGraphics gfx;
    protected final int tileSize = Game.tileSize;
    protected boolean Drawable = true;
    private Item item;
    public Tile(PGraphics gfx) {
        this.gfx = gfx;
    }
    protected PVector pos;
    public boolean isPassable() {
        return passable;
    }
    public Item getItem() {
        return item;
    }

    public boolean isDrawable() {
        return Drawable;
    }

    public void setDrawable(boolean drawable) {
        Drawable = drawable;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    abstract public void draw(int row, int col);
    public PVector getPos() {
        return pos;
    }
}

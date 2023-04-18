package game.map.item;

import processing.core.PGraphics;

abstract public class Item {
    protected final PGraphics gfx;
    protected boolean passable;

    public Item(PGraphics gfx) {
        this.gfx = gfx;
        setPassable(true);
    }

    public abstract void draw(int row, int col);

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}

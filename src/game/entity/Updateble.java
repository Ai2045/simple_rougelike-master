package game.entity;

import game.Game;
import processing.core.PGraphics;
import processing.core.PVector;

public interface Updateble {
    void update(Game game);

    void draw(PGraphics gfx);
}

package game;

import game.Game;
import processing.core.PGraphics;

public class TextController {
    private Game game;
    private final PGraphics gfx ;

    public TextController(Game game) {
        this.game = game;
        gfx = game.getGraphics();
    }

    public void nextLevel() {
        gfx.textSize(32);
        gfx.textAlign(Game.CENTER);
        gfx.text("Next level!", game.width / 2, game.height / 2);
        gfx.textSize(20);
        gfx.text("Premi C per continuare o ESC per uscire", game.width / 2, game.height / 2 + 40);
    }

    public void GameOver()  {
        gfx.textSize(32);
        gfx.textAlign(Game.CENTER);
        gfx.text("Game Over!", gfx.width / 2, gfx.height / 2);
        gfx.textSize(20);
        gfx.text("Premi R per riprovare o ESC per uscire", gfx.width / 2, gfx.height / 2 + 40);
    }
}

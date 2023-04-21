package game;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;

public class UIController {
    private Game game;
    private PGraphics gfx ;
    private final HashMap<String, PImage> assets;
    private int numHearts;
    private ArrayList<PImage> healthImages = new ArrayList<>();

    public UIController(Game game) {
        this.game = game;
        gfx = game.getGraphics();
        assets = game.getAssets();
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

    public UIController() {
        numHearts = 5;
        assets = game.getAssets();
        for (int i = 0; i < numHearts; i++) {
            healthImages.add(assets.get("health full"));
        }
    }

    public void heroHealth() {
       var hero = game.getHero();
        var healthPerHeart = hero.getEnergy()/ hero.getMaxEnergy();
        for(int i=0; i<numHearts; i++) {
            if (hero.getEnergy() < (i + 1) * healthPerHeart) {
                // L'eroe ha meno salute di quella rappresentata da questo cuore
                healthImages.set(i, assets.get("health empty"));
            } else {
                // L'eroe ha abbastanza salute per questo cuore
                healthImages.set(i, assets.get("health full"));
            }
        }
        drawHealth();
    }

    public void drawHealth() {
        for (var image:
             healthImages) {
            gfx.image(image, 2*Game.tileSize, 2*Game.tileSize);
        }
        gfx.textAlign(Game.X);
        gfx.text("health", 2*Game.tileSize, 2*Game.tileSize);
    }
}

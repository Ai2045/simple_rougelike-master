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
        numHearts = 5;
        assets = game.getAssets();
        initHearts();
    }

    public void initHearts() {
        if (healthImages.size() == 0) {
            for(int i=0; i<numHearts; i++) {
                healthImages.add(assets.get("heart full"));
            }
        }else {
            for(int i=0; i<numHearts; i++) {
                healthImages.set(i,assets.get("heart full"));
            }
        }

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


    public void heroHealth() {
        var hero = game.getHero();
        var healthPerHeart = hero.getMaxEnergy()/numHearts;
        for(int i=numHearts-1; i>=0; i--) {
            if (hero.getEnergy() < (i) * healthPerHeart) {
                // L'eroe ha meno salute di quella rappresentata da questo cuore
                healthImages.set(i, assets.get("heart empty"));
            }
        }
        drawHealth();

    }

    private void drawHealth() {
        for (int i = 0; i < healthImages.size(); i++) {
            healthImages.get(i).resize(Game.tileSize*2, Game.tileSize*2);
            gfx.image(healthImages.get(i), i*Game.tileSize*2, 5);
        }
    }

}

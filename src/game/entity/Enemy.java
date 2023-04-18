package game.entity;

import game.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public abstract class Enemy extends Entity implements Updateble{

    public Enemy(PVector pos, HashMap<String, PImage> assets) {
        super(pos, assets);
    }

    @Override
    public abstract void draw(PGraphics gfx);

    @Override
    public void update(Game game) {
        var tileMap = game.getTileMap();
        var random = Game.gameAlgoritm;
        var hero = game.getHero();
        var distanceX = hero.getPos().x - pos.x;
        var distanceY = hero.getPos().y - pos.y;

        var direction = random.getNextMove(distanceX, distanceY);
        var intentX = direction.x+ pos.x;
        var intentY = direction.y+ pos.y;
        if (tileMap.isValidMove(intentX, intentY)) {
            move(intentX, intentY);
        }
        if (game.checkCollision(getPos())) {
            hero.isAttacked();
            System.out.println(hero.getEnergy());
        }

    }
}

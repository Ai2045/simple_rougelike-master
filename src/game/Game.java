package game;

import game.entity.*;
import game.map.item.DebuffPotion;
import game.map.item.Item;
import game.map.item.BuffPotion;
import game.map.TileMap;
import game.map.item.Key;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends PApplet {
    public static final int rows = 40;
    public static final int cols = 50;
    public static final int tileSize = 16;
    public static final int MaxFrame = 60;
    private Player hero;
    private List<Updateble> NPCs;
    public static final int WIDTH = cols * tileSize;
    public static final int HEIGHT = rows * tileSize;
    private HashMap<String, PImage> assets;
    private TileMap tileMap;
    public static GameAlgoritm gameAlgoritm;
    private UIController uiController;
    private boolean nextLevel;
    private boolean gameOver;

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        nextLevel = false;
        gameOver = false;
        assets = new HashMap<>();

        assets.put("knight0", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/knight_m_idle_anim_f0.png"));
        assets.put("knight1", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/knight_m_idle_anim_f1.png"));
        assets.put("knight2", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/knight_m_idle_anim_f2.png"));
        assets.put("knight3", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/knight_m_idle_anim_f3.png"));

        assets.put("demon0", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/big_demon_idle_anim_f0.png"));
        assets.put("demon1", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/big_demon_idle_anim_f1.png"));
        assets.put("demon2", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/big_demon_idle_anim_f2.png"));
        assets.put("demon3", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/big_demon_idle_anim_f3.png"));

        assets.put("chort0", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/chort_idle_anim_f0.png"));
        assets.put("chort1", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/chort_idle_anim_f1.png"));
        assets.put("chort2", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/chort_idle_anim_f2.png"));
        assets.put("chort3", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/chort_idle_anim_f3.png"));

        assets.put("elfo", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/elf_m_idle_anim_f1.png"));
        assets.put("goblin", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/goblin_idle_anim_f0.png"));
        assets.put("door", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/doors_leaf_closed.png"));
        assets.put("floor", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/floor_1.png"));
        assets.put("floor2", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/floor_2.png"));
        assets.put("wall", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/wall_mid.png"));
        assets.put("flask red", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/flask_big_red.png"));
        assets.put("flask green", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/flask_big_green.png"));
        assets.put("heart full", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/ui_heart_full.png"));
        assets.put("heart half", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/ui_heart_half.png"));
        assets.put("heart empty", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/ui_heart_empty.png"));

        assets.put("chest", loadImage("assets/0x72_DungeonTilesetII_v1.3.1/frames/chest_empty_open_anim_f0.png"));
        gameAlgoritm = new GameAlgoritm(this);
        tileMap = new TileMap(this);
        uiController = new UIController(this);

        NPCs = new ArrayList<>();
        NPCs.add(new Demon(gameAlgoritm.randomNCPpos(), assets));
        NPCs.add(new Chort(gameAlgoritm.randomNCPpos(), assets));

        hero = new Player(new PVector(cols / 2, rows / 2), assets);
    }

    @Override
    public void draw() {
        if (!gameOver) {
            if(!nextLevel) {

                tileMap.draw();
                hero.draw(getGraphics());
                //System.out.printf("hero ->(%.2f, %.2f)\n", hero.getPos().x, hero.getPos().y);
                for (var npc : NPCs) {
                    npc.update(this);
                    npc.draw(getGraphics());
                    //System.out.printf("npc ->(%.2f, %.2f)\n", npc.getPos().x, npc.getPos().y);
                }
                checkEnegy();
                checkDoor();
                uiController.heroHealth();
            } else {
                // schermata di next level
                uiController.nextLevel();
            }
        } else {
            // schermata di game over
                uiController.GameOver();

        }
    }

    private void checkDoor() {
        var doorPosX = tileMap.getDoorPos().x;
        var doorPosY = tileMap.getDoorPos().y;
        if (hero.getPos().x == doorPosX &&
            hero.getPos().y == doorPosY) {
            nextLevel = true;
            tileMap.setTile((int) doorPosY, (int) doorPosX, null);
        }
    }


    private void checkEnegy() {
        if (hero.getEnergy() <= 0) gameOver = true;
    }

    @Override
    public void keyPressed() {
        isNextLevel();
        isGameOver();
        float intentX = hero.getPos().x;
        float intentY = hero.getPos().y;
        switch (keyCode) {
            case 65 -> intentX -= 1;       //a
            case 68 -> intentX += 1;       //d
            case 83 -> intentY += 1;       //s
            case 87 -> intentY -= 1;       //w
        }

        if (tileMap.isValidMove(intentX, intentY)) {
            var tile = tileMap.getTile((int) intentY, (int) intentX);
            hero.move(intentX, intentY);
            if (checkItem(tile.getItem())) tile.setItem(null);
        }
    }

    private void isNextLevel() {
        if (!nextLevel) return;
        if (keyCode == 67) {
            nextLevel = false;
            hero.setEnergy(100);
            hero.setPos(new PVector(cols / 2, rows / 2));
            tileMap.clear();
            tileMap.initMap(gameAlgoritm);
        }else if (keyCode == ESC) {
            exit();
        }
    }

    private void isGameOver() {
        if (!gameOver) return;
        if (keyCode == 82) {
            gameOver = false;
            hero.setEnergy(100);
            hero.setPos(new PVector(cols / 2, rows / 2));
            tileMap.clear();
            tileMap.initMap(gameAlgoritm);
        }else if (keyCode == ESC) {
            exit();
        }
    }

    public boolean checkItem(Item item) {
        if (item == null) return false;
        System.out.println(hero.getEnergy());
        if (item instanceof BuffPotion) hero.useBuffPotion();
        if (item instanceof DebuffPotion) hero.useDebuffPotion();
        if (item instanceof Key) {
            hero.setKey();
            var doorPos = tileMap.getDoorPos();
            var doorTile = tileMap.getTile((int)doorPos.y, (int)doorPos.x);
            doorTile.setPassable(true);
        }
        System.out.println(hero.getEnergy());
        return true;
    }

    public boolean checkCollision(PVector pos) {
        return hero.getPos().x == pos.x
                && hero.getPos().y == pos.y;
    }
    public TileMap getTileMap() {
        return tileMap;
    }

    public Player getHero() {
        return hero;
    }

    public HashMap<String, PImage> getAssets() {
        return assets;
    }

    public static void main(String[] args) {
        PApplet.main("game.Game");
    }
}
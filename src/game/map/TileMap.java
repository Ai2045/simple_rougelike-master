package game.map;


import game.Game;
import game.GameAlgoritm;
import game.entity.Player;
import game.map.item.BuffPotion;
import game.map.item.DebuffPotion;
import game.map.item.Key;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Arrays;
import java.util.HashMap;

public class TileMap {
    private final int rows = Game.rows;
    private final int cols = Game.cols;
    private final HashMap<String, PImage> assets;
    private Tile[][] tiles;
    private final PGraphics gfx;

    private final PVector doorPos;

    public TileMap(Game game) {
        this.assets = game.getAssets();
        tiles = new Tile[rows][cols];
        this.gfx = game.getGraphics();
        this.doorPos = new PVector(0, 0);
        var random = Game.gameAlgoritm;

        initMap(random);
    }

    public void initMap(GameAlgoritm random) {
        initDoor(random);
        initKey(random);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (tiles[r][c] == null) {
                    if (random.nextInt(100) > 20){
                        if (random.nextBoolean()){
                            tiles[r][c] = new FloorTile(gfx, assets.get("floor1"));
                        }else {
                            tiles[r][c] = new FloorTile(gfx, assets.get("floor2"));
                        }

                        if (random.nextInt(500) < 1) {
                            getTile(r,c).setItem(new BuffPotion(gfx, assets.get("flask red")));
                        } else if (random.nextInt(500) <1) {
                            getTile(r,c).setItem(new DebuffPotion(gfx, assets.get("flask green")));
                        }

                    }else {
                        tiles[r][c] = new WallTile(gfx, assets.get("wall"));
                    }
                }
            }
        }
}
    private void initKey(GameAlgoritm random) {
        int keyX = random.nextInt(cols);
        int keyY = random.nextInt(rows);
        tiles[keyY][keyX] = new FloorTile(gfx, assets.get("floor1"));
        tiles[keyY][keyX].setItem(new Key(gfx, assets.get("chest")));
    }

    public void initDoor(GameAlgoritm random) {

        doorPos.x = random.nextInt(cols);
        doorPos.y = random.nextInt(rows);
        tiles[(int)doorPos.y][(int)doorPos.x] = new DoorTile(gfx, assets.get("door"));
//        tiles[doorY+1][doorX] = new DoorTile(gfx, assets.get("door"));
//        tiles[doorY][doorX+1] = new DoorTile(gfx, assets.get("door"));
//        tiles[doorY+1][doorX+1] = new DoorTile(gfx, assets.get("door"));
//        tiles[doorY+1][doorX].setDrawable(false);
//        tiles[doorY][doorX+1].setDrawable(false);
//        tiles[doorY+1][doorX+1].setDrawable(false);
    }
    public Tile getTile(int rows, int cols) {
        return tiles[rows][cols];
    }

    public boolean isPassable(int rows, int cols) {
        return getTile(rows, cols).isPassable();
    }

    public boolean isValidMove(float x, float y) {
        if (!(x >= 0 && x <= cols-1 && y >= 0 && y <= rows-1)) return false;
        var tile = getTile((int)y, (int)x);
        return tile.isPassable();
    }

    public PVector getDoorPos() {
        return doorPos;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void draw() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (getTile(r,c).isDrawable()){
                    getTile(r, c).draw(r, c);
                    var item = getTile(r,c).getItem();
                    if (item != null) item.draw(r,c);
                }
            }
        }
    }
    public void setTile(int row, int col, Tile t) {
        var tile = getTile(row, col);
        tile = t;
    }
    public void clear() {
       tiles = new Tile[rows][cols];
    }
}

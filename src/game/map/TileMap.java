package game.map;


import game.Game;
import game.GameAlgoritm;
import game.map.item.BuffPotion;
import game.map.item.DebuffPotion;
import game.map.item.Key;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
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
        initWall();
        initFloor(random);
        initItem(random);
        initDoor(random);
        initKey(random);
}
private void initItem(GameAlgoritm random) {
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            var floor = tiles[r][c];
            if (floor instanceof FloorTile) {
                if (random.nextInt(1000) < 5) floor.setItem(new BuffPotion(gfx, assets.get("flask red")));
                if (random.nextInt(1000) < 5) floor.setItem(new BuffPotion(gfx, assets.get("flask green")));
            }

        }
    }
}
//    private void initFloor(GameAlgoritm random) {
//        int x = cols/2;
//        int y = rows/2;
//        int count = 0;
//        do {
//            int dx = random.nextInt(-1, 2);
//            int dy = random.nextInt(-1, 2);
//
//            if (dx != 0 || dy != 0) {
//                x += dx;
//                y += dy;
//
//                if (x >= 0 && x < cols && y >= 0 && y < rows) {
//                    tiles[y][x] = new FloorTile(gfx, assets.get("floor"));
//                    count++;
//                }
//            }
//        } while (count != cols * rows);
//    }

    private void initFloor(GameAlgoritm random) {
            int x = cols/2;
            int y = rows/2;
            int count = 0;
            while (count < rows*cols*1.5) {
                int cx = random.nextInt(1, cols-1);
                int cy = random.nextInt(1, rows-1);


                    if (random.nextBoolean()) {
                        tiles[cy][cx+1] =  new FloorTile(gfx, assets.get("floor"));
                    } else if (random.nextBoolean()) {
                        tiles[cy][cx-1] =  new FloorTile(gfx, assets.get("floor"));
                    } else if (random.nextBoolean()) {
                        tiles[cy+1][cx] =  new FloorTile(gfx, assets.get("floor"));
                    } else if (random.nextBoolean()) {
                        tiles[cy-1][cx] =  new FloorTile(gfx, assets.get("floor"));
                    }
                count++;
            }
        }
private void initWall() {
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            tiles[r][c] = new WallTile(gfx, assets.get("wall"));
        }
    }
}
    private int countFloors() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (tiles[r][c] instanceof FloorTile) count++;
            }
        }
        return count;
    }
        public boolean isNotFloor(int x, int y) {
        return (!(getTile(y, x) instanceof FloorTile));
        }
    private void initKey(GameAlgoritm random) {
        int keyX;
        int keyY;
        do {
            keyX = random.nextInt(cols);
            keyY = random.nextInt(rows);
        } while (isNotFloor(keyX, keyY));
        tiles[keyY][keyX].setItem(new Key(gfx, assets.get("chest")));
    }

    public void initDoor(GameAlgoritm random) {
        do {
            doorPos.x = random.nextInt(cols);
            doorPos.y = random.nextInt(rows);
        } while (isNotFloor((int) doorPos.x, (int) doorPos.y));
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

package game;

import processing.core.PVector;

import java.util.Random;

public class GameAlgoritm extends Random {
    private Game game;

    public GameAlgoritm(Game game){
        this.game = game;
    }
    private int time;
    //algoritm to generate a roguelike map
    public int getDirection(float dis) {
        if (time == 10) {
            time = 0;
            return this.nextInt(-1, 1);
        }
        if (this.nextInt(100) < 10) {
            if (dis > 0) {
                return 1;
            }else if (dis < 0){
                return -1;
            }
        }
        time++;
        return 0;
    }

    public PVector randomNCPpos() {
        var tiles = game.getTileMap().getTiles();
        int x;
        int y;
        do {
            x = this.nextInt(Game.cols);
            y = this.nextInt(Game.rows);
        }while (game.getTileMap().isNotFloor(x, y));
        return new PVector(x, y);
    }
    public PVector getNextMove(float disX, float disY ) {
        PVector direction = new PVector();
        if (this.nextBoolean()){
            direction.x = getDirection(disX);
        }else {
            direction.y = getDirection(disY);
        }
        return direction;
    }
}

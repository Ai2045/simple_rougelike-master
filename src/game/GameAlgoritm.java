package game;

import processing.core.PVector;

import java.util.Random;

public class GameAlgoritm extends Random {
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

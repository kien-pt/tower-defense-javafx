package game.enemy;

public class SmallerEnemy extends BaseEnemy {
    public SmallerEnemy(int posX, int posY, int type) {
        super(posX, posY, "smaller", type,50);
        setFrameAmount(9);
        setSpeed(1.25);
    }
}
package game.enemy;

public class TankerEnemy extends BaseEnemy {
    public TankerEnemy(int posX, int posY, int type) {
        super(posX, posY, "tanker", type,120);
        setFrameAmount(12);
        setSpeed(0.25);
    }
}

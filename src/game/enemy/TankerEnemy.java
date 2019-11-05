package game.enemy;

public class TankerEnemy extends BaseEnemy {
    public TankerEnemy(int posX, int posY, int type) {
        super(posX, posY, "tanker", type);
        setSpeed(0.5);
    }
}

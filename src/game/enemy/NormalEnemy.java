package game.enemy;

public class NormalEnemy extends BaseEnemy {
    public NormalEnemy(int posX, int posY, int type) {
        super(posX, posY, "normal", type,80);
        setFrameAmount(12);
        setSpeed(1);
    }
}

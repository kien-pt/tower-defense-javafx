package game.enemy;

public class BossEnemy extends BaseEnemy {
    public BossEnemy(int posX, int posY, int type) {
        super(posX, posY, "boss", type);
        setFrameAmount(12);
        setSpeed(0.125);
    }
}
package game.enemy;

import game.object.Effect;

public class BossEnemy extends BaseEnemy {
    public BossEnemy(int posX, int posY, int type) {
        super(posX, posY, "boss", type,300, 100);
        setFrameAmount(12);
        setSpeed(0.35);
    }

    @Override
    public Effect getTempDying() {
        return new Effect(posX - 16, posY - 16, "file:resources/Effect/die_boss_", 10, 24);
    }
}
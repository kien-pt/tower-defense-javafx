package game.enemy;

import game.object.Effect;

public class NormalEnemy extends BaseEnemy {
    public NormalEnemy(int posX, int posY, int type) {
        super(posX, posY, "normal", type,80, 50);
        setFrameAmount(12);
        setSpeed(0.75);
    }

    @Override
    public Effect getTempDying() {
        return new Effect(posX + 3, posY + 10, "file:resources/Effect/die_normal_", 5, 24);
    }
}

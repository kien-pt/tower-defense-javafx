package game.enemy;

import game.object.Effect;

public class SmallerEnemy extends BaseEnemy {
    public SmallerEnemy(int posX, int posY, int type) {
        super(posX, posY, "smaller", type,50, 30);
        setFrameAmount(9);
        setSpeed(1.25);
    }

    @Override
    public Effect getTempDying() {
        return new Effect(posX - 8, posY - 8, "file:resources/Effect/die_smaller_", 9, 16);
    }
}
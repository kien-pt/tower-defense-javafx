package game.enemy;

import game.object.Effect;

public class TankerEnemy extends BaseEnemy {
    public TankerEnemy(int posX, int posY, int type) {
        super(posX, posY, "tanker", type,150, 70);
        setFrameAmount(12);
        setSpeed(0.5);
    }

    @Override
    public Effect getTempDying() {
        return new Effect(posX + 5, posY + 5, "file:resources/Effect/die_tanker_", 5, 24);
    }
}

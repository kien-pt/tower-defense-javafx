package game.tower;

import game.gui.UpdateBar;

public class EmptyTower extends BaseTower {

    public EmptyTower(int posX, int posY) {
        super(posX, posY, "empty");
        setRange(0);
        setShoot(false);
    }
}

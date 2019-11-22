package game.tower;

import game.gui.RangeCircle;

public class EmptyTower extends BaseTower {
    public EmptyTower(int posX, int posY) {
        super(posX, posY, "empty");
        setRange(0);
        setPrice(0);
        setStrength(0);
        setShoot(false);
        setSellprice(0);
        setUpgradeprice(0);
        setRangeCircle(new RangeCircle(getXcenter(), posY - 10, getRange()));
    }
}

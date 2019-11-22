package game.tower;

import game.gui.RangeCircle;
import game.soldier.NormalSoldier;

public class NormalTower extends BaseTower {
    public NormalTower(int posX, int posY) {
        super(posX, posY, "normal");
        setRange(180);
        setPrice(200);
        setSpeed(0.75);
        setShoot(false);
        setActive(true);
        setStrength(10);
        setPath("arrow");
        setSellprice(150);
        setUpgradeprice(100);
        setSoldier(new NormalSoldier(posX + 36, posY - 10));
        setRangeCircle(new RangeCircle(getXcenter(), posY - 10, getRange()));
    }
}

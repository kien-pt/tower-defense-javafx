package game.tower;

import game.gui.RangeCircle;
import game.soldier.SniperSoldier;

public class SniperTower extends BaseTower {
    public SniperTower(int posX, int posY) {
        super(posX, posY, "sniper");
        setSpeed(1);
        setPrice(300);
        setRange(200);
        setShoot(false);
        setActive(true);
        setStrength(12);
        setSellprice(200);
        setPath("magebolt");
        setUpgradeprice(150);
        setSoldier(new SniperSoldier(posX + 38, posY - 17));
        setRangeCircle(new RangeCircle(getXcenter(), posY - 10, getRange()));
    }
}

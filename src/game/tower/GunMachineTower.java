package game.tower;

import game.gui.RangeCircle;
import game.soldier.GunMachineSoldier;

public class GunMachineTower extends BaseTower {
    public GunMachineTower(int posX, int posY) {
        super(posX - 4, posY - 20, "gun");
        setRange(150);
        setSpeed(0.5);
        setPrice(500);
        setStrength(7);
        setShoot(false);
        setActive(true);
        setSellprice(300);
        setPath("bullet");
        setUpgradeprice(999);
        setSoldier(new GunMachineSoldier(posX + 36, posY - 20));
        setRangeCircle(new RangeCircle(getXcenter(), posY - 10, getRange()));
    }
}

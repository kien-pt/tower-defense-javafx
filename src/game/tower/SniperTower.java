package game.tower;

import game.object.Effect;
import game.soldier.SniperSoldier;
import javafx.scene.canvas.GraphicsContext;

public class SniperTower extends BaseTower {

    public SniperTower(int posX, int posY) {
        super(posX, posY, "sniper",300,20,150,200);
        setRange(200);
        setShoot(false);
        setActive(true);
        setSpeed(1);
        setPath("magebolt");
        setSoldier(new SniperSoldier(posX + 38, posY - 17));
    }
}

package game.tower;

import game.object.Effect;
import game.soldier.NormalSoldier;
import javafx.scene.canvas.GraphicsContext;

public class NormalTower extends BaseTower {

    public NormalTower(int posX, int posY) {
        super(posX, posY, "normal",200,10,100,150);
        setRange(150);
        setShoot(false);
        setActive(true);
        setSpeed(0.75);
        setPath("arrow");
        setSoldier(new NormalSoldier(posX + 36, posY - 11));
    }
}

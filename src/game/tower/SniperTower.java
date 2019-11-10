package game.tower;

import game.object.Effect;
import game.soldier.SniperSoldier;
import javafx.scene.canvas.GraphicsContext;

public class SniperTower extends BaseTower {
    private Effect buildSmoke;

    public SniperTower(int posX, int posY) {
        super(posX, posY, "sniper");
        setRange(200);
        setShoot(false);
        setActive(true);
        setSpeed(1);
        setSoldier(new SniperSoldier(posX + 38, posY - 20));
        buildSmoke = new Effect(posX + 25, posY + 30, "file:resources/Effect/effect_buildSmoke_", 35, 60);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (buildSmoke != null) buildSmoke.draw(gc);
    }

    @Override
    public void update() {
        super.update();
        if (buildSmoke != null) {
            buildSmoke.update();
            if (buildSmoke.isDestroyed()) buildSmoke = null;
        }
    }
}

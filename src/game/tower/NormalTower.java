package game.tower;

import game.object.Effect;
import javafx.scene.canvas.GraphicsContext;

public class NormalTower extends BaseTower {
    private Effect buildSmoke;

    public NormalTower(int posX, int posY) {
        super(posX, posY, "normal");
        setRange(150);
        setShoot(false);
        setActive(true);
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

package game.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ActiveTower extends Tower {

    public ActiveTower(int posX, int posY, Image image) {
        super(posX, posY, image);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
//        if (effect_buildSmoke != null) effect_buildSmoke.draw(gc);
    }

    @Override
    public void drawLayout(GraphicsContext gc) {
        if (upgradeRing != null) upgradeRing.draw(gc);
    }

    @Override
    public void update() {
        if (upgradeRing != null) upgradeRing.update();
    }

    @Override
    public void setUpgradeRate(int rate) {

    }

    @Override
    public void hover(int mouseX, int mouseY) {

    }

    @Override
    public void attack() {

    }

    @Override
    public void upgrade() {

    }
}

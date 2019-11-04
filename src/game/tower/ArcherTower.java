package game.tower;

import game.object.Icon;
import javafx.scene.image.Image;

public class ArcherTower extends BaseTower {

    public ArcherTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/archer_tower"));
        icons = new Icon[2];
    }

    @Override
    public void update() {
        // if (upgradeRing != null) upgradeRing.update();
    }

    @Override
    public void attack() {

    }

    @Override
    public void upgrade() {

    }
}

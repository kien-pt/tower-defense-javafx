package game.tower;

import game.object.Icon;
import javafx.scene.image.Image;

public class ArcherTower extends BaseTower {

    public ArcherTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/archer_tower.png"));
        icons = new Icon[2];
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        super.onClick(mouseX, mouseY);
        if (click(mouseX, mouseY)) {
//            ring của trụ đã nâng cấp chỉ có 2 icon: nâng cấp và bán
            icons[0] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 0);
            icons[1] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 1);
        }
    }

    @Override
    public void attack() {

    }

    @Override
    public void upgrade() {

    }
}

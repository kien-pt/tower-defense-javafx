package game.tower;

import javafx.scene.image.Image;

public class NormalTower extends ActiveTower {
    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/normal_tower.png"));
    }
}

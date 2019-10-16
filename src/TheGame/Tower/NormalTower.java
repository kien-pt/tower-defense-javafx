package TheGame.Tower;

import javafx.scene.image.Image;

public class NormalTower extends ActiveTower {
    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("TheGame/Resources/normal_tower.png"));
    }
}

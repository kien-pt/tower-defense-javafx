package game.enemy;

import javafx.scene.image.Image;

public class NormalEnemy extends BaseEnemy {
    public NormalEnemy(int posX, int posY, int type) {
        super(posX, posY, "normal_", new Image("file:resources/enemy/normal_0.png"), type);
        speed = 1;
    }
}

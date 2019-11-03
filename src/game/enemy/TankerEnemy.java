package game.enemy;

import javafx.scene.image.Image;

public class TankerEnemy extends BaseEnemy {
    public TankerEnemy(int posX, int posY, int type) {
        super(posX, posY, "tanker_", new Image("file:resources/enemy/tanker_0.png"), type);
        speed = 5;
    }
}

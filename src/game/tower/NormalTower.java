package game.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NormalTower extends ActiveTower {
    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/normal_tower.png"));
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}

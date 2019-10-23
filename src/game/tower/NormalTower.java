package game.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.troops.Bullet;

public class NormalTower extends ActiveTower {
    private Bullet bullet;
    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/normal_tower.png"));
        this.bullet = new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        bullet.draw(gc);
        bullet.MoveBullet(500, 500);
    }
}

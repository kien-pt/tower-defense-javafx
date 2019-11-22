package game.soldier;

import game.object.Effect;
import game.object.GameObject;
import javafx.scene.image.Image;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;

public class BaseSoldier extends GameObject implements UpdatableObject {
    private int frameAmount;
    private boolean shooting;
    private String tag, direction;
    private Effect shootingSoldier;

    BaseSoldier(int posX, int posY, String tag) {
        super(posX, posY, new Image("file:resources/soldier/" + tag + "_idle.png"));
        shooting = false;
        this.tag = tag;
    }

    @Override
    public void update() {
        if (shootingSoldier != null) {
            shootingSoldier.update();
            if (shootingSoldier.isDestroyed()) {
                shooting = false;
                shootingSoldier = null;
                setImage(new Image("file:resources/soldier/" + tag + "_" + direction + "_idle.png"));
            }
        }
        if (shooting && shootingSoldier == null) {
            shootingSoldier = new Effect(posX, posY, "file:resources/soldier/" + tag + "_" + direction + "_", frameAmount, 6 * frameAmount);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (shootingSoldier != null) shootingSoldier.draw(gc); else super.draw(gc);
    }

    public void setShooting(boolean shooting) { this.shooting = shooting; }
    void setFrameAmount(int frameAmount) { this.frameAmount = frameAmount; }
    public void setDirection(String direction) { this.direction = direction; }
}

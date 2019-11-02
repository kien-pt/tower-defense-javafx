package game.tower;

import game.object.GameObject;
import game.object.Ring;
import game.object.UpdatableObject;
import javafx.scene.image.Image;

public abstract class Tower extends GameObject implements UpdatableObject, BaseTower {
//    protected int attackRange;
    protected Ring upgradeRing;

//    protected int nEffect_buildSmoke;
//    protected GameObject effect_buildSmoke;

    Tower(int posX, int posY, Image image) {
        super(posX, posY, image);
    }

    public void onClick(int mouseX, int mouseY) {
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            upgradeRing = new Ring(x, y);
        } else upgradeRing = null;
    }

//    public void draw(GraphicsContext gc) {
//        super.draw(gc);
//    }

    //public abstract void drawLayout(GraphicsContext gc);
}

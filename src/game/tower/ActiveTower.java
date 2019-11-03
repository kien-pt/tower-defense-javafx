package game.tower;

import game.object.Effect;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.object.UpdatableObject;
import game.object.Ring;
import game.object.GameObject;

public class ActiveTower extends GameObject implements UpdatableObject, BaseTower {
    private GameObject ring;
    private Effect effect_buildSmoke;

    public ActiveTower(int posX, int posY, Image image) {
        super(posX, posY, image);
        String url = "file:resources/Effect/effect_buildSmoke_";
        effect_buildSmoke = new Effect(posX + 25, posY + 30, url, 35, 60);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            ring = new Ring(x, y);
        } else ring = null;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (effect_buildSmoke != null) {
            effect_buildSmoke.draw(gc);
            System.out.println("ok");
        }
    }

    @Override
    public void drawLayout(GraphicsContext gc) {
        if (ring != null) ring.draw(gc);
    }

    @Override
    public void update() {
        if (effect_buildSmoke != null) {
            effect_buildSmoke.update();
            if (effect_buildSmoke.isDestroyed()) effect_buildSmoke = null;
        }
        if (ring != null) ((Ring) ring).update();
    }

    @Override
    public void setUpgradeRate(int rate) {

    }

    @Override
    public boolean isUpgrade() {
        return false;
    }

    @Override
    public void hover(int mouseX, int mouseY) {

    }
}

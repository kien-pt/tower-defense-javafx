package game.tower;

import gui.Ring;

import java.util.ArrayList;
import game.enemy.BaseEnemy;
import game.object.GameObject;
import gui.UpdateBar;
import javafx.scene.image.Image;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;

public class EmptyTower extends GameObject implements UpdatableObject, BaseTower {
    private UpdateBar updateBar;
    private Ring ring;

    public EmptyTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/empty_tower.png"));
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }

    public void drawLayout(GraphicsContext gc) {
        // Vẽ thanh nâng cấp
        if (updateBar != null) updateBar.draw(gc);
        // Vẽ vòng tròn chọn lựa
        if (ring != null) ring.draw(gc);
    }

    public void update() {
        if (updateBar != null) updateBar.update();
        // Nếu có vòng lựa chọn thì update
        if (ring != null) ring.update();
    }


    public void onClick(int mouseX, int mouseY) {
        if (ring != null) {
            System.out.println(ring.getUpgrade());
            ring.onClick(mouseX, mouseY);
            if (ring.getUpgrade() >= 0) {
                updateBar = new UpdateBar(posX + 20, posY - 15);
            }
        }
        // Nếu Click vào tòa tháp sẽ hiện lên vòng tròn
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            ring = new Ring(x, y, 0);
        } else ring = null;
    }

    public void hover(int mouseX, int mouseY) {

        if (ring != null) ring.hover(mouseX, mouseY);
    }


    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    public boolean isUpgrade() {
        return (updateBar != null && updateBar.isDone());
    }
}

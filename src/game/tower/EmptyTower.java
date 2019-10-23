package game.tower;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.object.Icon;
import game.object.UpdatableObject;
import game.object.Ring;
import game.object.GameObject;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class EmptyTower extends GameObject implements UpdatableObject, BaseTower {
    private GameObject ring, buildBarBg, buildBar;
    private Icon[] icons = new Icon[4];
    private boolean upgrade;
    private int upgradeRate = -1;

    public EmptyTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/empty_tower.png"));
        this.upgrade = false;
        buildBarBg = new GameObject(posX + 20, posY - 15, new Image("file:resources/buildBar_bg.png"));
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }

    public void drawLayout(GraphicsContext gc) {
        // Vẽ vòng tròn chọn lựa
        if (ring != null) {
            ring.draw(gc);
            for (Icon i: icons) i.draw(gc);
        }
        // Vẽ thanh nâng cấp
        if (upgradeRate >= 0) {
            buildBarBg.draw(gc);
            buildBar.draw(gc);
        }
    }

    public void update() {
        if (upgradeRate >= 0) {
            Image tempImage = new Image("file:resources/buildBar.png", 54 * upgradeRate / 100, 8, false, false);
            buildBar = new GameObject(posX + 22, posY - 13, tempImage);
            // Nếu nâng cấp đủ 100% thì dừng
            if ((upgradeRate += 2) > 100) {
                upgradeRate = -1;
                upgrade = true;
            } else upgrade = false;
        }
        // Nếu có vòng lựa chọn thì update
        if (ring != null) {
            ((Ring) ring).update();
            for (Icon icon: icons) icon.update();

            int x_left = ring.getPosX();
            int x_right = ring.getPosX() + (int) ring.getImage().getWidth() - (int) icons[1].getImage().getWidth();
            int y_up = ring.getPosY();
            int y_down = ring.getPosY() + (int) ring.getImage().getHeight() - (int) icons[2].getImage().getHeight();
            icons[0].setPosX(x_left);
            icons[0].setPosY(y_up);
            icons[1].setPosX(x_right);
            icons[1].setPosY(y_up);
            icons[2].setPosX(x_left);
            icons[2].setPosY(y_down);
            icons[3].setPosX(x_right);
            icons[3].setPosY(y_down);
        }
    }

    public void onClick(int mouseX, int mouseY) {
        if (ring != null) for (Icon icon: icons) icon.onClick(mouseX, mouseY, this);

        // Nếu Click vào tòa tháp sẽ hiện lên vòng tròn
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            ring = new Ring(x, y);

            icons[0] = new Icon(ring.getPosX(), ring.getPosY(), 0);
            icons[1] = new Icon(ring.getPosX(), ring.getPosY(), 1);
            icons[2] = new Icon(ring.getPosX(), ring.getPosY(), 2);
            icons[3] = new Icon(ring.getPosX(), ring.getPosY(), 3);
        } else ring = null;
    }

    public void hover(int mouseX, int mouseY) {
        if (ring != null) for (Icon icon: icons) icon.hover(mouseX, mouseY);
    }

    @Override
    public void setUpgradeRate(int upgradeRate) {
        this.upgradeRate = upgradeRate;
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    @Override
    public boolean isUpgrade() {
        return upgrade;
    }
}

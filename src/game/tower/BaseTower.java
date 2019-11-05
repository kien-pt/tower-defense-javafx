package game.tower;

import game.enemy.BaseEnemy;
import game.object.GameObject;
import game.object.Icon;
import game.object.Ring;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class BaseTower extends GameObject implements UpdatableObject, ClickableObject {
    public int attackRange;
    public GameObject soldier;
    public Icon[] icons;
    Ring upgradeRing;
    ArrayList<BaseEnemy> targets = new ArrayList<>();
    private Ring ring;

    BaseTower(int posX, int posY, Image image) {
        super(posX, posY, image);
    }

    public void drawLayout(GraphicsContext gc) {

    }

    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }

    public void onClick(int mouseX, int mouseY) {
        if (upgradeRing != null) for (Icon icon : icons) icon.onClick(mouseX, mouseY, this);
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            upgradeRing = new Ring(x, y);
        } else upgradeRing = null;
    }

    public void hover(int mouseX, int mouseY) {
        if (upgradeRing != null) for (Icon icon : icons) icon.hover(mouseX, mouseY);
    }

    public abstract void findTargets();
//        cập nhật targets[];

    public abstract void attack();
//        tấn công dựa trên targets[]

    public abstract void upgrade();

//    public void setUpgradeRate(int upgradeRate) {
//        this.upgradeRate = upgradeRate;
//    }
}

package game.tower;

import game.object.GameObject;
import game.object.Icon;
import game.object.Ring;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class BaseTower extends GameObject implements UpdatableObject {
    public final GameObject buildBarBg = new GameObject(posX + 28, posY - 15, new Image("file:resources/buildBar_bg.png"));
    public int attackRange;
    public GameObject watchman;
    public Ring upgradeRing;
    public GameObject buildBar;
    public Icon[] icons = new Icon[4];
    public int upgradeRate = -1;

    BaseTower(int posX, int posY, Image image) {
        super(posX, posY, image);
    }

    public void drawLayout(GraphicsContext gc) {
        if (upgradeRing != null) {
            upgradeRing.draw(gc);
            for (Icon i : icons) i.draw(gc);
        }
        if (upgradeRate >= 0) {
            buildBarBg.draw(gc);
            buildBar.draw(gc);
        }
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

            icons[0] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 0);
            icons[1] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 1);
            icons[2] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 2);
            icons[3] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 3);
        } else upgradeRing = null;
    }
}

package game.gui;

import game.tower.*;
import game.object.GameObject;
import javafx.scene.image.Image;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;


public class Ring extends GameObject implements UpdatableObject {
    private Icon[] icons;
    private int upgrade, iconAmount;

    public Ring(int xCenter, int yCenter, int tag, Object caller) {
        super(new Image("file:resources/gui_ring.png"));
        scale = 0;
        upgrade = -1;
        setXcenter(xCenter);
        setYcenter(yCenter);
        if (tag == 0) iconAmount = 4; else iconAmount = 2;
        icons = new Icon[iconAmount];
        if (iconAmount == 4) for (int i = 0; i < iconAmount; i++) icons[i] = new Icon(-100, -100, i);
        else {
            for (int i = 0; i < iconAmount; i++) icons[i] = new Icon(-100, -100, i + 4);
            if (((BaseTower) caller).getRank() == 3 || ((caller instanceof GunMachineTower)))
                icons[0] = new Icon(-100, -100, 3);
            else icons[0].setText1(String.valueOf(((BaseTower) caller).getUpgradeprice()));
            icons[1].setText1(String.valueOf(((BaseTower) caller).getSellprice()));
        }
    }

    public void onClick(int mouseX, int mouseY, Object caller) {
        for (Icon icon : icons) {
            upgrade = icon.onClick(mouseX, mouseY, caller);
            if (upgrade >= 0) break;
        }
    }

    public void onHover(int mouseX, int mouseY, Object caller) {
        if (iconAmount == 2) {
            icons[0].setText1(String.valueOf(((BaseTower) caller).getUpgradeprice()));
            icons[1].setText1(String.valueOf(((BaseTower) caller).getSellprice()));
        }
        for (Icon icon : icons) icon.onHover(mouseX, mouseY, caller);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        for (Icon icon : icons) icon.draw(gc);
    }

    // Zoom vào cái vòng tròn chọn Icon
    public void update() {
        if (scale < 0.7) {
            scale += 0.1;
            setPosX(getPosX() + (int) getImage().getWidth() / 2);
            setPosY(getPosY() + (int) getImage().getHeight() / 2);
            setImage(new Image("file:resources/gui_ring.png", getWidth() * scale, getHeight() * scale, false, true));
            setPosX(getPosX() - (int) getImage().getWidth() / 2);
            setPosY(getPosY() - (int) getImage().getHeight() / 2);
        }

        for (Icon icon : icons) icon.update();

        if (iconAmount == 2) {
            int x_l = getPosX() - (int) icons[0].getImage().getWidth() / 2 + 10;
            int y = getPosY() + (int) getImage().getWidth() / 2 - (int) icons[0].getImage().getHeight() / 2;
            int x_r = getPosX() + (int) getImage().getWidth() - (int) icons[0].getImage().getWidth() / 2 - 10;
            icons[0].setPosX(x_r);
            icons[0].setPosY(y);
            icons[1].setPosX(x_l);
            icons[1].setPosY(y);

        } else {
            int x_left = getPosX();
            int x_right = getPosX() + (int) getImage().getWidth() - (int) icons[1].getImage().getWidth();
            int y_up = getPosY();
            int y_down = getPosY() + (int) getImage().getHeight() - (int) icons[2].getImage().getHeight();
            icons[0].setPosX(x_left);
            icons[0].setPosY(y_up);
            icons[0].setText1("200");

            icons[1].setPosX(x_right);
            icons[1].setPosY(y_up);
            icons[1].setText1("300");

            icons[2].setPosX(x_left);
            icons[2].setPosY(y_down);
            icons[2].setText1("500");

            icons[3].setPosX(x_right);
            icons[3].setPosY(y_down);
        }
    }

    public int getUpgrade() { return upgrade; }
}

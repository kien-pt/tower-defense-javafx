package game.gui;

import game.object.GameObject;
import game.object.UpdatableObject;
import game.tower.BaseTower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Icon extends GameObject implements UpdatableObject {
    private int tag;
    private int range;
    private boolean isOn;
    private String imageOn, imageOff;

    public Icon(int posX, int posY, int tag) {
        super(posX, posY, new Image("file:resources/icon/icon_" + tag + "_off.png"));
        this.tag = tag;
        range = 0;
        scale = 0;
        isOn = false;
        imageOn = "file:resources/icon/icon_" + tag + ".png";
        imageOff = "file:resources/icon/icon_" + tag + "_off.png";
    }
    public int onClick(int mouseX, int mouseY, Object caller) {
        if (hover(mouseX, mouseY)) return tag;
        return -1;
    }

    public void onHover(int mouseX, int mouseY, Object caller) {
        if (caller instanceof BaseTower) {
            BaseTower tower = (BaseTower) caller;
            if (hover(mouseX, mouseY)) {
                tower.setRangeCircle(new RangeCircle(tower.getXcenter(), tower.getYcenter(), getRange()));
            } else tower.setRangeCircle(new RangeCircle(tower.getXcenter(), tower.getYcenter(), tower.getRange()));
        }

        if (hover(mouseX, mouseY)) {
            if (!isOn) setImage(new Image(imageOn, getWidth() * scale, getHeight() * scale, false, true));
            isOn = true;
        } else {
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
            isOn = false;
//            if (caller != null) {
//                tower = (BaseTower) caller;
//                tower.setRangeCircle(null);
//            }
        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void update() {
        if (scale < 0.65) {
            scale += 0.05;
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}

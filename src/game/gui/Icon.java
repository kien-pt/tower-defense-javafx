package game.gui;

import game.object.GameObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Icon extends GameObject implements UpdatableObject {
    private int tag;
    private boolean isOn;
    private String imageOn, imageOff;

    public Icon(int posX, int posY, int tag) {
        super(posX, posY, new Image("file:resources/icon/icon_" + tag + "_off.png"));
        this.tag = tag;
        scale = 0;
        isOn = false;
        imageOn = "file:resources/icon/icon_" + tag + ".png";
        imageOff = "file:resources/icon/icon_" + tag + "_off.png";
    }

    public int onClick(int mouseX, int mouseY) {
        if (hover(mouseX, mouseY)) return tag;
        return -1;
    }

    public void onHover(int mouseX, int mouseY) {
        if (hover(mouseX, mouseY)) {
            if (!isOn) setImage(new Image(imageOn, getWidth() * scale, getHeight() * scale, false, true));
            isOn = true;
        } else {
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
            isOn = false;
        }
    }

    public void update() {
        if (scale < 1) {
            scale += 0.05;
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}

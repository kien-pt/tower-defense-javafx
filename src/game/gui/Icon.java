package game.gui;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import game.object.GameObject;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;

public class Icon extends GameObject implements UpdatableObject {
    private int tag;
    private Text text1;
    private boolean isOn;
    private String imageOn, imageOff;

    public Icon(int posX, int posY, int tag) {
        super(posX, posY, new Image("file:resources/icon/icon_" + tag + "_off.png"));
        scale = 0;
        isOn = false;
        this.tag = tag;
        text1 = new Text();
        imageOn = "file:resources/icon/icon_" + tag + ".png";
        imageOff = "file:resources/icon/icon_" + tag + "_off.png";
    }
    public int onClick(int mouseX, int mouseY, Object caller) {
        if (tag == 3 || !hover(mouseX, mouseY)) return -1;
        return tag;
    }

    public void onHover(int mouseX, int mouseY, Object caller) {
        if (hover(mouseX, mouseY)) {
            if (!isOn) setImage(new Image(imageOn, getWidth() * scale, getHeight() * scale, false, true));
            isOn = true;
        } else {
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
            isOn = false;
        }
    }


    public void update() {
        if (scale < 0.7) {
            scale += 0.05;
            setImage(new Image(imageOff, getWidth() * scale, getHeight() * scale, false, true));
        }
    }


    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (tag != 3) {
            Font myFont = Font.loadFont("file:resources/fast_99/fast99.ttf", 13 * scale / 0.7);
            gc.setFont(myFont);
            gc.setFill(Color.WHITE);
            gc.fillText(text1.getText(), posX + 22, posY + 71);
        }
    }

    void setText1(String text) { this.text1.setText(text); }
}

package TheGame.Sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import TheGame.Tower.BaseTower;

public class Icon extends GameObject {
    private int tag;
    private boolean isOn;
    private String imageURL_on, imageURL_off;

    public Icon(int posX, int posY, int tag) {
        super(posX, posY, new Image("TheGame/Resources/icon_" + tag + "_off.png"));
        this.tag = tag;
        scale = 0;
        isOn = false;
        imageURL_on = "TheGame/Resources/icon_" + tag + ".png";
        imageURL_off = "TheGame/Resources/icon_" + tag + "_off.png";
    }

    public void onClick(int mouseX, int mouseY, BaseTower tower) {
        if (click(mouseX, mouseY)) tower.setUpgradeRate(0);
    }

    public void hover(int mouseX, int mouseY) {
        if (click(mouseX, mouseY)) {
            if (!isOn) setImage(new Image(imageURL_on, getWidth() * scale, getHeight() * scale, false, true));
            isOn = true;
        } else {
            setImage(new Image(imageURL_off, getWidth() * scale, getHeight() * scale, false, true));
            isOn = false;
        }
    }

    public void update() {
        if (scale < 0.8) {
            scale += 0.05;
            setImage(new Image(imageURL_off, getWidth() * scale, getHeight() * scale, false, true));
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}

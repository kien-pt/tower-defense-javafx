package game.object;

import javafx.scene.image.Image;

public class Ring extends GameObject implements UpdatableObject {
    public Ring(int posX, int posY) {
        super(posX, posY, new Image("file:resources/gui_ring.png"));
        scale = 0;
    }

    // Zoom vào cái vòng tròn chọn Icon
    public void update() {
        if (scale < 0.8) {
            scale += 0.1;
            setPosX(getPosX() + (int) getImage().getWidth() / 2);
            setPosY(getPosY() + (int) getImage().getHeight() / 2);
            setImage(new Image("file:resources/gui_ring.png", getWidth() * scale, getHeight() * scale, false, true));
            setPosX(getPosX() - (int) getImage().getWidth() / 2);
            setPosY(getPosY() - (int) getImage().getHeight() / 2);
        }

    }
}

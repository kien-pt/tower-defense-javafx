package game.animation;

import javafx.scene.image.Image;

public abstract class GameObject extends StaticObject {
    public GameObject(int posX, int posY, Image image) {
        super(posX, posY, image);
    }

    protected boolean click(int mouseX, int mouseY) {
        return (getPosX() <= mouseX && mouseX <= getPosX() + getImage().getWidth()
                && getPosY() <= mouseY && mouseY <= getPosY() + getImage().getHeight());
    }

    public abstract void update();
}

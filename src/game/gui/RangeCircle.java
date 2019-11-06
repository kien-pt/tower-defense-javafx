package game.gui;

import game.object.GameObject;
import javafx.scene.image.Image;

public class RangeCircle extends GameObject {
    public RangeCircle(int posX, int posY) {
        super(posX, posY, new Image("file:resources/range_circle.png"));
    }
}

package game.gui;

import game.object.GameObject;
import javafx.scene.image.Image;

public class RangeCircle extends GameObject {
    public RangeCircle(int x, int y) {
        super(new Image("file:resources/range_circle.png"));
        setXcenter(x);
        setYcenter(y);
    }
}

package game.gui;

import game.object.GameObject;
import javafx.scene.image.Image;

public class RangeCircle extends GameObject {
    private static final int baseRange = 150;
    private static final int width = 289;
    private static final int height = 289;

    public RangeCircle(int x, int y, double range) {
/*
    do ring và icon gọi hàm setImage nên phải setImage sau khi khởi tạo chứ không khởi tạo trực tiếp từ ảnh được
    vì khi đó getwidth và getheight = 0 > không setXcenter và setYcenter được, và icon và ring bị loạn lên
*/
        super(new Image("file:resources/range_circle.png"));
        setImage(new Image("file:resources/range_circle.png", height * range / baseRange + 1, width * range / baseRange + 1, false, false));
        setXcenter(x);
        setYcenter(y);
    }
}

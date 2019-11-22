package game.soldier;

import javafx.scene.image.Image;

public class GunMachineSoldier extends BaseSoldier {
    public GunMachineSoldier(int posX, int posY) {
        super(posX, posY, "gun");
        setDirection("I");
        setFrameAmount(12);
        setImage(new Image("file:resources/soldier/gun_IV_idle.png"));
    }
}

package game.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ActiveTower extends Tower {
//    private int nEffect_buildSmoke;
    //    private GameObject ring;
//    kế thừa ring từ
//    private GameObject effect_buildSmoke;

    public ActiveTower(int posX, int posY, Image image) {
        super(posX, posY, image);
//        Image img = new Image("file:resources/Effect/buildSmoke_0.png");
//        effect_buildSmoke = new GameObject(posX + 25, posY + 30, img);
    }


//    kế thừa từ onClick của Tower
//    public void onClick(int mouseX, int mouseY) {
//        if (click(mouseX, mouseY)) {
//            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
//            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
//            upgradeRing = new Ring(x, y);
//        } else upgradeRing = null;
//    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
//        if (effect_buildSmoke != null) effect_buildSmoke.draw(gc);
    }

    @Override
    public void drawLayout(GraphicsContext gc) {
        if (upgradeRing != null) upgradeRing.draw(gc);
    }

    @Override
    public void update() {
//        nEffect_buildSmoke++;
//        if (nEffect_buildSmoke < 35) {
//            Image img = new Image("file:resources/smoke/buildSmoke_" + nEffect_buildSmoke / 5 + ".png");
//            effect_buildSmoke.setImage(img);
//        } else effect_buildSmoke = null;
        if (upgradeRing != null) upgradeRing.update();
    }

    @Override
    public void setUpgradeRate(int rate) {

    }

    @Override
    public void hover(int mouseX, int mouseY) {

    }

    @Override
    public void attack() {

    }

    @Override
    public void upgrade() {

    }
}

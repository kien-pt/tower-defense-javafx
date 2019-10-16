package TheGame.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import TheGame.Sprite.GameObject;
import TheGame.Sprite.Ring;
import TheGame.Sprite.StaticObject;

public class ActiveTower extends GameObject implements BaseTower{
    private int nEffect_buildSmoke;
    private GameObject ring;
    private StaticObject effect_buildSmoke;

    public ActiveTower(int posX, int posY, Image image) {
        super(posX, posY, image);
        Image img = new Image("TheGame/Resources/Effect/effect_buildSmoke_0.png");
        effect_buildSmoke = new StaticObject(posX + 25, posY + 30, img);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            ring = new Ring(x, y);
            System.out.println(ring.scale);
        } else ring = null;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (effect_buildSmoke != null) effect_buildSmoke.draw(gc);
    }

    @Override
    public void drawLayout(GraphicsContext gc) {
        if (ring != null) {
            ((Ring) ring).update();
            ring.draw(gc);
        }
    }

    @Override
    public void update() {
        nEffect_buildSmoke++;
        if (nEffect_buildSmoke < 35) {
            Image img = new Image("TheGame/Resources/Effect/effect_buildSmoke_" + nEffect_buildSmoke / 5 + ".png");
            effect_buildSmoke.setImage(img);
        } else effect_buildSmoke = null;
    }

    @Override
    public void setUpgradeRate(int rate) {

    }

    @Override
    public boolean isUpgrade() {
        return false;
    }

    @Override
    public void hover(int mouseX, int mouseY) {

    }
}

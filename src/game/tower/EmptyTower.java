package game.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.object.Icon;
import game.object.UpdatableObject;
import game.object.Ring;
import game.object.GameObject;

public class EmptyTower extends Tower implements UpdatableObject, BaseTower {

    private GameObject buildBarBg, buildBar;
    private int nEffect_buildSmoke;
    //private GameObject ring;
    //ring ke thua tu upgradeRing của Tower
    private GameObject effect_buildSmoke;

    private Icon[] icons = new Icon[4];
    //private boolean upgrade;
    private int upgradeRate = -1;

    public EmptyTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/empty_tower.png"));
//        attackRange = 0;
        //this.upgrade = false;
        buildBarBg = new GameObject(posX + 28, posY - 15, new Image("file:resources/buildBar_bg.png"));
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (effect_buildSmoke != null) effect_buildSmoke.draw(gc);
    }

    //cài đặt từ interface BaseTower
    public void drawLayout(GraphicsContext gc) {
        // Vẽ vòng tròn chọn lựa
        if (upgradeRing != null) {
            upgradeRing.draw(gc);
            for (Icon i: icons) i.draw(gc);
        }
        // Vẽ thanh nâng cấp
        if (upgradeRate >= 0) {
            buildBarBg.draw(gc);
            buildBar.draw(gc);
        }
    }

    //  kiểm tra liệu 1 tháp có thể nâng cấp, nếu có thì nâng cấp
    public void upgrade() {
        // hiển thị tùy chọn
        if (upgradeRing != null) {
            upgradeRing.update();
            for (Icon icon: icons) icon.update();

            int x_left = upgradeRing.getPosX();
            int x_right = upgradeRing.getPosX() + (int) upgradeRing.getImage().getWidth() - (int) icons[1].getImage().getWidth();
            int y_up = upgradeRing.getPosY();
            int y_down = upgradeRing.getPosY() + (int) upgradeRing.getImage().getHeight() - (int) icons[2].getImage().getHeight();

            icons[0].setPosX(x_left);
            icons[0].setPosY(y_up);
            icons[1].setPosX(x_right);
            icons[1].setPosY(y_up);
            icons[2].setPosX(x_left);
            icons[2].setPosY(y_down);
            icons[3].setPosX(x_right);
            icons[3].setPosY(y_down);
        }

        if (upgradeRate >= 0) {
            Image tempImage = new Image("file:resources/buildBar.png", 54 * upgradeRate / 100, 8, false, false);
            buildBar = new GameObject(posX + 30, posY - 13, tempImage);
//          Nếu nâng cấp đủ 100% thì dừng
            if ((upgradeRate += 2) > 100) {
                upgradeRate = -1;

//              xây trụ thực ra là đổi ảnh và tăng tầm bắn từ 0 lên x
                setImage("file:resources/tower/normal_tower.png");
//                khói
//                Image img = new Image("file:resources/smoke/buildSmoke_0.png");
//                effect_buildSmoke = new GameObject(posX + 25, posY + 30, img);

            }
        }


    }


    public void onClick(int mouseX, int mouseY) {
        if (upgradeRing != null) for (Icon icon : icons) icon.onClick(mouseX, mouseY, this);

        // Nếu Click vào tòa tháp sẽ hiện lên vòng tròn
        if (click(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            upgradeRing = new Ring(x, y);

            icons[0] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 0);
            icons[1] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 1);
            icons[2] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 2);
            icons[3] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 3);
        } else upgradeRing = null;
    }

    @Override
    public void attack() {
        //trụ trống không thể tấn công
    }

    @Override
    public void update() {
//      do không thể tấn công nên update của empty_tower chỉ là kiểm tra xem có nâng cấp hay không
        upgrade();
    }

    public void hover(int mouseX, int mouseY) {
        if (upgradeRing != null) for (Icon icon : icons) icon.hover(mouseX, mouseY);
    }

    @Override
    public void setUpgradeRate(int upgradeRate) {
        this.upgradeRate = upgradeRate;
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }
}

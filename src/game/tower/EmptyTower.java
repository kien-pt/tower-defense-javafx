package game.tower;

import game.object.GameObject;
import game.object.Icon;
import javafx.scene.image.Image;

public class EmptyTower extends BaseTower {

    public EmptyTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/empty_tower.png"));
        icons = new Icon[4];
    }

    public void upgrade() {
        // hiển thị tùy chọn
        if (upgradeRing != null) {
            upgradeRing.update();
            for (Icon icon: icons) icon.update();

            int x_left = upgradeRing.getPosX();
            int x_right = upgradeRing.getPosX() + (int) upgradeRing.getImage().getWidth() - (int) icons[1].getImage().getWidth();
            int y_up = upgradeRing.getPosY();
            int y_down = upgradeRing.getPosY() + (int) upgradeRing.getImage().getHeight() - (int) icons[2].getImage().getHeight();

            icons[0].setPosX(x_left).setPosY(y_up);
            icons[1].setPosX(x_right).setPosY(y_up);
            icons[2].setPosX(x_left).setPosY(y_down);
            icons[3].setPosX(x_right).setPosY(y_down);
        }

        if (upgradeRate >= 0) {
            Image tempImage = new Image("file:resources/buildBar.png", 54 * upgradeRate / 100, 8, false, false);
            buildBar = new GameObject(posX + 30, posY - 13, tempImage);
//          Nếu nâng cấp đủ 100% thì dừng
            if ((upgradeRate += 2) > 100) {
                upgradeRate = -1;

//              xây trụ thực ra là đổi ảnh và tăng tầm bắn từ 0 lên x
                setImage("file:resources/tower/archer_tower.png");
            }
        }
    }

    @Override
    public void attack() {
        //trụ trống không thể tấn công
    }

    @Override
    public void update() {
//      do không thể tấn công nên update của emptytower chỉ là kiểm tra xem có nâng cấp hay không
        upgrade();
    }
}

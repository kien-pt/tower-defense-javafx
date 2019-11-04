package game.tower;

import game.object.GameObject;
import game.object.Icon;
import javafx.scene.image.Image;

public class EmptyTower extends BaseTower {
    /*
        setUpgradeTo cho biết loại trụ mà emptyTower sẽ được nâng cấp thành
        nó sẽ được thay đổi khi bấm vào icon tương ứng khi upgrade
        lớp GameStage sẽ sử dụng giá trị của setUpgradeTo để biến emptyTower thành trụ tương ứng
    */
    public String upgradeTo = "?";

    public EmptyTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/empty_tower.png"));
        icons = new Icon[4];
    }

    public String getUpgradeTo() {
        return upgradeTo;
    }

    public void setUpgradeTo(String towerType) {
        this.upgradeTo = towerType;
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
            if ((upgradeRate += 2) > 100) {
                upgradeRate = -1;
                setUpgradeTo("archer");
            }
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        super.onClick(mouseX, mouseY);
        if (click(mouseX, mouseY)) {
            icons[0] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 0);
            icons[1] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 1);
            icons[2] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 2);
            icons[3] = new Icon(upgradeRing.getPosX(), upgradeRing.getPosY(), 3);
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

package TheGame.GameStage;

import javafx.scene.image.Image;

import TheGame.Sprite.StaticObject;
import TheGame.Tower.EmptyTower;

public class Level1 extends GameStage {
    private Image hammerTower, cityTower;

    public Level1(int i) {
        super(i);
        hammerTower = new Image("TheGame/Resources/Stage1_Hammer.png");
        cityTower = new Image("TheGame/Resources/city_tower.png");
    }

    @Override
    public void addOrnament() {
        ornament.add(new StaticObject(543, 337, hammerTower)); // Tượng cái búa ở giữa bản đồ
        ornament.add(new StaticObject(215, 290, cityTower)); // Tháp hoang bên trái
        ornament.add(new StaticObject(885, 475, cityTower)); // Tháp hoang bên phải

        towers.add(new EmptyTower(550, 650));
        towers.add(new EmptyTower(270, 560));
        towers.add(new EmptyTower(470, 480));
        towers.add(new EmptyTower(625, 480));
        towers.add(new EmptyTower(425, 390));
        towers.add(new EmptyTower(670, 390));
        towers.add(new EmptyTower(470, 315));
        towers.add(new EmptyTower(620, 315));
        towers.add(new EmptyTower(270, 210));
        towers.add(new EmptyTower(550, 140));
    }
}

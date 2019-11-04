package game.stage;

import game.enemy.NormalEnemy;
import game.object.GameObject;
import game.tower.EmptyTower;
import javafx.scene.image.Image;

public class Level1 extends GameStage {
    private Image hammerTower, cityTower, overWall;

    public Level1(int i) {
        super(i);
        hammerTower = new Image("file:resources/stage1/Stage1_Hammer.png");
        cityTower = new Image("file:resources/stage1/city_tower.png");
        overWall = new Image("file:resources/stage1/Stage1_overWall.png");
        enemies.add(new NormalEnemy(1200, 440));
        enemies.add(new NormalEnemy(1225, 440));
        enemies.add(new NormalEnemy(1250, 420));
        enemies.add(new NormalEnemy(1275, 430));
    }

    @Override
    public void addOrnament() {
        ornament.add(new GameObject(543, 337, hammerTower)); // Tượng cái búa ở giữa bản đồ
        ornament.add(new GameObject(215, 290, cityTower)); // Tháp hoang bên trái
        ornament.add(new GameObject(885, 475, cityTower)); // Tháp hoang bên phải
        ornament.add(new GameObject(-49, 220, overWall));

        // Vẽ 10 cái tháp trống
        int x_axis_offset = 10;
        int y_axis_offset = 10;

        baseTowers.add(new EmptyTower(260 - y_axis_offset, 560 - x_axis_offset));
        baseTowers.add(new EmptyTower(550 - y_axis_offset, 650 - x_axis_offset));
        baseTowers.add(new EmptyTower(470 - y_axis_offset, 480 - x_axis_offset));
        baseTowers.add(new EmptyTower(625 - y_axis_offset, 480 - x_axis_offset));
        baseTowers.add(new EmptyTower(425 - y_axis_offset, 390 - x_axis_offset));
        baseTowers.add(new EmptyTower(670 - y_axis_offset, 390 - x_axis_offset));
        baseTowers.add(new EmptyTower(470 - y_axis_offset, 315 - x_axis_offset));
        baseTowers.add(new EmptyTower(620 - y_axis_offset, 315 - x_axis_offset));
        baseTowers.add(new EmptyTower(270 - y_axis_offset, 230 - x_axis_offset));
        baseTowers.add(new EmptyTower(550 - y_axis_offset, 140 - x_axis_offset));
    }

    @Override
    public void update() {
        super.update();
    }
}

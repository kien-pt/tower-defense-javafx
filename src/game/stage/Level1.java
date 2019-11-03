package game.stage;

import game.enemy.NormalEnemy;
import game.enemy.TankerEnemy;
import javafx.scene.image.Image;

import game.object.GameObject;
import game.tower.EmptyTower;

public class Level1 extends GameStage {
    private Image hammerTower, cityTower, overWall;

    public Level1() {
        super(1);
        hammerTower = new Image("file:resources/stage1/Stage1_Hammer.png");
        cityTower = new Image("file:resources/stage1/city_tower.png");
        overWall = new Image("file:resources/stage1/Stage1_overWall.png");
        enemies.add(new NormalEnemy(1200, 440, -1));
        enemies.add(new NormalEnemy(1225, 440, -1));
        enemies.add(new NormalEnemy(1300, 450, 1));
        enemies.add(new NormalEnemy(1275, 430, -1));
        enemies.add(new NormalEnemy(1250, 420, 1));
        enemies.add(new TankerEnemy(1330, 430, 1));
        enemies.add(new TankerEnemy(1360, 420, -1));
    }

    @Override
    public void addOrnament() {
        ornament.add(new GameObject(543, 337, hammerTower)); // Tượng cái búa ở giữa bản đồ
        ornament.add(new GameObject(215, 290, cityTower)); // Tháp hoang bên trái
        ornament.add(new GameObject(885, 475, cityTower)); // Tháp hoang bên phải
        ornament.add(new GameObject(-49, 220, overWall));

        // Vẽ 10 cái tháp trống
        towers.add(new EmptyTower(550, 650));
        towers.add(new EmptyTower(260, 560));
        towers.add(new EmptyTower(470, 480));
        towers.add(new EmptyTower(625, 480));
        towers.add(new EmptyTower(425, 390));
        towers.add(new EmptyTower(670, 390));
        towers.add(new EmptyTower(470, 315));
        towers.add(new EmptyTower(620, 315));
        towers.add(new EmptyTower(260, 210));
        towers.add(new EmptyTower(550, 140));
    }

    @Override
    public void update() {
        super.update();
    }
}

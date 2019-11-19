package game.stage;

import game.enemy.BossEnemy;
import game.enemy.NormalEnemy;
import game.enemy.SmallerEnemy;
import game.enemy.TankerEnemy;
import game.object.GameObject;
import game.tower.EmptyTower;
import javafx.scene.image.Image;

public class Level1 extends GameStage {
    private Image hammerTower, cityTower, overWall;

    public Level1() {
        super(1);
        hammerTower = new Image("file:resources/stage1/Stage1_Hammer.png");
        cityTower = new Image("file:resources/stage1/city_tower.png");
        overWall = new Image("file:resources/stage1/Stage1_overWall.png");
        enemies.add(new NormalEnemy(1250, 400, -1));
        enemies.add(new NormalEnemy(1275, 400, -1));
        enemies.add(new NormalEnemy(1300, 420, 1));
        enemies.add(new NormalEnemy(1325, 420, 1));
        enemies.add(new NormalEnemy(1350, 400, -1));
        enemies.add(new NormalEnemy(1375, 420, 1));
        enemies.add(new TankerEnemy(1200, 410, -1));
        enemies.add(new SmallerEnemy(1400, 380, 1));
        enemies.add(new SmallerEnemy(1400, 420, -1));
        enemies.add(new BossEnemy(1225, 350, 1));
    }

    @Override
    public void addOrnament() {
        ornament.add(new GameObject(543, 337, hammerTower)); // Tượng cái búa ở giữa bản đồ
        ornament.add(new GameObject(215, 290, cityTower)); // Tháp hoang bên trái
        ornament.add(new GameObject(885, 475, cityTower)); // Tháp hoang bên phải
        ornament.add(new GameObject(-49, 220, overWall));

        // Vẽ 10 cái tháp trống
        towers.add(new EmptyTower(540, 640));
        towers.add(new EmptyTower(250, 550));
        towers.add(new EmptyTower(460, 470));
        towers.add(new EmptyTower(615, 470));
        towers.add(new EmptyTower(415, 380));
        towers.add(new EmptyTower(665, 380));
        towers.add(new EmptyTower(460, 297));
        towers.add(new EmptyTower(610, 297));
        towers.add(new EmptyTower(250, 200));
        towers.add(new EmptyTower(540, 130));
    }

    @Override
    public void update() { super.update(); }
}

package game.stage;

import game.tower.EmptyTower;
import game.enemy.NormalEnemy;
import game.enemy.TankerEnemy;
import game.object.GameObject;
import javafx.scene.image.Image;

public class Level1 extends GameStage {
    private Image hammerTower, cityTower, overWall;

    public Level1() {
        super(1);
        hammerTower = new Image("file:resources/stage1/Stage1_Hammer.png");
        cityTower = new Image("file:resources/stage1/city_tower.png");
        overWall = new Image("file:resources/stage1/Stage1_overWall.png");
        enemies.add(new NormalEnemy(1200, 440, -1));
        enemies.add(new NormalEnemy(1225, 440, -1));
        enemies.add(new NormalEnemy(1250, 420, 1));
        enemies.add(new NormalEnemy(1275, 430, -1));
        enemies.add(new NormalEnemy(1300, 450, 1));
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
        int x_axis_factor = 10;
        int y_axis_factor = 10;
        //tiện cho việc dịch đồng thời 10 tháp 1 cách nhất quán

        towers.add(new EmptyTower(260-y_axis_factor, 560-x_axis_factor));
        towers.add(new EmptyTower(550-y_axis_factor, 650-x_axis_factor));
        towers.add(new EmptyTower(470-y_axis_factor, 480-x_axis_factor));
        towers.add(new EmptyTower(625-y_axis_factor, 480-x_axis_factor));
        towers.add(new EmptyTower(425-y_axis_factor, 390-x_axis_factor));
        towers.add(new EmptyTower(670-y_axis_factor, 390-x_axis_factor));
        towers.add(new EmptyTower(470-y_axis_factor, 315-x_axis_factor));
        towers.add(new EmptyTower(620-y_axis_factor, 315-x_axis_factor));
        towers.add(new EmptyTower(270-y_axis_factor, 230-x_axis_factor));
        towers.add(new EmptyTower(550-y_axis_factor, 140-x_axis_factor));
    }

    @Override
    public void update() {
        super.update();
    }
}

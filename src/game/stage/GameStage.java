package game.stage;

import game.enemy.BaseEnemy;
import game.object.GameObject;
import game.tower.ArcherTower;
import game.tower.BaseTower;
import game.tower.EmptyTower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class GameStage {
    ArrayList<GameObject> ornament = new ArrayList<>();
    ArrayList<BaseTower> baseTowers = new ArrayList<>();
    ArrayList<BaseEnemy> enemies = new ArrayList<>();
    private Image map;

    // Load map của level tương ứng
    GameStage(int k) {
        map = new Image("file:resources/Stage_" + k + ".png");
    }

    // Thêm vật trang trí
    public abstract void addOrnament();

    /**
     * Vẽ màn chơi
     * Vẽ kẻ địch
     * Vẽ các tháp
     * Vẽ vật trang trí
     * Vẽ các layout
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(map, 0, -50);
        for (BaseEnemy enemy : enemies) enemy.draw(gc);
        for (BaseTower baseTower : baseTowers) baseTower.draw(gc);
        for (GameObject i : ornament) i.draw(gc);
        for (BaseTower baseTower : baseTowers) baseTower.drawLayout(gc);
    }

    /**
     * Cập nhật màn chơi
     */

    public void update() {
        for (int i = 0; i < baseTowers.size(); ++i) {
            baseTowers.get(i).update();
            if (baseTowers.get(i) instanceof EmptyTower) {
                if (((EmptyTower) baseTowers.get(i)).getUpgradeTo().equals("archer")) {
                    baseTowers.set(i, new ArcherTower(baseTowers.get(i).getPosX(), baseTowers.get(i).getPosY()));
                }
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).getPosX() < -30) enemies.remove(i);
        }
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) for (BaseTower baseTower : baseTowers) baseTower.onClick((int) mouseX, (int) mouseY);
        if (key == 1) for (BaseTower baseTower : baseTowers) baseTower.hover((int) mouseX, (int) mouseY);
    }
}

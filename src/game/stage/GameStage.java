package game.stage;

import java.util.ArrayList;

import game.tower.BaseTower;
import game.enemy.BaseEnemy;
import game.object.GameObject;
import game.tower.NormalTower;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameStage {
    private Image map;
    public ArrayList<GameObject> ornament = new ArrayList<>();
    public ArrayList<BaseTower> towers = new ArrayList<>();
    public ArrayList<BaseEnemy> enemies = new ArrayList<>();

    // Load map của level tương ứng
    public GameStage(int k) {
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
        for (BaseEnemy enemy : enemies) ((GameObject) enemy).draw(gc);
        for (BaseTower tower : towers) ((GameObject) tower).draw(gc);
        for (GameObject i : ornament) i.draw(gc);
        for (BaseTower tower: towers) tower.drawLayout(gc);
    }

    /**
     * Cập nhật màn chơi
     */
    public void update() {
        for (int i = 0; i < towers.size(); i++) {
            // Update tháp
            towers.get(i).update();
            // Nêu tháp được nâng cấp thì set 1 tháp mới thay vào
            if (towers.get(i).getUpgrade() >= 0) {
                int newPosX = towers.get(i).getPosX() - 10;
                int newPosY = towers.get(i).getPosY() - 10;
                towers.set(i, new NormalTower(newPosX, newPosY));
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).getPosX() < -30) enemies.remove(i);
        }

        for (BaseTower i : towers) i.attack(enemies);
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) for (BaseTower tower: towers) tower.onClick((int) mouseX, (int) mouseY);
        if (key == 1) for (BaseTower tower : towers) tower.onHover((int) mouseX, (int) mouseY);
    }
}

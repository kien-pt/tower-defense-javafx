package game.stage;

import game.enemy.BaseEnemy;
import game.object.GameObject;
import game.tower.BaseTower;
import game.tower.NormalTower;
import game.tower.SniperTower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class GameStage {
    public ArrayList<GameObject> ornament = new ArrayList<>();
    public ArrayList<BaseTower> towers = new ArrayList<>();
    public ArrayList<BaseEnemy> enemies = new ArrayList<>();
    private Image map;

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
        for (BaseTower tower : towers) tower.draw(gc);
        sortEnemies(false); // Thằng nào thấp hơn thì vẽ sau, tránh bị đè lên nhau
        for (BaseEnemy enemy : enemies) enemy.draw(gc);
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
            if (towers.get(i).getUpgrade() == 0)
                towers.set(i, new NormalTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
            if (towers.get(i).getUpgrade() == 1)
                towers.set(i, new SniperTower(towers.get(i).getPosX(), towers.get(i).getPosY()));

        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).getPosX() < -30) enemies.remove(i);
        }

        sortEnemies(true);  // Bắn thằng đang đi nhanh nhất
        for (BaseTower i : towers) i.attack(enemies);
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) for (BaseTower tower : towers) tower.onClick((int) mouseX, (int) mouseY, this);
        if (key == 1) for (BaseTower tower : towers) tower.onHover((int) mouseX, (int) mouseY, this);
    }

    private boolean cmp(int i, int j, boolean X) {
        if (X && enemies.get(i).getPosX() + enemies.get(i).getWidth() <= enemies.get(j).getPosX() + enemies.get(j).getWidth()) return true;
        if (!X && enemies.get(i).getPosY() + enemies.get(i).getHeight() <= enemies.get(j).getPosY() + enemies.get(j).getHeight()) return true;
        return false;
    }

    private void sortEnemies(boolean X) {
        for (int  i = 1; i < enemies.size(); i++)
            for (int j = 0; j < i; j++) if (cmp(i, j, X)) {
                BaseEnemy tempEnemy = enemies.get(i);
                enemies.set(i, enemies.get(j));
                enemies.set(j, tempEnemy);
            }
    }
}

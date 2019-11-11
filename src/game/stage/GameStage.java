package game.stage;

import game.enemy.BaseEnemy;
import game.gui.Icon;
import game.object.GameObject;
import game.tower.BaseTower;
import game.tower.NormalTower;
import game.tower.SniperTower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class GameStage {
    public ArrayList<GameObject> ornament = new ArrayList<>();
    public ArrayList<BaseTower> towers = new ArrayList<>();
    public ArrayList<BaseEnemy> enemies = new ArrayList<>();
    private Image map;
    private double brightness;
    private ColorAdjust colorAdjust;

    private Icon pause;
    private boolean isPause;
    private long pauseTime;

    // Load map của level tương ứng
    public GameStage(int k) {
        map = new Image("file:resources/Stage_" + k + ".png");
        brightness = -1;
        colorAdjust = new ColorAdjust();
        isPause = false;
        pause = new Icon(1118, 30, 9);
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
        gc.setEffect(colorAdjust);
        gc.drawImage(map, 0, -50);
        gc.drawImage(map, 0, -50);
        pause.draw(gc);
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
        if (!isPause && brightness < 0) brightness += 0.05;
        colorAdjust.setBrightness(brightness);
        if (isPause) return;

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

        // Xoá bớt mấy thằng hết máu
        for (int i = 0; i < enemies.size(); i++)
            while (i < enemies.size() && enemies.get(i).getHealthBar().getHealth() <= 0) enemies.remove(i);

        sortEnemies(true);  // Bắn thằng đang đi nhanh nhất
        for (BaseTower i : towers) i.attack(enemies);
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) {
            if (isPause) {
                isPause = false;
                brightness = 0;
                pauseTime = System.currentTimeMillis() - pauseTime;
                for (BaseEnemy enemy: enemies) enemy.setLastAniTime(enemy.getLastAniTime() + pauseTime);
                for (BaseEnemy enemy: enemies) enemy.setLastMoveTime(enemy.getLastMoveTime() + pauseTime);
                for (BaseTower tower: towers) tower.setLastShootTime(tower.getLastShootTime() + pauseTime);
            }
            if (pause.onClick((int) mouseX, (int) mouseY, null) > 0) {
                isPause = true;
                brightness = -0.5;
                pauseTime = System.currentTimeMillis();
            }
            for (BaseTower tower : towers) {
                pause.onHover((int) mouseX, (int) mouseY, null);
                tower.onClick((int) mouseX, (int) mouseY, this);
            }
        }
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

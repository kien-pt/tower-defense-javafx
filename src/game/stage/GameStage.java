package game.stage;

import game.enemy.*;
import game.gui.HUD;
import game.tower.*;
import java.io.File;
import game.gui.Icon;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import game.object.GameObject;
import javafx.scene.image.Image;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.canvas.GraphicsContext;


public abstract class GameStage {
    private HUD hud;
    private Image map;
    private Icon pause;
    private Scanner mapData;
    private boolean isPause;
    private double brightness;
    ArrayList<BaseTower> towers;
    ArrayList<GameObject> ornament;
    private ColorAdjust colorAdjust;
    private ArrayList<BaseEnemy> enemies;
    private long pauseTime, readDataTime;

    /**
     * Load Map của Level tương ứng
     * Đọc dữ liệu từ file data
     * Thêm chút hiệu ứng Fade
     */
    GameStage(int k) {
        map = new Image("file:resources/Stage_" + k + ".png");

        try {
            File file = new File("resources/lvl" + k + ".dat");
            mapData = new Scanner(file);
        } catch (IOException e) { }

        isPause = false;
        brightness = -1;
        towers = new ArrayList<>();
        enemies = new ArrayList<>();
        ornament = new ArrayList<>();
        colorAdjust = new ColorAdjust();
        hud = new HUD(30,30);
        readDataTime = System.currentTimeMillis();
        pause = new Icon(1118, 30, 9);
    }

    public abstract void addOrnament();

    public void draw(GraphicsContext gc) {
        gc.setEffect(colorAdjust);
        gc.drawImage(map, 0, -50);

        pause.draw(gc);
        hud.draw(gc);

        // Nửa trên vẽ trước
        for (int i = 0; i < ornament.size() / 2; i++) ornament.get(i).draw(gc);
        for (int i = 0; i < towers.size() / 2; i++) towers.get(i).draw(gc);

        sortEnemies(false); // Thằng nào thấp hơn thì vẽ sau, tránh bị đè lên nhau
        for (BaseEnemy enemy : enemies) enemy.draw(gc);

        // Nửa dưới vẽ sau
        for (int i = towers.size() / 2; i < towers.size(); i++) towers.get(i).draw(gc);
        for (int i = ornament.size() / 2; i < ornament.size(); i++) ornament.get(i).draw(gc);

        // Layout vẽ sau cùng
        for (BaseTower tower: towers) tower.drawLayout(gc);
    }

    public void update() {
        if (!isPause && brightness < 0) brightness += 0.05;
        colorAdjust.setBrightness(brightness);
        if (isPause) return;

        // Đọc map
        if (enemies.isEmpty() && readDataTime + 7000 <= System.currentTimeMillis()) {
            hud.setWave(hud.getWave() + 1);
            while (mapData.hasNextLine()) {
                String type = mapData.next();
                int x = mapData.nextInt(), y = mapData.nextInt(), p = mapData.nextInt();
                if (type.equals("wait")) break;
                if (type.equals("boss")) enemies.add(new BossEnemy(x, y, p));
                if (type.equals("normal")) enemies.add(new NormalEnemy(x, y, p));
                if (type.equals("tanker")) enemies.add(new TankerEnemy(x, y, p));
                if (type.equals("smaller")) enemies.add(new SmallerEnemy(x, y, p));

            }
        }

        for (int i = 0; i < towers.size(); i++) {
            BaseTower tower = towers.get(i);
            tower.update();     // Update tháp

            // Nêu tháp được nâng cấp thì set 1 tháp mới thay vào
            if (0 <= tower.getUpgrade() && tower.getUpgrade() < 3) {
                if (tower.getUpgrade() == 0 && hud.getCoins() >= 200) towers.set(i, new NormalTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
                else if (tower.getUpgrade() == 1 && hud.getCoins() >= 300) towers.set(i, new SniperTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
                else if (tower.getUpgrade() == 2 && hud.getCoins() >= 500) towers.set(i, new GunMachineTower(towers.get(i).getPosX(), towers.get(i).getPosY()));
                hud.setCoins(hud.getCoins() - towers.get(i).getPrice());
            }

            // Nếu tháp được lên cấp hoặc bị bán đi
            if (tower.getUpgrade() == 4) {
                if (!tower.getTag().equals("gun")) {
                    tower.upgrade(new Image("file:resources/tower/" + tower.getTag() + "_tower_" + tower.increaseRank() + ".png"));
                    tower.setStrength(tower.getStrength() + 5);
                    tower.setUpgrade(-1);
                    if (tower.getRank() < 3) {
                        hud.setCoins(hud.getCoins() - tower.getUpgradeprice());
                        tower.setUpgradeprice(tower.getUpgradeprice() + 50);
                        tower.setSellprice(tower.getSellprice() + 50);
                    }
                }
            } else if (tower.getUpgrade() == 5) {
                towers.set(i, new EmptyTower(tower.getPosX(), tower.getPosY()));
                hud.setCoins(hud.getCoins()+tower.getSellprice());
            }
        }

        // Xoá bớt mấy thằng hết máu
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).getPosX() < -30) {
                enemies.remove(i);
                hud.DecreaseLife();
            }
            else if (enemies.get(i).isDetroyed()) {
                hud.setCoins(hud.getCoins() + enemies.get(i).getReward());
                enemies.remove(i);
            }
        }

        sortEnemies(true);  // Bắn thằng đang đi nhanh nhất
        for (BaseTower i : towers) i.attack(enemies);

        if (hud.getLife() <= 0) System.exit(0);
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
        return !X && enemies.get(i).getPosY() + enemies.get(i).getHeight() <= enemies.get(j).getPosY() + enemies.get(j).getHeight();
    }

    /**
     * X == true: sắp xếp theo tọa độ x => bắn thằng nào đi xa nhất
     * X == false: sắp xếp theo tọa độ y => vẽ thằng nào ở dưới trước, tránh bị đè
     */
    private void sortEnemies(boolean X) {
        for (int  i = 1; i < enemies.size(); i++)
            for (int j = 0; j < i; j++) if (cmp(i, j, X)) {
                BaseEnemy tempEnemy = enemies.get(i);
                enemies.set(i, enemies.get(j));
                enemies.set(j, tempEnemy);
            }
    }

    public HUD getHud() { return hud; }
}

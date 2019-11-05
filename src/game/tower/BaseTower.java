package game.tower;

import game.enemy.BaseEnemy;
import game.gui.Ring;
import game.gui.UpdateBar;
import game.object.ClickableObject;
import game.object.Effect;
import game.object.GameObject;
import game.object.UpdatableObject;
import game.troops.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BaseTower extends GameObject implements UpdatableObject, ClickableObject {
    private Ring ring;
    private String tag;
    private double range;
    private long lastShootTime;
    private Effect shootSoldier;
    private boolean shoot, active;
    private int upgrade, tempUpgrade;
    private ArrayList<Bullet> bullets;
    private GameObject soldier, tempSoldier;
    private UpdateBar updateBar, tempUpdateBar;

    public BaseTower(int posX, int posY, String tag) {
        super(posX, posY, new Image("file:resources/tower/" + tag + "_tower.png"));
        upgrade = -1;
        active = false;
        this.tag = tag;
        bullets = new ArrayList<Bullet>();
        lastShootTime = System.currentTimeMillis();
        tempUpdateBar = new UpdateBar(posX + 28, posY - 15);
        tempSoldier = new GameObject(posX + 49, posY - 5, new Image("file:resources/soldier/" + tag + "_idle.png"));
        soldier = tempSoldier;
    }

    @Override
    public void onHover(int mouseX, int mouseY) {
        // Show Range
        if (ring != null) ring.onHover(mouseX, mouseY);
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        if (ring != null) ring.onClick(mouseX, mouseY);

        if (hover(mouseX, mouseY)) {
            int x = getPosX() + (int) getImage().getWidth() / 2 - 121;
            int y = getPosY() + (int) getImage().getHeight() / 2 - 121;
            if (active) ring = new Ring(x, y, 1);
            else ring = new Ring(x, y, 0);
        } else {
            if (ring != null && ring.getUpgrade() >= 0) {
                updateBar = tempUpdateBar;
                tempUpgrade = ring.getUpgrade();
            }
            ring = null;
        }
    }

    @Override
    public void update() {
        if (ring != null) ring.update();

        if (shootSoldier != null) {
            shootSoldier.update();
            if (shootSoldier.isDestroyed()) shootSoldier = null;
        }

        if (shoot) {
            soldier = null;
            if (shootSoldier == null)
                shootSoldier = new Effect(posX + 52, posY - 10, "file:resources/soldier/" + tag + "_", 4, 24);
        } else if (shootSoldier == null) soldier = tempSoldier;

        if (updateBar != null && updateBar.isDone()) {
            updateBar = null;
            upgrade = tempUpgrade;
        }
        if (updateBar != null) updateBar.update();

        for (Bullet bullet : bullets) bullet.update();
    }

    public void attack(ArrayList<BaseEnemy> enemies) {
        if (range == 0) return; // Không bắn
        shoot = false;
        while (!bullets.isEmpty() && bullets.get(0).isDestroyed()) bullets.remove(0);   // Xóa bớt đạn thừa
        if (lastShootTime + 1000 < System.currentTimeMillis()) {
            lastShootTime = System.currentTimeMillis();
            int towerCenterX = posX + getWidth() / 2;   // Tọa độ tâm X của tháp
            int towerCenterY = posY + getHeight() / 2;  // Tọa độ tâm Y của tháp
            for (BaseEnemy enemy : enemies) {
                double dis = Math.pow(towerCenterX - enemy.getPosX(), 2) + Math.pow(towerCenterY - enemy.getPosY(), 2);
                if (dis <= range * range) {
                    bullets.add(new Bullet(posX + getWidth() / 2, posY - 10, enemy));
                    shoot = true;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (soldier != null) soldier.draw(gc);
        if (shootSoldier != null) shootSoldier.draw(gc);
        if (updateBar != null) updateBar.draw(gc);
    }

    public void drawLayout(GraphicsContext gc) {
        if (ring != null) ring.draw(gc);
        for (Bullet bullet : bullets) bullet.draw(gc);
    }

    public Ring getRing() {
        return ring;
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public String getTag() {
        return tag;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

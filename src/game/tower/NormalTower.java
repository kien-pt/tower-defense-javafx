package game.tower;

import game.enemy.BaseEnemy;
import game.enemy.NormalEnemy;
import game.object.Effect;
import game.object.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.troops.Bullet;

import java.util.ArrayList;

public class NormalTower extends ActiveTower {
    private ArrayList<Bullet> bullets;
    private double range;
    private GameObject soldier;
    private Effect shootSoldier;
    private boolean isShoot;
    private long lastShootTime;

    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/normal_tower.png"));
        this.range = 200;
        isShoot = false;
        soldierTag = "normal_";
        soldier = new GameObject(posX + 52, posY - 5, new Image("file:resources/soldier/normal_idle.png"));
        this.bullets = new ArrayList<>();
        lastShootTime = System.currentTimeMillis();
    }

    public double getRange() {
        return this.range;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (isShoot) {
            if (shootSoldier == null)
                shootSoldier = new Effect(posX + 52, posY - 10, "file:resources/soldier/normal_", 4, 24);
            if (soldier != null) soldier = null;
        } else if (shootSoldier == null)
            soldier = new GameObject(posX + 52, posY - 5, new Image("file:resources/soldier/normal_idle.png"));
        if (soldier != null) soldier.draw(gc);

        if (shootSoldier != null && shootSoldier.isDestroyed()) shootSoldier = null;
        if (shootSoldier != null) {
            shootSoldier.draw(gc);
            shootSoldier.update();
        }

        for (Bullet i : bullets) {
            if (i != null) {
                i.draw(gc);
            }
        }
    }

    public void attack(ArrayList<BaseEnemy> enemies) {
        isShoot = false;
        while (!bullets.isEmpty() && bullets.get(0).isDestroyed()) bullets.remove(0);
        if (lastShootTime + 1000 < System.currentTimeMillis()) {
            for (BaseEnemy enemy : enemies) {
                double dis = Math.pow(posX - enemy.getPosX(), 2) + Math.pow(posY - enemy.getPosY(), 2);
                if (dis <= range * range) {
                    bullets.add(new Bullet(posX + getWidth() / 2, posY - 10, enemy));
                    isShoot = true;
                    break;
                }
            }
            lastShootTime = System.currentTimeMillis();
        }
    }

    @Override
    public void update() {
        super.update();
        for (Bullet bullet : bullets) bullet.update();
    }
}

package game.tower;

import game.enemy.BaseEnemy;
import game.gui.RangeCircle;
import game.gui.Ring;
import game.gui.UpdateBar;
import game.object.*;
import game.soldier.BaseSoldier;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BaseTower extends GameObject implements UpdatableObject, ClickableObject {
    private RangeCircle rangeCircle;
    private Ring ring;
    private String tag;
    private long lastShootTime;
    private double range, speed;
    //private Effect shootSoldier;
    private boolean shoot, active;
    private int upgrade, tempUpgrade;
    private ArrayList<Bullet> bullets;
    //private GameObject soldier, tempSoldier;
    private BaseSoldier soldier;
    private UpdateBar updateBar, tempUpdateBar;

    BaseTower(int posX, int posY, String tag) {
        super(posX, posY, new Image("file:resources/tower/" + tag + "_tower.png"));
        upgrade = -1;
        active = false;
        this.tag = tag;
        bullets = new ArrayList<Bullet>();
        lastShootTime = System.currentTimeMillis();
        tempUpdateBar = new UpdateBar(posX + 28, posY - 12);
        //tempSoldier = new GameObject(posX + 49, posY - 5, new Image("file:resources/soldier/" + tag + "_idle.png"));
        //soldier = tempSoldier;
    }

    @Override
    public void onHover(int mouseX, int mouseY, Object caller) {
        // Show Range
        if (ring != null) ring.onHover(mouseX, mouseY, this);
    }

    @Override
    public void onClick(int mouseX, int mouseY, Object caller) {
        if (ring != null) ring.onClick(mouseX, mouseY, this);
        rangeCircle = new RangeCircle(getXcenter(), getYcenter() - getHeight() / 2 + 19, getRange());

        if (hover(mouseX, mouseY)) {
            if (active) ring = new Ring(getXcenter(), getYcenter(), 1);
            else ring = new Ring(getXcenter(), getYcenter(), 0);
        } else {
            if (ring != null && ring.getUpgrade() >= 0) {
                updateBar = tempUpdateBar;
                tempUpgrade = ring.getUpgrade();
            }
            ring = null;
            rangeCircle = null;
        }
    }

    @Override
    public void update() {
        if (ring != null) ring.update();

        if (soldier != null) soldier.update();

        //if (shootSoldier != null) {
        //    shootSoldier.update();
        //    if (shootSoldier.isDestroyed()) shootSoldier = null;
        //}

        //if (shoot) {
        //    soldier = null;
        //    if (shootSoldier == null)
        //        shootSoldier = new Effect(posX + 52, posY - 10, "file:resources/soldier/" + tag + "_", 4, 24);
        //} else if (shootSoldier == null) soldier = tempSoldier;

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
        if (lastShootTime + 1000 * speed < System.currentTimeMillis()) {
            lastShootTime = System.currentTimeMillis();
            for (BaseEnemy enemy : enemies) {
                double dis = Math.pow(getXcenter() - enemy.getPosX(), 2) + Math.pow(getYcenter() - enemy.getPosY(), 2);
                if (dis <= range * range) {
                    bullets.add(new Bullet(posX + getWidth() / 2, posY - 10, enemy));
                    soldier.setShooting(true);
                    if (soldier.getPosY() <= enemy.getPosY()) {
                        if (tag.equals("normal")) if (soldier.getPosX() <= enemy.getPosX()) soldier.setDirection("IV"); else soldier.setDirection("III");
                        if (tag.equals("sniper")) soldier.setDirection("front");
                    } else {
                        if (tag.equals("normal")) if (soldier.getPosX() <= enemy.getPosX()) soldier.setDirection("I"); else soldier.setDirection("II");
                        if (tag.equals("sniper")) soldier.setDirection("behind");
                    }

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
        //if (soldier != null) soldier.draw(gc);
        //if (shootSoldier != null) shootSoldier.draw(gc);
        if (updateBar != null) updateBar.draw(gc);
        if (rangeCircle != null) rangeCircle.draw(gc);
    }

    public void drawLayout(GraphicsContext gc) {
        if (ring != null) ring.draw(gc);
        for (Bullet bullet : bullets) bullet.draw(gc);
    }

    public Ring getRing() { return ring; }
    public RangeCircle getRangeCircle() { return rangeCircle; }
    public void setRangeCircle(RangeCircle rangeCircle) { this.rangeCircle = rangeCircle; }
    public boolean isShoot() { return shoot; }
    public void setShoot(boolean shoot) { this.shoot = shoot; }
    public String getTag() { return tag; }
    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
    public int getUpgrade() { return upgrade; }
    public void setUpgrade(int upgrade) { this.upgrade = upgrade; }
    public void setActive(boolean active) { this.active = active; }
    public void setSpeed(double speed) { this.speed = speed; }
    public void setSoldier(BaseSoldier soldier) { this.soldier = soldier; }
    public long getLastShootTime() { return lastShootTime; }
    public void setLastShootTime(long lastShootTime) { this.lastShootTime = lastShootTime; }
}

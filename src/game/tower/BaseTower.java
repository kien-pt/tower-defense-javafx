package game.tower;

import game.gui.Ring;
import game.object.*;
import game.gui.UpdateBar;
import java.util.ArrayList;
import game.enemy.BaseEnemy;
import game.gui.RangeCircle;
import game.soldier.BaseSoldier;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class BaseTower extends GameObject implements UpdatableObject, ClickableObject {
    private Ring ring;
    private String tag, path;
    private Effect buildSmoke;
    private long lastShootTime;
    private UpdateBar updateBar;
    private double range, speed;
    private BaseSoldier soldier;
    private RangeCircle rangeCircle;
    private ArrayList<Bullet> bullets;
    private boolean shoot, active, showRange;
    private int upgrade, tempUpgrade, price, upgradeprice, sellprice, strength, rank;

    BaseTower(int posX, int posY, String tag) {
        super(posX, posY, new Image("file:resources/tower/" + tag + "_tower_" + 1 + ".png"));
        rank = 1;
        upgrade = -1;
        active = false;
        this.tag = tag;
        showRange = false;
        bullets = new ArrayList<>();
        lastShootTime = System.currentTimeMillis();
        buildSmoke = new Effect(posX + 25, posY + 30, "file:resources/Effect/effect_buildSmoke_", 35, 60);
    }

    @Override
    public void onHover(int mouseX, int mouseY, Object caller) {
        if (ring != null) ring.onHover(mouseX, mouseY, this);
        // Show Range
        showRange = hover(mouseX, mouseY);
    }

    @Override
    public void onClick(int mouseX, int mouseY, Object caller) {
        if (ring != null) ring.onClick(mouseX, mouseY, this);

        if (hover(mouseX, mouseY)) {
            if (active) ring = new Ring(getXcenter(), getYcenter(), 1, this);
            else ring = new Ring(getXcenter(), getYcenter(), 0, this);
        } else {
            if (ring != null && ring.getUpgrade() >= 0) {
                updateBar = new UpdateBar(posX + 30, posY - 22);
                tempUpgrade = ring.getUpgrade();
            }
            ring = null;
        }
    }

    @Override
    public void update() {
        if (ring != null) ring.update();

        if (soldier != null) soldier.update();

        if (updateBar != null && updateBar.isDone()) {
            updateBar = null;
            upgrade = tempUpgrade;
        }
        if (updateBar != null) updateBar.update();

        for (Bullet bullet : bullets) bullet.update();

        if (buildSmoke != null) {
            buildSmoke.update();
            if (buildSmoke.isDestroyed()) buildSmoke = null;
        }
    }

    /**
     * Đống này khá phức tạp, chủ yếu là toán thôi
     * Gặp enemy nào trong tầm là bắn 1 viên đạn
     * Viên đạn sẽ đuổi theo target chọn trước đó
     */
    public void attack(ArrayList<BaseEnemy> enemies) {
        if (range == 0) return; // Không bắn
        shoot = false;
        while (!bullets.isEmpty() && bullets.get(0).isDestroyed()) bullets.remove(0);   // Xóa bớt đạn thừa
        if (lastShootTime + 1000 * speed < System.currentTimeMillis()) {
            lastShootTime = System.currentTimeMillis();
            for (BaseEnemy enemy : enemies) {
                double dis = Math.pow(getXcenter() - enemy.getPosX(), 2) + Math.pow(posY - 10 - enemy.getPosY(), 2);
                if (dis <= range * range && enemy.getHealthBar().getHealth() > 0) {
                    bullets.add(new Bullet(posX + getWidth() / 2, posY - 10, enemy, path, strength));
                    soldier.setShooting(true);
                    // Xoay soldier theo hướng bắn
                    if (soldier.getPosY() <= enemy.getPosY()) {
                        if (tag.equals("sniper")) soldier.setDirection("front");
                        else if (soldier.getPosX() <= enemy.getPosX()) soldier.setDirection("IV"); else soldier.setDirection("III");
                    } else {
                        if (tag.equals("sniper")) soldier.setDirection("behind");
                        else if (soldier.getPosX() <= enemy.getPosX()) soldier.setDirection("I"); else soldier.setDirection("II");
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
        if (updateBar != null) updateBar.draw(gc);
        if (buildSmoke != null) buildSmoke.draw(gc);
    }

    public void drawLayout(GraphicsContext gc) {
        if (ring != null) ring.draw(gc);
        for (Bullet bullet : bullets) bullet.draw(gc);
        if (showRange) rangeCircle.draw(gc);
    }

    public int increaseRank() {
        if (rank < 3) rank++;
        return rank;
    }

    public void upgrade(Image image) {
        super.setImage(image);
        buildSmoke = new Effect(posX + 25, posY + 30, "file:resources/Effect/effect_buildSmoke_", 35, 60);
    }

    /**
     * Rất lắm getter, setter
     * Nhưng sẽ tiện việc cài đặt sau này
     */
    double getRange() { return range; }
    public int getRank() { return rank; }
    public String getTag() { return tag; }
    public int getPrice() { return price; }
    public int getUpgrade() { return upgrade; }
    public int getStrength() { return strength; }
    void setPath(String path) { this.path = path; }
    public int getSellprice() { return sellprice; }
    void setPrice(int price) { this.price = price; }
    void setSpeed(double speed) { this.speed = speed; }
    void setRange(double range) { this.range = range; }
    void setShoot(boolean shoot) { this.shoot = shoot; }
    public int getUpgradeprice() { return upgradeprice; }
    void setActive(boolean active) { this.active = active; }
    public long getLastShootTime() { return lastShootTime; }
    public void setUpgrade(int upgrade) { this.upgrade = upgrade; }
    void setSoldier(BaseSoldier soldier) { this.soldier = soldier; }
    public void setStrength(int strength) { this.strength = strength; }
    public void setSellprice(int sellprice) { this.sellprice = sellprice; }
    void setRangeCircle(RangeCircle rangeCircle) { this.rangeCircle = rangeCircle; }
    public void setUpgradeprice(int upgradeprice) { this.upgradeprice = upgradeprice; }
    public void setLastShootTime(long lastShootTime) { this.lastShootTime = lastShootTime; }







}
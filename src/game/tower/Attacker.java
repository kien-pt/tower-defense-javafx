package game.tower;

import game.enemy.BaseEnemy;
import game.enemy.NormalEnemy;
import game.troops.Bullet;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Iterator;

public class Attacker {
    private NormalTower tower;
    private BaseEnemy target;
    public boolean isShoot;
    private long lastShootTime;

    public Attacker(NormalTower tower) {
        this.tower = tower;
        lastShootTime = System.currentTimeMillis();
    }

    public void Shoot(ArrayList<BaseEnemy> enemies) {
        double MinXRange = tower.getPosX() + tower.getWidth() / 2 - tower.getRange();
        double MaxXRange = tower.getPosX() + tower.getWidth() / 2 + tower.getRange();
        double MinYRange = tower.getPosY() + tower.getHeight() / 2 - tower.getRange();
        double MaxYRange = tower.getPosY() + tower.getHeight() / 2 + tower.getRange();
        Iterator<BaseEnemy> iterator = enemies.iterator();

        isShoot = false;

        while (iterator.hasNext()) {
            target = iterator.next();
            if (target.getPosX() >= MinXRange && target.getPosX() <= MaxXRange && target.getPosY() >= MinYRange && target.getPosY() <= MaxYRange) {
                // Bắn 1 đạn thôi, vừa nãy bắn nhiều quá
                if (lastShootTime + 1000 < System.currentTimeMillis()) {
                    tower.getBullets().add(new Bullet(tower.getPosX() + tower.getWidth() / 2, tower.getPosY() + tower.getHeight() / 2 - 30, 5, 5, true));
                    lastShootTime = System.currentTimeMillis();
                    isShoot = true;
                }

                if (!tower.getBullets().isEmpty()) {
                    tower.getBullets().get(0).MoveBullet(target.getPosX(), target.getPosY());
                    // Vừa này là If không phải While nên no sẽ lỗi có đạn nó không xóa đi
                    while (!tower.getBullets().isEmpty() && tower.getBullets().get(0).getPosX() == target.getPosX() && tower.getBullets().get(0).getPosY() == target.getPosY()) {
                        tower.getBullets().remove(0);
                    }
                }
                break;
            }
        }
    }
}

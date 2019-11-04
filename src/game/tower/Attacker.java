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

    public Attacker(NormalTower tower) {
        this.tower = tower;
    }

    public void Shoot(ArrayList<BaseEnemy> enemies) {
        double MinXRange = tower.getPosX() - tower.getRange();
        double MaxXRange = tower.getPosX() + tower.getRange();
        double MinYRange = tower.getPosY() - tower.getRange();
        double MaxYRange = tower.getPosY() + tower.getRange();
        Iterator<BaseEnemy> iterator = enemies.iterator();

        while (iterator.hasNext()) {
            target = iterator.next();
            if (target.getPosX() >= MinXRange && target.getPosX() <= MaxXRange && target.getPosY() >= MinYRange && target.getPosY() <= MaxYRange) {
                tower.getBullets().add(new Bullet(tower.getPosX() + tower.getWidth() / 2, tower.getPosY() + tower.getHeight() / 2, 5, 5, true));
                tower.getBullets().get(0).MoveBullet(target.getPosX(), target.getPosY());
                if (tower.getBullets().get(0).getPosX() == target.getPosX() && tower.getBullets().get(0).getPosY() == target.getPosY()) {
                    tower.getBullets().remove(0);
                }
                break;
            }
        }
    }
}

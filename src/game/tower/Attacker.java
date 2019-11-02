package game.tower;

import game.enemy.BaseEnemy;
import game.enemy.NormalEnemy;
import game.troops.Bullet;;
import java.util.ArrayList;

public class Attacker {
    private NormalTower tower;

    public Attacker(NormalTower tower) {
        this.tower = tower;
    }

    public void CreateTarget(ArrayList<BaseEnemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            double distance = Math.sqrt(Math.pow(tower.getPosX() - enemies.get(i).getPosX(), 2) + Math.pow(tower.getPosY() - enemies.get(i).getPosY(), 2));
            if (distance <= tower.getRange()) {
                tower.addTargets((NormalEnemy) enemies.get(i));
            }
//            else if (!tower.getTargets().isEmpty()) tower.getTargets().remove(0);
        }
    }

    public void Shoot(ArrayList<BaseEnemy> enemies) {
        if (!tower.getTargets().isEmpty()) {
            tower.getBullets().add(new Bullet(tower.getPosX() + tower.getWidth() / 2, tower.getPosY() + tower.getHeight() / 2, 5, 5, true));
            tower.getBullets().get(0).MoveBullet(tower.getTargets().get(0).getPosX(), tower.getTargets().get(0).getPosY());
            if (tower.getBullets().get(0).getPosX() == enemies.get(0).getPosX() && tower.getBullets().get(0).getPosY() == enemies.get(0).getPosY()) {
                tower.getBullets().remove(0);
            }
        }
    }


}

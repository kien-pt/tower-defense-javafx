package game.tower;

import game.enemy.BaseEnemy;
import game.enemy.NormalEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.troops.Bullet;

import java.util.ArrayList;

public class NormalTower extends ActiveTower {
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<BaseEnemy> targets = new ArrayList<>();
    private double range;
    private Attacker attacker;

    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/normal_tower.png"));
        this.range = 200;
        this.attacker = new Attacker(this);
        //this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
    }

    public double getRange() {
        return this.range;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        for (Bullet i : bullets) {
            if (i != null) i.draw(gc);
        }
    }

    public void addTargets(NormalEnemy enemy) {
        this.targets.add(enemy);
    }

    public ArrayList<BaseEnemy> getTargets() {
        return this.targets;
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }

    public Attacker getAttacker() {
        return attacker;
    }
}

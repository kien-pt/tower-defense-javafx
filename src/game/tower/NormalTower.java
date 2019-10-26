package game.tower;

import game.enemy.NormalEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.troops.Bullet;

import java.util.ArrayList;

public class NormalTower extends ActiveTower {
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<NormalEnemy> targets = new ArrayList<>();
    private ArrayList<NormalEnemy> enemies = new ArrayList<>();
    private double range;
    public NormalTower(int posX, int posY) {
        super(posX, posY, new Image("file:resources/tower/normal_tower.png"));
        this.range = 600;
        for (int i = 0; i < 5; i++)
            this.enemies.add(new NormalEnemy(400 - i * 10, 100 + i * 100));
        CreateTarget(this.enemies);
        //while (!this.targets.isEmpty())
        this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
        this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
        this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
        this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
        this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
        this.bullets.add(new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true));
    }

    public double getRange() {
        return this.range;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        for (NormalEnemy i : this.enemies)
            i.draw(gc);
        for (Bullet i : bullets)
            i.draw(gc);
        /*while (!this.targets.isEmpty()){
            this.bullets = new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true);
            //this.bullets.draw(gc);
            this.bullets.MoveBullet(this.targets.get(0).getPosX(),this.targets.get(0).getPosY());
            this.targets.remove(0);
        }*/
        if (!this.bullets.isEmpty() && !this.targets.isEmpty()) {
            this.bullets.get(0).MoveBullet(this.targets.get(0).getPosX(), this.targets.get(0).getPosY());
            if (this.bullets.get(0).getPosX() == this.targets.get(0).getPosX() && this.bullets.get(0).getPosY() == this.targets.get(0).getPosY()) {
                this.bullets.remove(0);
                this.targets.remove(0);
            }
        }
    }

    public void CreateBullet() {

    }

    public void CreateTarget(ArrayList<NormalEnemy> enemies) {
        for (int i = 0; i < enemies.size(); i++) {
            double distance = Math.sqrt(Math.pow(this.posX - enemies.get(i).getPosX(), 2) + Math.pow(this.posY - enemies.get(i).getPosY(), 2));
            if (distance <= this.getRange()) {
                this.targets.add(enemies.get(i));
            }
        }
    }

    public void Shoot() {
        if (!this.targets.isEmpty()) {
            //this.bullet = new Bullet(this.posX + this.getWidth() / 2, this.posY + this.getHeight() / 2, 5, 5, true);
        }
    }
}

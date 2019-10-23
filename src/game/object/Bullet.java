package game.troops;

import game.object.GameObject;
import javafx.scene.image.Image;

/**
 * Created by Khiem472 on 17/10/19.
 */
public class Bullet extends GameObject {
    private int xVal;
    private int yVal;
    private boolean isFired;

    public Bullet(int posX, int posY, int xVal, int yVal, boolean isFired) {
        super(posX, posY, new Image("file:resources/arrow.png"));
        this.xVal = xVal;
        this.yVal = yVal;
        this.isFired = isFired;
    }

    public void setFired(boolean isFired) {
        this.isFired = isFired;
    }

    public int getxVal() {
        return this.xVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }

    public int getyVal() {
        return this.yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }

    public boolean IsFired() {
        return this.isFired;
    }

    public void MoveBullet(int targetX, int targetY) {
        int distance = (int) Math.sqrt(Math.pow((double) targetX - posX, 2) + Math.pow((double) targetY - posY, 2));
        if (isFired == true) {
            this.posX += ((targetX - posX) / (distance / 5));
            this.posY += ((targetY - posY) / (distance / 5));
            if (this.posX >= targetX || this.posY >= targetY)
                isFired = false;
        }
    }
}

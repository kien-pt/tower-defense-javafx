package game.troops;

import game.object.GameObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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

    public void MoveBullet(int targetX, int targetY) {
        int distance = (int) Math.sqrt(Math.pow((double) targetX - posX, 2) + Math.pow((double) targetY - posY, 2));
        distance /= 5;


        if (this.IsFired()) {
            if (distance != 0) {
                double moveX = 0, moveY = 0;
                moveX = (targetX - posX) / distance;
                moveY = (targetY - posY) / distance;

                setPosX(posX + (int) moveX);
                setPosY(posY + (int) moveY);


                ImageView iv = new ImageView(new Image("file:resources/arrow.png"));

                double cc = 0;
                if (moveX < 0) cc = 180;
                if (moveX == 0 && moveY < 0) cc = 180;

                if (moveX != 0)
                    iv.setRotate(Math.toDegrees(Math.atan(moveY / moveX)) + cc);
                else iv.setRotate(90 + cc);

                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                Image rotatedImage = iv.snapshot(params, null);
                setImage(rotatedImage);


            }
        }
    }

    public void setFired(boolean isFired) {
        this.isFired = isFired;
    }

    public boolean IsFired() {
        return this.isFired;
    }
}
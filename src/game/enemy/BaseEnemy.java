package game.enemy;

import game.object.GameObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.font.ImageGraphicAttribute;

public class BaseEnemy extends GameObject implements UpdatableObject {
    public Image frame[] = new Image[12];
    public String tag;
    public double speed, doublePosX, doublePosY;
    private int currentFrame, hp;
    private long lastAniTime, lastMoveTime;

    public BaseEnemy(int posX, int posY, String tag, Image image) {
        super(posX, posY, image);
        this.tag = tag;
        currentFrame = 0;
        doublePosX = getPosX();
        doublePosY = getPosY();
        lastAniTime = lastMoveTime = System.currentTimeMillis();
        for (int i = 0; i < 12; i++) frame[i] = new Image("file:resources/enemy/" + tag + i + ".png");
        setImage(frame[0]);
    }

    private void move(double x, double y) {
        doublePosX += x;
        doublePosY += y;
        setPosX((int) doublePosX);
        setPosY((int) doublePosY);
    }

    @Override
    public void update() {
        if (lastAniTime + 60 < System.currentTimeMillis()) {
            lastAniTime = System.currentTimeMillis();
            currentFrame = (currentFrame + 1) % 12;
            setImage(frame[currentFrame]);
        }

        if (lastMoveTime + 40 / speed < System.currentTimeMillis()) {
            //Chia ra các checkpoint để enemy di chuyển
            if (getPosX() >= 850) move(-1.8, 0);
            if (790 <= getPosX() && getPosX() <= 850) move(-1, -1.5);
            if (750 <= getPosX() && getPosX() <= 790) move(-1.2728, -1.2728);
            if (670 <= getPosX() && getPosX() <= 750) move(-1.5, -1);
            if (480 <= getPosX() && getPosX() <= 670) move(-1.8, 0);
            if (440 <= getPosX() && getPosX() <= 480) move(-1.5, 1);
            if (400 <= getPosX() && getPosX() <= 440) move(-1.2728, 1.2728);
            if (320 <= getPosX() && getPosX() <= 400) move(-1, 1.5);
            if (getPosX() <= 320) move(-1.8, 0);
            lastMoveTime = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        int width = (int) getImage().getWidth();
        int height = (int) getImage().getHeight();
        gc.drawImage(getImage(), 0, 0, width, height, getPosX() + width, getPosY() - height, -width, height);
    }
}

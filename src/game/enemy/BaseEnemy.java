package game.enemy;

import game.object.AnimateObject;
import game.object.GameObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BaseEnemy extends GameObject implements UpdatableObject, AnimateObject {
    private String tag;
    private Image frame[] = new Image[12];
    private long lastAniTime, lastMoveTime;
    private double speed, doublePosX, doublePosY;
    private int currentFrame, hp, path, frameAmount;


    public BaseEnemy(int posX, int posY, String tag, int path) {
        super(posX, posY, new Image("file:resources/enemy/" + tag + "_0.png"));
        this.tag = tag;
        this.path = path;
        currentFrame = 0;
        doublePosX = getPosX();
        doublePosY = getPosY();
        lastAniTime = lastMoveTime = System.currentTimeMillis();
        for (int i = 0; i < 12; i++) frame[i] = new Image("file:resources/enemy/" + tag + "_" + i + ".png");
    }

    private void move(double x, double y) {
        doublePosX += x;
        doublePosY += y;
        setPosX((int) doublePosX);
        setPosY((int) doublePosY);
    }

    @Override
    public void update() {
        animateUpdate(16);
        if (lastMoveTime + 10 / speed < System.currentTimeMillis()) {
            //Chia ra các checkpoint để enemy di chuyển
            if (getPosX() >= 850) move(-0.9, 0);
            if (790 <= getPosX() && getPosX() <= 850) move(-0.5, -0.75 * path);
            if (750 <= getPosX() && getPosX() <= 790) move(-0.6364, -0.6364 * path);
            if (670 <= getPosX() && getPosX() <= 750) move(-0.75, -0.5 * path);
            if (480 <= getPosX() && getPosX() <= 670) move(-0.9, 0);
            if (440 <= getPosX() && getPosX() <= 480) move(-0.75, 0.5 * path);
            if (400 <= getPosX() && getPosX() <= 440) move(-0.6364, 0.6364 * path);
            if (320 <= getPosX() && getPosX() <= 400) move(-0.5, 0.75 * path);
            if (getPosX() <= 320) move(-0.9, 0);
            lastMoveTime = System.currentTimeMillis();
        }
    }

    @Override
    public void animateUpdate(int FPS) {
        if (lastAniTime + 1000 / FPS < System.currentTimeMillis()) {
            lastAniTime = System.currentTimeMillis();
            currentFrame = (currentFrame + 1) % frameAmount;
            setImage(frame[currentFrame]);
        }
    }

    public void setSpeed(double speed) { this.speed = speed; }
    public void setFrameAmount(int frameAmount) { this.frameAmount = frameAmount; }
}

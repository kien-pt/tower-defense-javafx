package game.enemy;

import game.gui.HealthBar;
import game.object.Effect;
import game.object.GameObject;
import javafx.scene.image.Image;
import game.object.AnimateObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;

public class BaseEnemy extends GameObject implements UpdatableObject, AnimateObject {
    private Effect dying;
    private Image[] frame;
    private boolean detroyed;
    private HealthBar healthBar;
    private long lastAniTime, lastMoveTime;
    private double speed, doublePosX, doublePosY;
    private int currentFrame, hp, path, reward, frameAmount, currentHp;


    BaseEnemy(int posX, int posY, String tag, int path, int hp, int reward) {
        super(posX, posY, new Image("file:resources/enemy/" + tag + "_0.png"));
        this.hp = hp;
        currentFrame = 0;
        this.path = path;
        this.currentHp = hp;
        this.reward = reward;
        frame = new Image[12];
        this.detroyed = false;
        doublePosX = getPosX();
        doublePosY = getPosY();
        lastAniTime = lastMoveTime = System.currentTimeMillis();
        this.healthBar = new HealthBar(posX + getWidth() / 2 - 15, posY - 10);
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
        if (lastMoveTime + 10 < System.currentTimeMillis()) {
            //Chia ra các checkpoint để enemy di chuyển
            if (getPosX() >= 850) move(-0.9 * speed, 0);
            if (790 <= getPosX() && getPosX() <= 850) move(-0.5 * speed, -0.75 * speed * path);
            if (750 <= getPosX() && getPosX() <= 790) move(-0.6364 * speed, -0.6364 * speed * path);
            if (670 <= getPosX() && getPosX() <= 750) move(-0.75 * speed, -0.5 * speed * path);
            if (480 <= getPosX() && getPosX() <= 670) move(-0.9 * speed, 0);
            if (440 <= getPosX() && getPosX() <= 480) move(-0.75 * speed, 0.5 * speed * path);
            if (400 <= getPosX() && getPosX() <= 440) move(-0.6364 * speed, 0.6364 * speed * path);
            if (320 <= getPosX() && getPosX() <= 400) move(-0.5 * speed, 0.75 * speed * path);
            if (getPosX() <= 320) move(-0.9 * speed, 0);
            healthBar.setPosX(getPosX() + getWidth() / 2 - 15);
            healthBar.setPosY(getPosY() - 10);
            lastMoveTime = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (getCurrentHp() > 0) super.draw(gc);
        else if (dying == null) dying = getTempDying(); else if (dying.isDestroyed()) detroyed = true;
        if (dying == null) healthBar.draw(gc);
        else {
            dying.draw(gc);
            dying.update();
        }
    }

    @Override
    public void animateUpdate(int FPS) {
        if (lastAniTime + 1000 / FPS < System.currentTimeMillis()) {
            currentFrame = (currentFrame + 1) % frameAmount;
            lastAniTime = System.currentTimeMillis();
            setImage(frame[currentFrame]);
        }
    }


    public int getHp() { return hp; }
    public int getReward() { return reward; }
    public Effect getTempDying() { return null; }
    public int getCurrentHp() { return currentHp; }
    public boolean isDetroyed() { return detroyed; }
    void setSpeed(double speed) { this.speed = speed; }
    public long getLastAniTime() { return lastAniTime; }
    public HealthBar getHealthBar() { return healthBar; }
    public long getLastMoveTime() { return lastMoveTime; }
    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }
    void setFrameAmount(int frameAmount) { this.frameAmount = frameAmount; }
    public void setLastAniTime(long lastAniTime) { this.lastAniTime = lastAniTime; }
    public void setLastMoveTime(long lastMoveTime) { this.lastMoveTime = lastMoveTime; }
}

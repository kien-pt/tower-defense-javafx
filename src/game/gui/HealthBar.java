package game.gui;

import game.object.GameObject;
import game.object.UpdatableObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class HealthBar extends GameObject implements UpdatableObject {
    private int health;
    private int tempHealth;

    public HealthBar(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        health = 0;
        tempHealth = health;
    }

    @Override
    public void update() {


    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(posX, posY, posX + tempHealth, posY);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(posX + tempHealth, posY, posX + tempHealth, posY + 3);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(posX + tempHealth, posY + 3, posX, posY + 3);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(posX, posY + 3, posX, posY);

        gc.setFill(Color.RED);
        gc.fillRect(posX, posY, tempHealth, 3);

        gc.setFill(Color.GREEN);
        gc.fillRect(posX, posY, health, 3);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setTempHealth(int health) {
        this.tempHealth = health;
    }
}
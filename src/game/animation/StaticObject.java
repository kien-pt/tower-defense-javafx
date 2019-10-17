package game.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StaticObject {
    public double scale;
    protected int posX, posY;
    private int height, width;
    private Image image;

    public StaticObject(int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        height = (int) image.getHeight();
        width = (int) image.getWidth();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(getImage(), getPosX(), getPosY());
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public Image getImage() {
        return image;
    }
}

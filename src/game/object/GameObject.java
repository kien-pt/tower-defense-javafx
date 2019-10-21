package game.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
    public double scale;
    protected int posX, posY;
    private int height, width;
    private Image image;

    public GameObject(int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        height = (int) image.getHeight();
        width = (int) image.getWidth();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(getImage(), getPosX(), getPosY());
    }

    protected boolean click(int mouseX, int mouseY) {
        return (getPosX() <= mouseX && mouseX <= getPosX() + getImage().getWidth()
                && getPosY() <= mouseY && mouseY <= getPosY() + getImage().getHeight());
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

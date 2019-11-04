package game.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
    protected int posX, posY;
    double scale;
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

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public GameObject setPosX(int posX) {
        this.posX = posX;
        return this;
    }

    public int getPosY() {
        return posY;
    }

    public GameObject setPosY(int posY) {
        this.posY = posY;
        return this;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String url) {
        setImage(new Image(url));
    }
}

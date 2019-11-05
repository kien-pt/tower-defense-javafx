package game.tower;

import javafx.scene.canvas.GraphicsContext;

public interface BaseTower {
    void onHover(int mouseX, int mouseY);
    void drawLayout(GraphicsContext gc);
    void update();

    void onClick(int mouseX, int mouseY);

    boolean isUpgrade();

    int getPosX();
    int getPosY();
}

package game.tower;

import javafx.scene.canvas.GraphicsContext;

public interface BaseTower {
    void hover(int mouseX, int mouseY);
    void drawLayout(GraphicsContext gc);
    void setUpgradeRate(int rate);
    void upgrade();
    void attack();
}

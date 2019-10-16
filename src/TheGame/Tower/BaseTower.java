package TheGame.Tower;

import javafx.scene.canvas.GraphicsContext;

public interface BaseTower {
    void onClick(int mouseX, int mouseY);
    void hover(int mouseX, int mouseY);
    void drawLayout(GraphicsContext gc);
    void update();
    void setUpgradeRate(int rate);

    int getPosX();
    int getPosY();
    boolean isUpgrade();
}

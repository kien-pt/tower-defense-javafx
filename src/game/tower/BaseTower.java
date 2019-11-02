package game.tower;

import javafx.scene.canvas.GraphicsContext;

public interface BaseTower {
    //    void onClick(int mouseX, int mouseY);
    void hover(int mouseX, int mouseY);
    void drawLayout(GraphicsContext gc);
    void setUpgradeRate(int rate);
    void upgrade();
    void attack();

//    không cần 2 hàm này vì nó được kế thừa từ GameObject
//    int getPosX();
//    int getPosY();
}

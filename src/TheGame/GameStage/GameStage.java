package TheGame.GameStage;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import TheGame.Sprite.GameObject;
import TheGame.Sprite.StaticObject;
import TheGame.Tower.BaseTower;
import TheGame.Tower.NormalTower;

import java.util.ArrayList;

public abstract class GameStage {
    private Image map;
    public ArrayList<StaticObject> ornament = new ArrayList<>();
    public ArrayList<BaseTower> towers = new ArrayList<>();

    // Load map của level tương ứng
    public GameStage(int k) {
        map = new Image("TheGame/Resources/Stage_" + k + ".png");
    }

    // Thêm vật trang trí
    public abstract void addOrnament();

    /**
     * Vẽ màn chơi
     * Vẽ các tháp
     * Vẽ vật trang trí
     * Vẽ các layout
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(map, 0, -50);
        for (BaseTower tower: towers) ((GameObject) tower).draw(gc);
        for (StaticObject i: ornament) i.draw(gc);
        for (BaseTower tower: towers) tower.drawLayout(gc);
    }

    /**
     * Cập nhật màn chơ8
     */
    public void update() {
        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).update();
            if (towers.get(i).isUpgrade())
                towers.set(i, new NormalTower(towers.get(i).getPosX() - 10, towers.get(i).getPosY() - 10));
        }
    }

    /** Đọc các sự kiện bàn phím chuột
     *  key = 0: Mouse Position When Clicking
     *  key = 1: Mouse Position
     */
    public void input(int key, double mouseX, double mouseY) {
        if (key == 0) for (BaseTower tower: towers) tower.onClick((int) mouseX, (int) mouseY);
        if (key == 1) for (BaseTower tower: towers) tower.hover((int) mouseX, (int) mouseY);
    }
}

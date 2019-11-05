package game.stage;

import game.gui.Icon;
import game.object.GameObject;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Welcome extends GameStage {
    private Image mainMenuImage;
    private Icon startIcon;
    private GameObject[] logoBlink = new GameObject[19];
    private int k = 0, stage;
    private long lastBlinkTime, waitTime;

    public Welcome() {
        super(0);
        stage = 0;
        waitTime = 50;
        startIcon = new Icon(436, 330, 8);
        lastBlinkTime = System.currentTimeMillis();
        mainMenuImage = new Image("file:resources/Stage_0.png", 1580, 900, false, true);
        logoBlink[0] = new GameObject(360 + 39, 10 + 95, new Image("file:resources/welcome/logo (0).png"));
        logoBlink[1] = new GameObject(360 + 38, 10 + 86, new Image("file:resources/welcome/logo (1).png"));
        logoBlink[2] = new GameObject(360 + 38, 10 + 79, new Image("file:resources/welcome/logo (2).png"));
        logoBlink[3] = new GameObject(360 + 38, 10 + 73, new Image("file:resources/welcome/logo (3).png"));
        logoBlink[4] = new GameObject(360 + 81, 10 + 68, new Image("file:resources/welcome/logo (4).png"));
        logoBlink[5] = new GameObject(360 + 105, 10 + 69, new Image("file:resources/welcome/logo (5).png"));
        logoBlink[6] = new GameObject(360 + 165, 10 + 69, new Image("file:resources/welcome/logo (6).png"));
        logoBlink[7] = new GameObject(360 + 190, 10 + 70, new Image("file:resources/welcome/logo (7).png"));
        logoBlink[8] = new GameObject(360 + 220, 10 + 71, new Image("file:resources/welcome/logo (8).png"));
        logoBlink[9] = new GameObject(360 + 262, 10 + 75, new Image("file:resources/welcome/logo (9).png"));
        logoBlink[10] = new GameObject(360 + 276, 10 + 79, new Image("file:resources/welcome/logo (10).png"));
        logoBlink[11] = new GameObject(360 + 302, 10 + 80, new Image("file:resources/welcome/logo (11).png"));
        logoBlink[12] = new GameObject(360 + 327, 10 + 85, new Image("file:resources/welcome/logo (12).png"));
        logoBlink[13] = new GameObject(360 + 345, 10 + 85, new Image("file:resources/welcome/logo (13).png"));
        logoBlink[14] = new GameObject(360 + 363, 10 + 91, new Image("file:resources/welcome/logo (14).png"));
        logoBlink[15] = new GameObject(360 + 377, 10 + 94, new Image("file:resources/welcome/logo (15).png"));
        logoBlink[16] = new GameObject(360 + 387, 10 + 105, new Image("file:resources/welcome/logo (16).png"));
        logoBlink[17] = new GameObject(360 + 398, 10 + 139, new Image("file:resources/welcome/logo (17).png"));
        logoBlink[18] = new GameObject(1200, 900, new Image("file:resources/welcome/logo (17).png"));
    }

    @Override
    public void addOrnament() {
        ornament.add(new GameObject(-190, 0, mainMenuImage));
        ornament.add(new GameObject(360, 10, new Image("file:resources/welcome/logo.png")));
    }

    @Override
    public void input(int key, double mouseX, double mouseY) {
        if (key == 1) startIcon.onHover((int) mouseX, (int) mouseY);
        if (key == 0) if (startIcon.onClick((int) mouseX, (int) mouseY) > 0) stage = 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        startIcon.draw(gc);
        logoBlink[k].draw(gc);
        if (lastBlinkTime + waitTime < System.currentTimeMillis()) {
            k = (k + 1) % 19;
            waitTime -= 5;
            if (k == 0) waitTime = 45;
            if (k == 18) waitTime = 2000;
            lastBlinkTime = System.currentTimeMillis();
        }
    }

    public int getStage() {
        return stage;
    }
}

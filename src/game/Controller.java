package game;

import game.stage.Level1;
import game.stage.Welcome;
import javafx.scene.Scene;
import game.stage.GameStage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

public class Controller {
    private int stage;
    private GameStage level;

    Controller() {
        stage = 0;
        level = new Welcome();
        level.addOrnament();
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, 1200, 900);
        level.draw(gc);
    }

    public void update() {
        level.update();
        if (stage == 0 && ((Welcome) level).getStage() > 0) {
            stage = 1;
            level = new Level1();
            level.addOrnament();
        }
    }

    void mouseController(Scene theScene) {
        theScene.setOnMouseClicked( new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                level.input(0, e.getX(), e.getY());
            }
        });
        theScene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                level.input(1, event.getX(), event.getY());
            }
        });
    }
}

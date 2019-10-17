package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import game.stage.GameStage;
import game.stage.Level1;

public class Controller {
    private GameStage level;

    Controller() {
        level = new Level1(1);
        level.addOrnament();
    }

    public void draw(GraphicsContext gc) {
        level.draw(gc);
    }
    public void update() { level.update();  }

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

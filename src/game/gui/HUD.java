package game.gui;

import game.object.GameObject;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class HUD extends GameObject {
    private int life, coins, wave;
    private Text text1, text2, text3;

    public HUD(int posX,int posY){
        super(posX,posY,new Image("file:resources/hud_background.png"));
        this.life = 5;
        this.wave = 0;
        this.coins = 1000;
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
    }

    public void DecreaseLife(){ life--; }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        Font myFont = Font.loadFont("file:resources/fast_99/fast99.ttf", 25);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        text1.setText(String.valueOf(life));
        text2.setText(String.valueOf(coins));
        text3.setText(String.valueOf(wave));

        gc.clearRect(45,25,10,10);
        gc.fillText(text1.getText(),78,57);
        gc.clearRect(125,25,40,10);
        gc.fillText(text2.getText(),180,57);
        gc.clearRect(60,60,5,10);
        gc.fillText("WAVE "+ text3.getText(),105,98);
    }

    public int getWave() { return wave; }
    public int getLife() { return life; }
    public int getCoins() { return coins; }
    public void setWave(int wave) { this.wave = wave; }
    public void setCoins(int coins) { this.coins = coins; }
}

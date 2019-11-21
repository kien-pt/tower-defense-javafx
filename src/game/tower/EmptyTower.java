package game.tower;

public class EmptyTower extends BaseTower {

    public EmptyTower(int posX, int posY) {
        super(posX, posY, "empty",0,0,0,0);
        setRange(0);
        setShoot(false);
    }
}

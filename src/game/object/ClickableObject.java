package game.object;

public interface ClickableObject {
    void onHover(int mouseX, int mouseY);

    void onClick(int mouseX, int mouseY);
}

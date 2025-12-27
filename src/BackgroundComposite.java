import java.awt.*;
import java.util.List;

public class BackgroundComposite extends GameObject{
    List<GameSprite> BackgroundElements = new java.util.ArrayList<>();
    GameConfig config = GameConfig.getInstance();
    public BackgroundComposite(){
        super(0, 0, 0, 0, null);
        BackgroundElements.add(new GameSprite(0, 0, config.WIDTH, config.HEIGHT, "fond.png", new BackgroundScrollStrategy(), null));
        BackgroundElements.add(new GameSprite(config.WIDTH, 0, config.WIDTH, config.HEIGHT, "fond.png", new BackgroundScrollStrategy(), null));

    }
    public void reset(){
        BackgroundElements.get(0).x = config.WIDTH;
        BackgroundElements.get(1).x = 0;
    }

    @Override
    public void update() {
        for (GameSprite bg : BackgroundElements) {
            bg.update();
        }

        if (BackgroundElements.getFirst().x <= 0) {
            BackgroundElements.getFirst().x = config.WIDTH;
        }
    }

    @Override
    public void draw(Graphics g) {
        for (GameSprite bg : BackgroundElements) {
            bg.draw(g);
        }
    }
}

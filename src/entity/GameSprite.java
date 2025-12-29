package entity;

import strategy.MovementStrategy;

import java.awt.*;

// Leaf (Feuille) : Un objet simple (ex: le Chat, ou un seul Livre)
public class GameSprite extends GameObject {
    private final MovementStrategy strategy;
    private final Color fallbackColor; // Couleur si pas d'image

    public GameSprite(int x, int y, int w, int h, String imgPath, MovementStrategy strat, Color color) {
        super(x, y, w, h, imgPath);
        this.strategy = strat;
        this.fallbackColor = color;
    }

    public MovementStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public void update() {
        if (strategy != null) strategy.move(this);
    }

    @Override
    public void draw(Graphics g) {
        if (image != null && image.getWidth(null) > 0) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            // Dessin de secours si pas d'image
            g.setColor(fallbackColor);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
}

package entity;

import config.GameConfig;
import strategy.BooksScrollStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Composite : Un Obstacle composé de deux livres (Haut et Bas)
public class ObstacleComposite extends GameObject {
    private final List<GameSprite> children = new ArrayList<>();
    private boolean passed = false; // Pour compter le score

    public ObstacleComposite(int x) {
        super(x, 0, 0, 0, null); // x géré globalement, y ignoré
        generateBooks(x);
    }

    private void generateBooks(int x) {
        GameConfig conf = GameConfig.getInstance();

        // 1. Define the constant dimensions
        int fixedWidth = 90;
        // Using the specific ratio you provided: 90 * 8.377 ≈ 754 pixels
        int fixedHeight = (int) (fixedWidth * 8.377);

        int minGapY = 50;
        int maxRange = conf.HEIGHT - conf.PIPE_GAP - 100;


        int gapY = new Random().nextInt(maxRange) + minGapY;

        // 3. Create Top Book
        children.add(new GameSprite(x, gapY - fixedHeight, fixedWidth, fixedHeight, "images/livre.png", new BooksScrollStrategy(), Color.RED));

        // 4. Create Bottom Book
        children.add(new GameSprite(x, gapY + conf.PIPE_GAP, fixedWidth, fixedHeight, "images/livre.png", new BooksScrollStrategy(), Color.RED));
    }

    @Override
    public void update() {
        // Le Composite délègue la mise à jour aux enfants
        for (GameSprite sprite : children) {
            sprite.update();
        }
        // Mise à jour de la position X du composite (basée sur le premier enfant)
        if (!children.isEmpty()) this.x = children.getFirst().x;
    }

    @Override
    public void draw(Graphics g) {
        // Le Composite délègue le dessin aux enfants
        for (GameSprite sprite : children) {
            sprite.draw(g);
        }
    }

    // Vérifie la collision avec un objet (le chat)
    public boolean checkCollision(Rectangle playerBounds) {
        for (GameSprite sprite : children) {
            if (sprite.getBounds().intersects(playerBounds)) return true;
        }
        return false;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean p) {
        this.passed = p;
    }
}

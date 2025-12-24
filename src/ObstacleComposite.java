import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Composite : Un Obstacle composé de deux livres (Haut et Bas)
class ObstacleComposite extends GameObject {
    private List<GameSprite> children = new ArrayList<>();
    private boolean passed = false; // Pour compter le score

    public ObstacleComposite(int x) {
        super(x, 0, 0, 0, null); // x géré globalement, y ignoré
        generateBooks(x);
    }

    private void generateBooks(int x) {
        GameConfig conf = GameConfig.getInstance();
        int gapY = new Random().nextInt(conf.HEIGHT - conf.PIPE_GAP - 100) + 50;

        // Livre du haut
        children.add(new GameSprite(x, 0, 60, gapY, "livre.png", new ScrollStrategy(), Color.RED));
        // Livre du bas
        children.add(new GameSprite(x, gapY + conf.PIPE_GAP, 60, conf.HEIGHT - (gapY + conf.PIPE_GAP), "livre.png", new ScrollStrategy(), Color.RED));
    }

    @Override
    public void update() {
        // Le Composite délègue la mise à jour aux enfants
        for (GameSprite sprite : children) {
            sprite.update();
        }
        // Mise à jour de la position X du composite (basée sur le premier enfant)
        if (!children.isEmpty()) this.x = children.get(0).x;
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

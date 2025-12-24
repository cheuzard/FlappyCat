import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// ==========================================
// MODÈLE (MVC)
// ==========================================
class FlappyModel extends GameSubject {
    public GameSprite cat;
    public List<ObstacleComposite> obstacles;
    public int score;
    public boolean isGameOver;
    private int tickCounter;

    public FlappyModel() {
        reset();
    }

    public void reset() {
        GameConfig conf = GameConfig.getInstance();
        // Le chat commence avec la stratégie Gravité
        cat = new GameSprite(100, conf.HEIGHT / 2, 40, 40, "chat.png", new GravityStrategy(), Color.ORANGE);
        obstacles = new ArrayList<>();
        score = 0;
        isGameOver = false;
        tickCounter = 0;
        notifyObservers();
    }

    public void jump() {
        if (!isGameOver) {
            MovementStrategy s = cat.getStrategy();
            if (s instanceof GravityStrategy) {
                ((GravityStrategy) s).jump();
            }
        } else {
            reset(); // Rejouer si Game Over
        }
    }

    public void updateGame() {
        if (isGameOver) return;

        // 1. Mise à jour du chat
        cat.update();

        // 2. Gestion des obstacles (Génération & Suppression)
        tickCounter++;
        if (tickCounter % 100 == 0) { // Tous les 100 ticks, un nouvel obstacle
            obstacles.add(new ObstacleComposite(GameConfig.getInstance().WIDTH));
        }

        List<ObstacleComposite> toRemove = new ArrayList<>();
        for (ObstacleComposite obs : obstacles) {
            obs.update();

            // Collision
            if (obs.checkCollision(cat.getBounds())) {
                isGameOver = true;
                GameConfig.getInstance().saveHighScore(score);
            }

            // Score
            if (!obs.isPassed() && obs.x + 60 < cat.x) {
                score++;
                obs.setPassed(true);
            }

            // Nettoyage hors écran
            if (obs.x < -100) toRemove.add(obs);
        }
        obstacles.removeAll(toRemove);

        // 3. Collision Sol/Plafond
        if (cat.y < 0 || cat.y > GameConfig.getInstance().HEIGHT) {
            isGameOver = true;
            GameConfig.getInstance().saveHighScore(score);
        }

        // 4. Notifier la Vue
        notifyObservers();
    }
}

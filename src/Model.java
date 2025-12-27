import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// ==========================================
// MODÈLE (MVC)
// ==========================================
class Model extends GameSubject {
    public GameSprite cat;
    public List<ObstacleComposite> obstacles;
    public BackgroundComposite background = new BackgroundComposite();
    public int score;
    public boolean isGameOver;
    public boolean isFirstRun = true;
    private int tickCounter;

    public Model() {
        reset();
    }

    public void reset() {
        GameConfig conf = GameConfig.getInstance();
        // Le chat commence avec la stratégie Gravité
        cat = new GameSprite(100, conf.HEIGHT / 2 , 80, (int)(80*0.575), "chat.png", new GravityStrategy(), Color.ORANGE);
        obstacles = new ArrayList<>();
        score = 0;
        isGameOver = false;
        tickCounter = 99; // Pour générer un obstacle immédiatement
        background.reset();
        notifyObservers();
    }

    public void jump() {
        if (!isGameOver && !isFirstRun) {
            MovementStrategy s = cat.getStrategy();
            if (s instanceof GravityStrategy) {
                ((GravityStrategy) s).jump();
            }
        } else {
            isFirstRun = false;
            reset(); // Rejouer si Game Over
            this.jump();
        }
    }

    public void updateGame() {
        if (isGameOver || isFirstRun) return;

        // 1. Mise à jour du chat
        cat.update();

        // 2. Gestion des obstacles (Génération & Suppression)
        tickCounter++;
        if (tickCounter % GameConfig.getInstance().OBSTACLE_FREQUENCY == 0) { // Tous les 100 ticks, un nouvel obstacle
            obstacles.add(new ObstacleComposite(GameConfig.getInstance().WIDTH));
            tickCounter = 0;
        }

        // Mise à jour du background
        background.update();

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
            if (obs.x < -400) toRemove.add(obs);
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

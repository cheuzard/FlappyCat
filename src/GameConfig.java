import java.io.*;

// ==========================================
// 1. PATRON SINGLETON : Configuration du Jeu
// ==========================================
// Rôle : Garantir une unique instance de configuration accessible partout.
// Gestion du stockage (High Score) incluse ici.
class GameConfig {
    private static GameConfig instance;

    public final int WIDTH = 800;
    public final int HEIGHT = 600;
    public final int PIPE_SPEED = 5;
    public final int GRAVITY = 1;
    public final int JUMP_STRENGTH = -15;
    public final int PIPE_GAP = 200; // Espace entre les livres

    private int highScore = 0;
    private final String SCORE_FILE = "highscore.txt";

    private GameConfig() {
        loadHighScore();
    }

    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    // Gestion du stockage (fichier)
    public void saveHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
                writer.write(String.valueOf(highScore));
            } catch (IOException e) {
                System.err.println("Erreur sauvegarde score: " + e.getMessage());
            }
        }
    }

    private void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) highScore = Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            highScore = 0;
        }
    }

    public int getHighScore() {
        return highScore;
    }
}

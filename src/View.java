import javax.swing.*;
import java.awt.*;

// ==========================================
// VUE (MVC)
// ==========================================
class View extends JPanel implements GameObserver {
    private final Model model;
    private Image bgImage;

    public View(Model model) {
        this.model = model;
        this.model.attach(this); // S'abonne au modèle
        setPreferredSize(new Dimension(GameConfig.getInstance().WIDTH, GameConfig.getInstance().HEIGHT));
        setFocusable(true);
        try {
            bgImage = new ImageIcon("fond.png").getImage();
        } catch (Exception e) {
            bgImage = null;
        }
    }

    @Override
    public void updateView() {
        repaint(); // Appel standard Swing pour redessiner
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessin Background
        if (bgImage != null && bgImage.getWidth(null) > 0) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(200, 230, 255)); // Bleu ciel par défaut
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Dessin Obstacles (Via Composite)
        for (ObstacleComposite obs : model.obstacles) {
            obs.draw(g);
        }

        // Dessin Chat
        model.cat.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + model.score, 20, 30);
        g.drawString("Best: " + GameConfig.getInstance().getHighScore(), 20, 60);

        if (model.isGameOver) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", GameConfig.getInstance().WIDTH/2 - 40*3, 250);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Appuyez sur ESPACE pour rejouer", GameConfig.getInstance().WIDTH/2 - 150, 300);
        }
    }
}

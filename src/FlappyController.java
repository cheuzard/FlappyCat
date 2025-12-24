import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// ==========================================
// CONTRÔLEUR (MVC)
// ==========================================
class FlappyController implements KeyListener {
    private FlappyModel model;
    private Timer timer;

    public FlappyController(FlappyModel model, FlappyView view) {
        this.model = model;

        // Gestion des touches
        view.addKeyListener(this);

        // Boucle de jeu (60 FPS environ)
        timer = new Timer(16, e -> model.updateGame());
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.jump();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

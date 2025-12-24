import javax.swing.*;
import java.awt.*;

// Component (Interface commune)
abstract class GameObject {
    public int x, y, width, height;
    protected Image image; // Pour le dessin

    public GameObject(int x, int y, int w, int h, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        // Chargement simple d'image
        try {
            this.image = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            this.image = null;
        }
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

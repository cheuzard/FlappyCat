package entity;

import util.ResourceLoader;

import java.awt.*;

// Component (Interface commune)
public abstract class GameObject {
    public int x, y, width, height;
    protected Image image; // Pour le dessin

    public GameObject(int x, int y, int w, int h, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        // Chargement simple d'image
        this.image = ResourceLoader.getInstance().getImage(imagePath);
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

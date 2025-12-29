package strategy;

import config.GameConfig;
import entity.GameObject;

// Stratégie pour les Livres (Défilement linéaire vers la gauche)
public class BooksScrollStrategy implements MovementStrategy {
    @Override
    public void move(GameObject obj) {
        obj.x -= GameConfig.getInstance().PIPE_SPEED;
    }
}

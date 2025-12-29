package strategy;

import config.GameConfig;
import entity.GameObject;

// Stratégie pour le Chat (Gravité + Saut)
public class GravityStrategy implements MovementStrategy {
    private int velocity = 0;

    public void jump() {
        velocity = -GameConfig.getInstance().JUMP_STRENGTH;
    }

    @Override
    public void move(GameObject obj) {
        velocity += GameConfig.getInstance().GRAVITY;
        obj.y += velocity;
    }
}

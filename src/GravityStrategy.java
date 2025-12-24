// Stratégie pour le Chat (Gravité + Saut)
class GravityStrategy implements MovementStrategy {
    private int velocity = 0;

    public void jump() {
        velocity = GameConfig.getInstance().JUMP_STRENGTH;
    }

    @Override
    public void move(GameObject obj) {
        velocity += GameConfig.getInstance().GRAVITY;
        obj.y += velocity;
    }

    public void reset() {
        velocity = 0;
    }
}

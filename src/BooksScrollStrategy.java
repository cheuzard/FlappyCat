// Stratégie pour les Livres (Défilement linéaire vers la gauche)
class BooksScrollStrategy implements MovementStrategy {
    @Override
    public void move(GameObject obj) {
        obj.x -= GameConfig.getInstance().PIPE_SPEED;
    }
}

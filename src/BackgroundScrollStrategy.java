class BackgroundScrollStrategy implements MovementStrategy{
    @Override
    public void move(GameObject obj) {
        obj.x -= GameConfig.getInstance().BACKGROUND_SPEED;

        if (obj.x <= -GameConfig.getInstance().WIDTH) {
            obj.x = 0;
        }
    }
}

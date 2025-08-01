class MoveFixed implements MovementStrategy {
    @Override
    public void move(int heading, int distance) {
        System.out.println("Fixed. No change in position.");
    }
}

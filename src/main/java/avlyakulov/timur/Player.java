package avlyakulov.timur;

public class Player {
    private int life;

    public Player() {
        this.life = 5;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void decreaseLife() {
        --life;
    }
}

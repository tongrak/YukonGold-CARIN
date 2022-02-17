package yukongold.carin.gamestuff;

public class Coor {
    private final int x;
    private final int y;

    public Coor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Coor c1){
        return (this.x == c1.x) && (this.y==c1.y);
    }
}

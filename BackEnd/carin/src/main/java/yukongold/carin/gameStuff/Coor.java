package yukongold.carin.gamestuff;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coor coor = (Coor) o;
        return x == coor.x && y == coor.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

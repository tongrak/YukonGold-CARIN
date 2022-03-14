package yukongold.carin.gamestuff;

import java.util.Objects;

public class Coor {
    private final int x;
    private final int y;

    public Coor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Getting integer X of this Coor.
     * 
     * @return integer representing X of this Coor.
     */
    public int getX() {
        return x;
    }

    /** Getting integer Y of this Coor.
     * 
     * @return integer representing Y of this Coor.
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        return x + "," + y;
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

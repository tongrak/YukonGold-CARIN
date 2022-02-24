package yukongold.carin.gamestuff;

interface PlayerAction{ }

public abstract class PlayerActAbs implements PlayerAction {
    private final Coor selectedCoor;

    public PlayerActAbs(Coor coor){
        this.selectedCoor = coor;
    }

    public Coor getSelectedCoor() {
        return selectedCoor;
    }
}
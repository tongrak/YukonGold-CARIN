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

class RelocateAct extends PlayerActAbs{
    private final Coor destination;

    public RelocateAct(Coor selectedCoor, Coor destCoor){
        super(selectedCoor);
        destination = destCoor;
    }

    public Coor getDestination() {
        return destination;
    }
}
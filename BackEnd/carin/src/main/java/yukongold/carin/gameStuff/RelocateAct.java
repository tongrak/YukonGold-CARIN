package yukongold.carin.gamestuff;

public class RelocateAct extends PlayerActAbs{
    private final Coor destination;

    public RelocateAct(Coor selectedCoor, Coor destCoor){
        super(selectedCoor);
        destination = destCoor;
    }

    public Coor getDestination() {
        return destination;
    }
}
package yukongold.carin.gamestuff;

import java.nio.file.Path;

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

class SpawnAct extends PlayerActAbs{
    private final Path selectedABType;

    public SpawnAct(Coor coor, Path geneCodePath) {
        super(coor);
        this.selectedABType = geneCodePath;
    }

    public Path getSelectedABType() {
        return selectedABType;
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
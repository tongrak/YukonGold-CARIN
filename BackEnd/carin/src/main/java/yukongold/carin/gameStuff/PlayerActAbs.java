package yukongold.carin.gameStuff;

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
    private final ABGene selectedABType;

    public SpawnAct(Coor coor, ABGene geneCodePath) {
        super(coor);
        this.selectedABType = geneCodePath;
    }

    public ABGene getSelectedABType() {
        return selectedABType;
    }
}

class RelocateAct extends PlayerActAbs{
    public RelocateAct(Coor coor){
        super(coor);
    }
}
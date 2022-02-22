package yukongold.carin.gamestuff;

import java.nio.file.Path;

public class SpawnAct extends PlayerActAbs{
    private final Path selectedABType;

    public SpawnAct(Coor coor, Path geneCodePath) {
        super(coor);
        this.selectedABType = geneCodePath;
    }

    public Path getSelectedABType() {
        return selectedABType;
    }
}
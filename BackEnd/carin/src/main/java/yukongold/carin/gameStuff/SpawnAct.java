package yukongold.carin.gamestuff;

import java.nio.file.Path;

public class SpawnAct extends PlayerActAbs{
    // private final Path selectedABType;
    private final int typeIndex;

    public SpawnAct(Coor coor, int typeIndex) {
        super(coor);
        this.typeIndex = typeIndex;
    }

    public int getTypeIndex() {
        return typeIndex;
    }
}
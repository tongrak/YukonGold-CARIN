package yukongold.carin.gamestuff;

import java.nio.file.Path;

public abstract class GamePiece {
    private Path theGeneCode;
    private int hp;

    public GamePiece(Path gene, int hp){
        this.theGeneCode = gene;
        this.hp = hp;
    }

    public Path getTheGeneCode() {
        return theGeneCode;
    }

    public void turnStart(){
        GeneEvaluator geneEval = GeneEvaluator.getInstance();
        // TODO And somehow send GPAction to GPManager

    }
}

class Antibody extends GamePiece{

    public Antibody(Path gene, int hp) {
        super(gene, hp);
    }
}

class Virus extends GamePiece{

    public Virus(Path gene, int hp) {
        super(gene, hp);
    }


}

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
    
    /** manipulate GP hp according to inpputed amount and return isDead
     * 
     * @param amount value of what to manipulated which could be negative(for damage) posive for(for heal)
     * @return isDead of this GP
     */
    public boolean hpManipulate(int amount){
        hp += amount;
        if(hp<0)return false;
        else return true;
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

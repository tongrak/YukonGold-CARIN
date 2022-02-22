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
        if(hp<0)return true;
        else return false;
    }

    public void turnStart(Coor coor){
        GeneEvaluator geneEval = GeneEvaluator.getInstance();
        GPManager GPMana = GPManager.getInstance();
        GPAction nextAct = geneEval.getGPAction(theGeneCode, coor);
        GPMana.actGPAct(nextAct, coor);
    }
}
<<<<<<< HEAD
=======


>>>>>>> c1607265bcfa020125597447bfbcbab0de28770f

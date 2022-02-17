package yukongold.carin.gameStuff;

public abstract class GamePiece {
    private GeneticCode theGeneCode;

    void setTheGeneCode(GeneticCode theGeneCode) {
        this.theGeneCode = theGeneCode;
    }

    GeneticCode getTheGeneCode() {
        return theGeneCode;
    }

    abstract void move();

    abstract void shoot();
}

class Antibody extends GamePiece{

    public Antibody(ABGene gene){
        super.setTheGeneCode(gene);
    }

    public ABGene getGeneticCode(){
        return (ABGene) super.getTheGeneCode();
    }

    @Override
    void move() {

    }

    @Override
    void shoot() {

    }

    void mutate(VirusGene gene){
        super.setTheGeneCode(gene);
    }
}

class Virus extends GamePiece{

    public Virus(VirusGene gene){
        super.setTheGeneCode(gene);
    }

    public VirusGene getGeneticCode(){return (VirusGene) super.getTheGeneCode();}

    @Override
    void move() {

    }

    @Override
    void shoot() {

    }
}

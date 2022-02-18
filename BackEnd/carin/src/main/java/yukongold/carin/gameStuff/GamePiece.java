package yukongold.carin.gamestuff;

import java.nio.file.Path;

public abstract class GamePiece {
    private Path theGeneCode;

    void setTheGeneCode(Path theGeneCode) {
        this.theGeneCode = theGeneCode;
    }

    public Path getTheGeneCode() {
        return theGeneCode;
    }

    abstract void turnStart();

    abstract void move();

    abstract void shoot();
}

class Antibody extends GamePiece{

    public Antibody(Path gene){
        super.setTheGeneCode(gene);
    }

    // public Path getGeneticCode(){return (Path) super.getTheGeneCode();}

    @Override
    void move() {

    }

    @Override
    void shoot() {

    }

    void mutate(Path gene){
        super.setTheGeneCode(gene);
    }

    @Override
    void turnStart() {
        // TODO Auto-generated method stub
        
    }
}

class Virus extends GamePiece{

    public Virus(Path gene){
        super.setTheGeneCode(gene);
    }

    // public Path getGeneticCode(){return (Path) super.getTheGeneCode();}

    @Override
    void move() {

    }

    @Override
    void shoot() {

    }

    @Override
    void turnStart() {
        // TODO Auto-generated method stub
        
    }
}

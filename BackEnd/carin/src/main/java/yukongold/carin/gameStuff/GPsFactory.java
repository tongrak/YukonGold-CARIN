package yukongold.carin.gameStuff;

public class GPsFactory {
    private GPsFactory instance;
    private GPsFactory(){ }
    public GPsFactory getInstance(){
        if(this.instance == null){
            this.instance = new GPsFactory();
        }
        return instance;
    }

    public static Virus createNewVirus(VirusGene gene){
        return new Virus(gene);
    }

    public static Antibody createNewAB(ABGene gene){
        return new Antibody(gene);
    }
}

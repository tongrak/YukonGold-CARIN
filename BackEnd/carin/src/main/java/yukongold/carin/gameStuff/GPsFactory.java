package yukongold.carin.gamestuff;

import java.nio.file.Path;

public class GPsFactory {
    private int ABHP = 25;
    private int VirusHP = 15;
    private static GPsFactory instance;
    private GPsFactory(){ }
    public static GPsFactory getInstance(){
        if(instance == null){
            instance = new GPsFactory();
        }
        return instance;
    }

    public Virus createNewVirus(Path gene){
        return new Virus(gene, VirusHP);
    }

    public Antibody createNewAB(Path gene){
        return new Antibody(gene, ABHP);
    }
}

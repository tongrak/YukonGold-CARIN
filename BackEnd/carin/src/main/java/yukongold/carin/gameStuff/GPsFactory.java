package yukongold.carin.gamestuff;

import java.nio.file.Path;

public class GPsFactory {
    private static GPsFactory instance;
    private GPsFactory(){ }
    public static GPsFactory getInstance(){
        if(instance == null){
            instance = new GPsFactory();
        }
        return instance;
    }

    public Virus createNewVirus(Path gene){
        return new Virus(gene);
    }

    public Antibody createNewAB(Path gene){
        return new Antibody(gene);
    }
}

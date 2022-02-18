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

    public Virus createNewVirus(Path gene, int hp){
        return new Virus(gene, hp);
    }

    public Antibody createNewAB(Path gene, int hp){
        return new Antibody(gene, hp);
    }
}

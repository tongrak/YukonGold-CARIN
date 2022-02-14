import java.nio.file.Path;

public class GeneticCode {
    protected final Path genePath;

    public GeneticCode(Path genePath) {
        this.genePath = genePath;
    }

    public Path getGenePath(){
        return this.genePath;
    }
}

class VirusGene extends GeneticCode{
    public VirusGene(Path genePath){
        super(genePath);
    }
}

class ABGene extends GeneticCode{
    public ABGene(Path genePath){
        super(genePath);
    }
}
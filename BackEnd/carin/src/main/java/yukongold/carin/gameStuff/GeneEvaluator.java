package yukongold.carin.gameStuff;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GeneEvaluator {
    Map<Path, DecodedGene> decodedGeneMap;
    private GeneEvaluator instance;
    private GeneEvaluator(){
        decodedGeneMap = new HashMap<>();
    }

    public GeneEvaluator getInstance(){
        if(this.instance == null){
            this.instance = new GeneEvaluator();
        }
        return this.instance;
    }

    public GPAction getGPAction(Path geneCode, Coor coor){
        try {
        if(decodedGeneMap.containsKey(geneCode)){
                return decodedGeneMap.get(geneCode).getGPAction(coor);
        }else{
            //
            return null;
        }
        } catch (SyntaxError e) {
            throw new RuntimeException("");
        }
    }
}

package yukongold.carin.gamestuff;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class GeneEvaluator {
    private static Map<Path, DecodedGene> decodedGeneMap;
    private static GeneEvaluator instance;
    private GeneEvaluator(){
        decodedGeneMap = new HashMap<>();
    }

    public static GeneEvaluator getInstance(){
        if(instance == null){
            instance = new GeneEvaluator();
        }
        return instance;
    }

    public GPAction getGPAction(Path geneCode, Coor coor){
        try {
            if(!decodedGeneMap.containsKey(geneCode)){
                Tokenizer tk = Tokenizer.getInstance();
                tk.tokenize(geneCode);
                DecodedGene newDeGene = new DecodedGene(tk);
                decodedGeneMap.put(geneCode, newDeGene);
            }
            return decodedGeneMap.get(geneCode).getGPAction(coor);

        } catch (SyntaxError e) {
            throw new RuntimeException("GeneEval.getGPAction(): GP at" + coor.toString() + " detected syntax error: " + e);
        }
    }
}

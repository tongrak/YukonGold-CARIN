package yukongold.carin;

import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.DecodedGene;
import yukongold.carin.gamestuff.Tokenizer;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DecodedGeneTest {
    static DecodedGene geneCodeA;

    @Test
    void quickCheck(){
        Tokenizer tk = Tokenizer.getInstance();
        tk.tokenize(Path.of("src/test/java/yukongold/carin/Test/sampleGeneCode.txt"));

        assertDoesNotThrow(() -> {geneCodeA = new DecodedGene(tk);});
        // DecodedGene sim = geneCodeA;
        System.out.println("Help us all");

    }

}
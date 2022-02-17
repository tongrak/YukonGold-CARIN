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
        Tokenizer tk = new Tokenizer();
        tk.tokenize(Path.of("Test/decodedGeneTester.txt"));
        assertDoesNotThrow(() -> {geneCodeA = new DecodedGene(tk);});
        // DecodedGene sim = geneCodeA;
        System.out.println("Help us all");

    }

}
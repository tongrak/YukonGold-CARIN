package yukongold.carin;

import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.Tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

// import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    @Test
    void tokenizingTest1() {
        Tokenizer tk = Tokenizer.getInstance();
        tk.tokenize(Path.of("src/test/java/yukongold/carin/sampleGeneCode/sampleGeneCode.txt"));
        assertEquals("virusLoc", tk.peer());
        assertEquals("virusLoc", tk.peer());
        assertEquals("virusLoc", tk.pop());
        assertEquals("=", tk.peer());
        tk.pop();
        tk.pop();
        tk.pop();
        assertEquals("(", tk.pop());
    }


}
package yukongold.carin;

import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.Tokenizer;

import java.nio.file.Path;

// import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    @Test
    void tokenize() {
        Tokenizer tk = Tokenizer.getInstance();
        tk.tokenize(Path.of("/Test/tokenizerCheck.txt"));
        System.out.println("Hold_on");
    }

    @Test
    void peer() {
    }

    @Test
    void pop() {
    }
}
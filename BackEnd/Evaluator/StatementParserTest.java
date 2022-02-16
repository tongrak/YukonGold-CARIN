import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatementParserTest {
    final static String[] reservedWordArr = {"antibody", "down", "downleft", "downright", "else", "if", "left", "move", "nearby", "right", "shoot", "then", "up", "upleft", "upright", "virus", "while"};
    final static Map<String, ExprAbs> bindingMap = new HashMap<>();
    static ExpressionParser expressionParserTester;
    static StatementParser statementParserTester;
    static Tokenizer tk;



    @BeforeAll
    static void setUp(){
        tk = new Tokenizer();
        tk.tokenize(Path.of("Test/sampleGeneCode.txt"));
        expressionParserTester = new ExpressionParser(tk,bindingMap); //need some coor of sample GP.
        statementParserTester = new StatementParser(tk, reservedWordArr, bindingMap, expressionParserTester);
    }

    @Test
    void parseS() throws SyntaxError {
        setUp();
        StatementBox s0 = statementParserTester.parseS();
        assertDoesNotThrow(()->{ StatementBox s1 = statementParserTester.parseS();});

    }
}
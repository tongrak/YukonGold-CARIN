import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//enum Direction{left, right, up, down, upleft, upright, downleft, downright}


public class ProgramParser {
    protected final Map<String, ExprAbs> binding;
    protected final LinkedList<StatementBox> statementLL;
    private final String[] reservedKeyWord = {"antibody", "else", "if", "move", "nearby", "shoot", "then", "virus", "while", "left", "right", "up", "down", "upleft", "upright", "downleft", "downright"};

    StatementParser sParer;
    ExpressionParser eParser;

    public ProgramParser(Tokenizer tk, Coor theCoor) throws SyntaxError {
        this.binding = new HashMap<>();
        this.statementLL = new LinkedList<>();

        eParser = new ExpressionParser(tk, binding, theCoor);
        sParer = new StatementParser(tk, reservedKeyWord, binding, eParser);

        StatementBox currStatement = null;
        while(tk.peer()!=null){
            currStatement = sParer.parseS();
        }
    }

    public LinkedList<StatementBox> getStatementLL() {
        return statementLL;
    }
}

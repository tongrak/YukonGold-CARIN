import java.util.LinkedList;
import java.util.Map;

//enum Direction{left, right, up, down, upleft, upright, downleft, downright}


public class ProgramParser {
    private Map<String, ExprAbs> binding;
    private LinkedList<StatementBox> statementLL;
    private final String[] reservedKeyWord = {"antibody", "else", "if", "move", "nearby", "shoot", "then", "virus", "while", "left", "right", "up", "down", "upleft", "upright", "downleft", "downright"};

}

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DecodedGene {
    protected final Map<String, ExprAbs> binding;
    protected final LinkedList<StatementBox> statementLL;

    StatementParser sParer;
    ExpressionParser eParser;

    public DecodedGene(Tokenizer tk, Coor theCoor) throws SyntaxError {
        final String[] reservedKeyWord = {"antibody", "else", "if", "move", "nearby", "shoot", "then", "virus", "while", "left", "right", "up", "down", "upleft", "upright", "downleft", "downright"};
        this.binding = new HashMap<>();
        this.statementLL = new LinkedList<>();

        eParser = new ExpressionParser(tk, binding, theCoor);
        sParer = new StatementParser(tk, reservedKeyWord, binding, eParser);

        StatementBox currStatement = null;
        while(tk.peer()!=null){
            statementLL.add(sParer.parseS());
        }
    }

    public GPAction getGPAction(Coor theCoor) throws SyntaxError{
        for (StatementBox s : statementLL) {
            GPAction currGPAction = s.getGPAction(theCoor);
            if(currGPAction != null)return currGPAction;
        }
        throw new SyntaxError("ProgramParser: statement list is GPAction less");
    }

     protected static int directionToEightDirec(String strIn) throws SyntaxError{
        String[] directionArr = {"left", "right", "up", "down", "upleft", "upright", "downleft", "downright"};
        boolean isDirection = false;
        for(String str: directionArr)if(strIn.equals(str)){isDirection = true; break;}
        if (!isDirection) throw new SyntaxError("Syntax error: incorrect direction");
        return switch (strIn) {
            case "left" ->  17;
            case "right" -> 13;
            case "up" -> 11;
            case "down" -> 15;
            case "upleft" -> 18;
            case "upright" -> 12;
            case "downleft" -> 16;
            case "downright" -> 14;
            default -> 0;
        };
    }
}

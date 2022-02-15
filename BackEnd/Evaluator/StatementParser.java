import java.util.LinkedList;
import java.util.Map;

interface StatementBox{
    /** Each Statement lead to termination some way or another.
     * Which, said termination could be an Action for GP.
     *
     * @return the 1st GPAction detected in the statement or null if none have detected.
     * */
    GPAction getGPAction(); //if return null == not terminable
}

abstract class BlockStatementAbs implements StatementBox{
    private LinkedList<StatementBox> statementLL;

    void setStatementLL(LinkedList<StatementBox> statementLL){
        this.statementLL = statementLL;
    }

    LinkedList<StatementBox> getStatementLL() {
        return statementLL;
    }
}

class BlockStatementImpl extends BlockStatementAbs{
    public BlockStatementImpl(LinkedList<StatementBox> statementLL){
        super.setStatementLL(statementLL);
    }

    @Override
    public GPAction getGPAction() {
        StatementBox statementHolder = null;
        for(StatementBox sb : getStatementLL())if(sb.getGPAction() != null){ statementHolder = sb; break;}
        return (statementHolder != null)? statementHolder.getGPAction(): null;
    }
}

abstract class IfStatementAbs implements StatementBox{
    protected ExprAbs theExpr;
    protected StatementBox thenStatement;
    protected StatementBox elseStatement;

    public IfStatementAbs(ExprAbs theExpr, StatementBox thenStatement, StatementBox elseStatement) {
        this.theExpr = theExpr;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
}

class IfStatementImpl extends IfStatementAbs {

    public IfStatementImpl(ExprAbs theExpr, StatementBox thenStatement, StatementBox elseStatement) {
        super(theExpr, thenStatement, elseStatement);
    }

    @Override
    public GPAction getGPAction() {
        if(super.theExpr.eval() > 0){
            return super.thenStatement.getGPAction();
        }else{
            return super.elseStatement.getGPAction();
        }
    }
}

abstract class WhileStatementAbs implements StatementBox{
    protected ExprAbs theExpr;
    protected StatementBox thenStatement;

    public WhileStatementAbs(ExprAbs theExpr, StatementBox thenStatement) {
        this.theExpr = theExpr;
        this.thenStatement = thenStatement;
    }
}

class WhileStatementImpl extends WhileStatementAbs{

    public WhileStatementImpl(ExprAbs theExpr, StatementBox thenStatement) {
        super(theExpr, thenStatement);
    }

    @Override
    public GPAction getGPAction() {
        int count = 0;
        while(super.theExpr.eval() > 0 && count < 1000){
            GPAction temp = this.thenStatement.getGPAction();
            if(temp != null)return temp;
            count++;
        }
        return null;
    }
}

record AssignmentCommand(String identifier, ExprAbs expr, Map<String, ExprAbs> binding) implements StatementBox {
    @Override
    public GPAction getGPAction() {
        binding.put(identifier, expr);
        return null;
    }
}

record MoveCommand(int eightDirecCoor) implements StatementBox {
    @Override
    public GPAction getGPAction() {
        return new MoveAct(eightDirecCoor);
    }
}

record ShootCommand(int eightDirecCoor) implements StatementBox {
    @Override
    public GPAction getGPAction() {
        return new ShootAct(eightDirecCoor);
    }
}

public class StatementParser {
    protected Tokenizer tk;
    protected final String[] reservedWord;
    protected final Map<String, ExprAbs> binding;
    protected ExpressionParser exprParser;

    public StatementParser(Tokenizer tk, String[] reservedWord, Map<String, ExprAbs> binding, ExpressionParser exprParser){
        this.tk = tk;
        this.reservedWord = reservedWord;
        this.binding = binding;
        this.exprParser = exprParser;
    }

    private int directionToEightDirec(String strIn) throws SyntaxError{
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

    private StatementBox parseC(boolean isReservedWord, String currToken) throws SyntaxError{
        if(!isReservedWord) {
            return new AssignmentCommand(currToken, exprParser.parseE(), binding);
        }else{
            String nextToken = tk.pop();
            if(currToken.equals("move")){
                return new MoveCommand(directionToEightDirec(nextToken));
            }else{
                return new ShootCommand(directionToEightDirec(nextToken));
            }
        }
    }

    protected StatementBox parseS() throws SyntaxError {
            String currToken = tk.pop();
            boolean isReservedWord = false;
            for(String str: reservedWord)
                if (currToken.equals(str)) {isReservedWord = true;break;}
            //Command section;
            if(!isReservedWord || currToken.equals("move") || currToken.equals("shoot" )){
                return parseC(isReservedWord, currToken);
            }else{
                switch (currToken) {
                    case "{":
                        LinkedList<StatementBox> inBracketExpr = new LinkedList<>();
                        while (!tk.peer().equals("}")) {
                            inBracketExpr.add(parseS());
                        }
                        tk.pop();
                        return new BlockStatementImpl(inBracketExpr);
                    case "if":
                        if (tk.pop().equals("(")) {
                            ExprAbs exprBox = exprParser.parseE();
                            if (!tk.pop().equals(")")) throw new SyntaxError("if statement syntax error");
                            if (!tk.pop().equals("then")) throw new SyntaxError("if statement syntax error");
                            StatementBox thenStatement = parseS();
                            if (!tk.pop().equals("else")) throw new SyntaxError("if statement syntax error");
                            StatementBox elseStatement = parseS();
                            return new IfStatementImpl(exprBox, thenStatement, elseStatement);
                        } else throw new SyntaxError("if statement syntax error");
                    case "while":
                        if (tk.pop().equals("(")) {
                            ExprAbs exprBox = exprParser.parseE();
                            if (!tk.pop().equals(")")) throw new SyntaxError("while statement syntax error");
                        }
                        break;
                }
            }
        throw new SyntaxError("statement syntax error");
    }
}

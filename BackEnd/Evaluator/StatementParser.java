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

class MoveCommand implements StatementBox{
    private final int eightDirecCoor;

    public MoveCommand(int eightDirecCoor){
        this.eightDirecCoor = eightDirecCoor;
    }

    @Override
    public GPAction getGPAction() {
        return new MoveAct(eightDirecCoor);
    }
}

class ShootCommand implements StatementBox{
    private final int eightDirecCoor;

    public ShootCommand(int eightDirecCoor){
        this.eightDirecCoor = eightDirecCoor;
    }

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

    StatementBox parse() throws SyntaxError {//Throw Syntax error
        while(tk.peer() != null){
            String currToken = tk.pop();
            boolean isReservedWord = false;
            for(String str: reservedWord)
                if (currToken.equals(str)) {isReservedWord = true;break;}
            //Command section;
            if(!isReservedWord) {
                binding.put(currToken, exprParser.parseE());
            }else if(currToken.equals("move") || currToken.equals("shoot")){
                String[] directionArr = {"left", "right", "up", "down", "upleft", "upright", "downleft", "downright"};
                String nextToken = tk.pop();
                boolean isDirection = false;
                for(String str: directionArr)if(nextToken.equals(str)){isDirection = true; break;}
                if (!isDirection) throw new SyntaxError("ActionCommand syntax error");
                int eightDirection = switch (nextToken) {
                    case "left" -> 17;
                    case "right" -> 13;
                    case "up" -> 11;
                    case "down" -> 15;
                    case "upleft" -> 18;
                    case "upright" -> 12;
                    case "downleft" -> 16;
                    case "downright" -> 14;
                    default -> 0;
                };
                if(currToken.equals("move")){
                    return new MoveCommand(eightDirection);
                }else{
                    return new ShootCommand(eightDirection);
                }
            }else{
                switch (currToken) {
                    case "{":
                        LinkedList<StatementBox> inBracketExpr = new LinkedList<>();
                        while (!tk.peer().equals("}")) {
                            inBracketExpr.add(parse());
                        }
                        tk.pop();
                        return new BlockStatementImpl(inBracketExpr);
                    case "if":
                        if (tk.pop().equals("(")) {
                            ExprAbs exprBox = exprParser.parseE();
                            if (!tk.pop().equals(")")) throw new SyntaxError("if statement syntax error");
                            if (!tk.pop().equals("then")) throw new SyntaxError("if statement syntax error");
                            StatementBox thenStatement = parse();
                            if (!tk.pop().equals("else")) throw new SyntaxError("if statement syntax error");
                            StatementBox elseStatement = parse();
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
        }
        throw new SyntaxError("statement syntax error");
    }
    //while (tk.hasNext)
    //command parse // if tk.peer is identifier then assignment else it's GPs
    //if tk.peer == { -> loop parse till detect } -> return BlockStatement
    //if tk.peer == if -> create ExpressionObj
    //if
}

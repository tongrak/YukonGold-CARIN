package yukongold.carin.gamestuff;

import java.util.LinkedList;
import java.util.Map;

interface StatementBox{
    /** Each Statement lead to termination some way or another.
     * Which, said termination could be an Action for GP.
     *
     * @return the 1st GPAction detected in the statement or null if none have detected.
     * */
    GPAction getGPAction(Coor theCoor) throws SyntaxError; //if return null == not terminable
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
    public GPAction getGPAction(Coor theCoor) throws SyntaxError {
        StatementBox statementHolder = null;
        for(StatementBox sb : getStatementLL())if(sb.getGPAction(theCoor) != null){ statementHolder = sb; break;}
        return (statementHolder != null)? statementHolder.getGPAction(theCoor): null;
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
    public GPAction getGPAction(Coor theCoor) throws SyntaxError {
        if(super.theExpr.eval(theCoor) > 0){
            return super.thenStatement.getGPAction(theCoor);
        }else{
            return super.elseStatement.getGPAction(theCoor);
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
    public GPAction getGPAction(Coor theCoor) throws SyntaxError {
        int count = 0;
        while(super.theExpr.eval(theCoor) > 0 && count < 1000){
            GPAction temp = this.thenStatement.getGPAction(theCoor);
            if(temp != null)return temp;
            count++;
        }
        return null;
    }
}

interface CommandBox extends StatementBox{ };

record AssignmentCommand(String identifier, ExprAbs expr, Map<String, ExprAbs> binding) implements CommandBox {
    @Override
    public GPAction getGPAction(Coor theCoor) {
        if(!identifier.equals("rand"))binding.put(identifier, expr);
        return null;
    }
}

record MoveCommand(int eightDirecCoor) implements CommandBox {
    @Override
    public GPAction getGPAction(Coor theCoor) {
        return new MoveAct(eightDirecCoor);
    }
}

record ShootCommand(int eightDirecCoor) implements CommandBox {
    @Override
    public GPAction getGPAction(Coor theCoor) {
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

    private boolean isCommandBox(String strIn){
        if (strIn.equals("move")||strIn.equals("shoot")) return true;
        for(String str: reservedWord) if (str.equals(strIn)) return false;
        return !strIn.equals("{");
    }

    private StatementBox parseC(String currToken) throws SyntaxError{
        if(currToken.equals("move")||currToken.equals("shoot")){
            String nextToken = tk.pop();
            if(currToken.equals("move")){
                return new MoveCommand(DecodedGene.directionToEightDirec(nextToken));
            }else{
                return new ShootCommand(DecodedGene.directionToEightDirec(nextToken));
            }
        }else if (tk.pop().equals("=")) return new AssignmentCommand(currToken, exprParser.parseE(), binding);

        throw new SyntaxError("Assignment statement syntax error");
    }


    protected StatementBox parseS() throws SyntaxError {
            String currToken = tk.pop();

            if(isCommandBox(currToken)){ //Command section;
                return parseC(currToken);
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
                            StatementBox theStatement = parseS();
                            return new WhileStatementImpl(exprBox, theStatement);
                        }else throw new SyntaxError("while statement syntax error");
                }
            }
        throw new SyntaxError("statement syntax error");
    }
}

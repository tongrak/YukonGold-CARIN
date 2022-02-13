import java.util.LinkedList;
import java.util.Map;

enum Direction{left, right, up, down, upleft, upright, downleft, downright}

interface StatementBox{
//    boolean isTerminable();
    GPAction getGPAction(); //if return null == not terminable
}

abstract class BlockStatementAbs implements StatementBox{
    private LinkedList<StatementBox> statementLL;
}
abstract class ifStatementAbs implements StatementBox{
    private ExprAbs theExpr;
    private StatementBox thenStatement;
    private StatementBox elseStatement;
}

abstract class StatementParser{
    abstract StatementBox parse(); //Throw Syntax error
        //while (tk.hasNext)
        //command parse // if tk.peer is identifier then assignment else it's GPs
        //if tk.peer == { -> loop parse till detect } -> return BlockStatement
        //if tk.peer == if -> create ExpressionObj
        //if 

}

public class ProgramParser {
    private Map<String, Integer> binding;
    private LinkedList<StatementBox> statementLL;

}

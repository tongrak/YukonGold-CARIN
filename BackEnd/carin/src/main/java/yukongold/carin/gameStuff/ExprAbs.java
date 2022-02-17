package yukongold.carin.gamestuff;

public abstract class ExprAbs implements ValuableBox {
    protected ExprAbs innerExpr;
    protected int operandIndex; //0-> none, 1-> +, 2-> -
    protected TermAbs theTerm;
}
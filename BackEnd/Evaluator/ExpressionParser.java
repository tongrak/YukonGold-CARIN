interface ValuableBox {
    int eval();
}

abstract class ExprAbs implements ValuableBox {
    protected ExprAbs innerExpr;
    protected int operandIndex; //0-> none, 1-> +, 2-> -
    protected TermAbs theTerm;
}

abstract class TermAbs implements ValuableBox {
    protected TermAbs innerTerm;
    protected int operandIndex; //0-> none, 1-> *, 2-> /, 3-> %;
    protected FactorAbs theFactor;
}

abstract class FactorAbs implements ValuableBox {
    protected boolean isExpo; //0 -> no, 1 -> yes
    protected FactorAbs innerFactor;
    protected PowerAbs thePower;
}

abstract class PowerAbs implements ValuableBox{
    protected boolean isBlanket;
    protected ExprAbs innerExpr;
    protected int theValue;
}

class EprBoxImpl extends ExprAbs{

    public EprBoxImpl(ExprAbs e, int operandIndex, TermAbs  t) {
        super.innerExpr = e;
        super.operandIndex = operandIndex;
        super.theTerm = t;
    }

    @Override
    public int eval() {
        if(super.innerExpr != null){
            if(super.operandIndex == 1){
                return super.innerExpr.eval() + super.theTerm.eval();   
            }
            else if(super.operandIndex == 2){
                return super.innerExpr.eval() - super.theTerm.eval();
            }
        }else{
            
        }
        throw new RuntimeException("Fail to evaluate a Expression");
    }
}

interface ExpressionParserInter{
    ExprAbs parseE();
    TermAbs parseT();
    FactorAbs parseF();
    PowerAbs parseP();
    int isNumber(String str);
}

public class ExpressionParser implements ExpressionParserInter {


    @Override
    public ExprAbs parseE() {
        return null;
    }

    @Override
    public TermAbs parseT() {
        return null;
    }

    @Override
    public FactorAbs parseF() {
        return null;
    }

    @Override
    public PowerAbs parseP() {
        return null;
    }

    @Override
    public int isNumber(String str) {
        return 0;
    }


    // need GPsStorage pointer for SensorExpression;
    // boolean isNumber(String str);
    // boolean isIdentifier(String str, Map<String, Integer> binding)
    //    ExprAbs parse()

}

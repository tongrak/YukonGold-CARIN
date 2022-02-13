interface ValuableBox {
    int eval();
}

abstract class ExprAbs implements ValuableBox {
    private ExprAbs innerExpr;
    private int operandIndex; //0-> none, 1-> +, 2-> -
    private TermAbs theTerm;
}

abstract class TermAbs implements ValuableBox {
    private TermAbs innerTerm;
    private int operandIndex; //0-> none, 1-> *, 2-> /, 3-> %;
    private FactorAbs theFactor;
}

abstract class FactorAbs implements ValuableBox {
    private boolean isExpo; //0 -> no, 1 -> yes
    private FactorAbs innerFactor;
    private PowerAbs thePower;
}

abstract class PowerAbs implements ValuableBox{
    private boolean isBlanket;
    private ExprAbs innerExpr;
    private int theValue;
}

public class ExpressionParser {
    // need GPsStorage pointer for SensorExpression;
    // boolean isNumber(String str);
    // boolean isIdentifier(String str, Map<String, Integer> binding)
    //    ExprAbs parse()

}

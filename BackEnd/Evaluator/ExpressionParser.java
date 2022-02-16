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
    protected boolean isNumber;
    protected ExprAbs innerExpr;
    protected int theValue;
    protected Map<String,ExprAbs> Binding;
    protected String identifier;
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
            }else{
                throw new SyntaxError();
            }
        }else{
            return super.theTerm.eval();
        }
    }
}

class TermAbsImpl extends TermAbs{

    public TermAbsImpl(TermAbs innerTerm,int operandIndex,FactorAbs theFactor){
        super.innerTerm = innerTerm;
        super.operandIndex = operandIndex;
        super.theFactor = theFactor;
    }

    @Override
    public int eval(){
        if(super.innerTerm != null){
            if(super.operandIndex == 1){
                return super.innerTerm.eval() * super.theFactor.eval();
            }else if(super.operandIndex == 2){
                return super.innerTerm.eval() / super.theFactor.eval();
            }else if(super.operandIndex == 3){
                return super.innerTerm.eval() % super.theFactor.eval();
            }else{
                throw new SyntaxError();
            }
        }else{
            return super.theFactor.eval();
        }
    }
}

class FactorAbsImpl extends FactorAbs{

    public FactorAbsImpl(boolean isExpo,FactorAbs innerFactor,PowerAbs thePower){
        super.isExpo = isExpo;
        super.innerFactor = innerFactor;
        super.thePower = thePower;
    }

    @Override
    public int eval(){
        if(isExpo){
            return Math.pow(super.thePower.eval(),super.innerFactor.eval());          
        }else{
            return super.thePower.eval();
        }
    }
}

class PowerAbsImpl extends PowerAbs{

    public PowerAbsImpl(boolean isBlanket,boolean isNumber,ExprAbs innerExpr,int theValue,Map<String,ExprAbs> Binding,String identifier){
        super.isBlanket = isBlanket;
        super.isNumber = isNumber;
        super.innerExpr = innerExpr;
        super.theValue = theValue;
        super.Binding = Binding;
        super.identifier = identifier; 
    }

    @Override
    public int eval(){
        if(isNumber){
            return super.theValue;
        }else if(super.Binding != null){
            return super.Binding.get(identifier).eval();
        }else if(isBlanket){
            return super.innerExpr.eval();
        }else if(false){ //SensorExpression
            return 0;
        }
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

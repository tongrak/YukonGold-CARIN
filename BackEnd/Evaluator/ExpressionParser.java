import java.util.Map;

interface ValuableBox {
    int eval() throws SyntaxError;
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
    protected int modeIndex; //0 -> none, 1 -> Number, 2 -> identifier, 3 -> Parentheses, 4 -> sensorEx;
//    protected boolean isBlanket;
//    protected boolean isNumber;
    protected Coor theCoor;
    protected ExprAbs innerExpr;
    protected Map<String,ExprAbs> Binding;
    protected String idOrActComm;
    protected int numValOr8Direc;

}

class EprBoxImpl extends ExprAbs{

    public EprBoxImpl(ExprAbs e, int operandIndex, TermAbs  t) {
        super.innerExpr = e;
        super.operandIndex = operandIndex;
        super.theTerm = t;
    }

    @Override
    public int eval() throws SyntaxError {
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

class TermAbsImpl extends TermAbs {

    public TermAbsImpl(TermAbs innerTerm,int operandIndex,FactorAbs theFactor){
        super.innerTerm = innerTerm;
        super.operandIndex = operandIndex;
        super.theFactor = theFactor;
    }

    @Override
    public int eval() throws SyntaxError{
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
    public int eval() throws SyntaxError{
        if(isExpo){
            return (int) Math.pow(super.thePower.eval(),super.innerFactor.eval());
        }else{
            return super.thePower.eval();
        }
    }
}

class PowerAbsImpl extends PowerAbs{

    public PowerAbsImpl(int modeIndex, Coor theCoor, ExprAbs innerExpr,int theValue,Map<String,ExprAbs> Binding,String identifier){
        super.modeIndex = modeIndex;
        super.innerExpr = innerExpr;
        super.numValOr8Direc = theValue;
        super.Binding = Binding;
        super.idOrActComm = identifier;
    }

    public PowerAbsImpl(int modeIndex, int value){
        this(modeIndex, null,null,value, null, null);
    }

    public PowerAbsImpl(int modeIndex, Map<String, ExprAbs> binding, String id){
        this(modeIndex, null, null, 0, binding, id);
    }

    public PowerAbsImpl(int modeIndex, ExprAbs expr){
        this(modeIndex, null, expr, 0, null,null);
    }

    public PowerAbsImpl(int modeIndex, Coor coor, String sensorEx , int EightDirec){
        this(modeIndex, coor, null, EightDirec, null, sensorEx);

    }

    @Override
    public int eval() throws SyntaxError{
        switch (this.modeIndex){
            case 1 -> {return  this.numValOr8Direc;}
            case 2 -> {return this.Binding.get(this.idOrActComm).eval();}
            case 3 -> {return this.innerExpr.eval();}
            case 4 -> {
                switch (this.idOrActComm) {
                    case "virus":
                        return GPsStorage.nearestVirus(this.theCoor);
                    case "antibody":
                        return GPsStorage.nearestAntiBody(this.theCoor);
                    case "nearby":
                        return GPsStorage.nearbyInDirec(this.theCoor, this.numValOr8Direc);
                }
            }
        }
        throw new SyntaxError("Power box syntax error");
    }
}

interface ExpressionParserInter{
    ExprAbs parseE();
    TermAbs parseT();
    FactorAbs parseF();
    PowerAbs parseP();
    boolean isNumber(String str);
    int tokenToNumber(String strInt);
}

public class ExpressionParser implements ExpressionParserInter {
    protected final Tokenizer tk;
    protected final Map<String, ExprAbs> binding;
    protected final Coor theCoor;

    public ExpressionParser(Tokenizer tk, Map<String, ExprAbs> binding, Coor theCoor) {
        this.binding = binding;
        this.theCoor = theCoor;
        this.tk = tk;
    }

    @Override
    public ExprAbs parseE() {
//        TermAbs innerE = parseT();
//        while(tk.peer().equals("+")||tk.peer().equals("-")){
//
//        }

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
    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }catch (Exception e) {return false;}
    }

    @Override
    public int tokenToNumber(String strInt) {
        if(isNumber(strInt)){
            return Integer.parseInt(strInt);
        }else throw new NumberFormatException();
    }


    // need GPsStorage pointer for SensorExpression;
    // boolean isNumber(String str);
    // boolean isIdentifier(String str, Map<String, Integer> binding)
    //    ExprAbs parse()

}

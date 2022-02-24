package yukongold.carin.gamestuff;

import java.util.Map;

interface ValuableBox {
    int eval(Coor theCoor) throws SyntaxError;
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

    public EprBoxImpl(TermAbs  t) {
        super.innerExpr = null;
        super.operandIndex = 0;
        super.theTerm = t;
    }

    @Override
    public int eval(Coor theCoor) throws SyntaxError {
        if(super.innerExpr != null){
            if(super.operandIndex == 1){
                return super.innerExpr.eval(theCoor) + super.theTerm.eval(theCoor);
            }
            else if(super.operandIndex == 2){
                return super.innerExpr.eval(theCoor) - super.theTerm.eval(theCoor);
            }else{
                throw new SyntaxError();
            }
        }else{
            return super.theTerm.eval(theCoor);
        }
    }
}



class TermAbsImpl extends TermAbs {

    public TermAbsImpl(TermAbs innerTerm,int operandIndex,FactorAbs theFactor){
        super.innerTerm = innerTerm;
        super.operandIndex = operandIndex;
        super.theFactor = theFactor;
    }

    public TermAbsImpl(FactorAbs theFactor){
        this(null,0,theFactor);
    }

    @Override
    public int eval(Coor theCoor) throws SyntaxError{
        if(super.innerTerm != null){
            if(super.operandIndex == 1){
                return super.innerTerm.eval(theCoor) * super.theFactor.eval(theCoor);
            }else if(super.operandIndex == 2){
                if(super.theFactor.eval(theCoor) == 0) throw new SyntaxError("can't divide by zero");
                return super.innerTerm.eval(theCoor) / super.theFactor.eval(theCoor);
            }else if(super.operandIndex == 3){
                if(super.theFactor.eval(theCoor) == 0) throw new SyntaxError("can't modelate by zero");
                return super.innerTerm.eval(theCoor) % super.theFactor.eval(theCoor);
            }else{
                throw new SyntaxError();
            }
        }else{
            return super.theFactor.eval(theCoor);
        }
    }
}

class FactorAbsImpl extends FactorAbs{

    public FactorAbsImpl(boolean isExpo,FactorAbs innerFactor,PowerAbs thePower){
        super.isExpo = isExpo;
        super.innerFactor = innerFactor;
        super.thePower = thePower;
    }

    public FactorAbsImpl(PowerAbs thePower){
        this(false, null, thePower);
    }

    @Override
    public int eval(Coor theCoor) throws SyntaxError{
        if(isExpo){
            return (int) Math.pow(super.thePower.eval(theCoor),super.innerFactor.eval(theCoor));
        }else{
            return super.thePower.eval(theCoor);
        }
    }
}

class PowerAbsImpl extends PowerAbs{

    public PowerAbsImpl(int modeIndex, ExprAbs innerExpr,int theValue,String identifier, Map<String, ExprAbs> binding){
        super.modeIndex = modeIndex;
        super.innerExpr = innerExpr;
        super.numValOr8Direc = theValue;
        super.idOrActComm = identifier;
        super.Binding = binding;
    }

    public PowerAbsImpl(int modeIndex, int value){
        this(modeIndex,null,value, null, null);
    }

    public PowerAbsImpl(int modeIndex, String id, Map<String, ExprAbs> binding){
        this(modeIndex, null, 0, id, binding);
    }

    public PowerAbsImpl(int modeIndex, ExprAbs expr){
        this(modeIndex, expr, 0,null,null);
    }

    public PowerAbsImpl(int modeIndex, String sensorEx , int EightDirec){
        this(modeIndex, null, EightDirec, sensorEx, null);

    }

    @Override
    public int eval(Coor theCoor) throws SyntaxError{
        switch (this.modeIndex){
            case 1 -> {return  this.numValOr8Direc;}
            case 2 -> {
                if(idOrActComm.equals("random")){return (int) (Math.random()*99);}
                if(!this.Binding.containsKey(this.idOrActComm))return 0;
                return this.Binding.get(this.idOrActComm).eval(theCoor);
            }
            case 3 -> {return this.innerExpr.eval(theCoor);}
            case 4 -> {
                switch (this.idOrActComm) {
                    case "virus":
                        return GPsStorage.nearestVirus(theCoor);
                    case "antibody":
                        return GPsStorage.nearestAntiBody(theCoor);
                    case "nearby":
                        return GPsStorage.nearbyInDirec(theCoor, this.numValOr8Direc);
                }
            }
        }
        throw new SyntaxError("Power box syntax error");
    }
}

public class ExpressionParser {
    protected final Tokenizer tk;
    protected final Map<String, ExprAbs> binding;

    public ExpressionParser(Tokenizer tk, Map<String, ExprAbs> binding) {
        this.binding = binding;
        this.tk = tk;
    }

    public ExprAbs parseE() throws SyntaxError {
       ExprAbs innerE = new EprBoxImpl(parseT());
       while(tk.peer().equals("+")||tk.peer().equals("-")){
           if(tk.peer().equals("+")){
               tk.pop();
               innerE = new EprBoxImpl(innerE,1,parseT());
           }else if(tk.peer().equals("-")){
                tk.pop();
                innerE = new EprBoxImpl(innerE,2,parseT());
           }else{
               throw new SyntaxError("Exception : Operation Error!");
           }

       }
       return innerE;
    }

    public TermAbs parseT() throws SyntaxError {
        TermAbs innerT = new TermAbsImpl(parseF());
        while(tk.peer().equals("*")||tk.peer().equals("/")||tk.peer().equals("%")){
            if(tk.peer().equals("*")){
                tk.pop();
                innerT = new TermAbsImpl(innerT,1,parseF());
            }else if(tk.peer().equals("/")){
                tk.pop();
                innerT = new TermAbsImpl(innerT,2,parseF());
            }else if(tk.peer().equals("%")){
                tk.pop();
                innerT = new TermAbsImpl(innerT,3,parseF());
            }else{
                throw new SyntaxError("Exception : Operation Error!");
            }
        }
        return innerT;
    }

    public FactorAbs parseF() throws SyntaxError {
        PowerAbs innerF = parseP();
        FactorAbs theFactor = new FactorAbsImpl(innerF);
        if(tk.peer().equals("^")){
            tk.pop();
            theFactor = new FactorAbsImpl(true, parseF(), innerF);
        }
        return theFactor;
    }

    public PowerAbs parseP() throws SyntaxError {
         String current = tk.pop();
         if(isNumber(current)){
             return new PowerAbsImpl(1,Integer.parseInt(current));
         }else if(current.equals("virus")||current.equals("antibody")||current.equals("nearby")){
             if(current.equals("nearby")) return new PowerAbsImpl(4, current, DecodedGene.directionToEightDirec(tk.pop()));
             return new PowerAbsImpl(4, current, 0);
         }else if(current.equals("(")){
             ExprAbs theExpr = parseE();
             if(!tk.pop().equals(")")) throw new SyntaxError("ExpressionParser: Parse P: Parentheses syntax error");
             return new PowerAbsImpl(3, theExpr);
         }else {return new PowerAbsImpl(2, current, this.binding);}

//         throw new SyntaxError("ExpressionParser: Parse P syntax error");
    }

    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }catch (Exception e) {return false;}
    }

}

package yukongold.carin.gamestuff;


public class SyntaxError extends Exception{
    public SyntaxError(){
        super("SyntaxError:");
    }

    public SyntaxError(String message){
        super(message);
    }
}

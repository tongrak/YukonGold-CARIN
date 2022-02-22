package yukongold.carin.visualfrontend;

public class VisualFrontEnd {
    private static VisualFrontEnd instance;
    private VisualFrontEnd(){}
    public static VisualFrontEnd getInstance(){
        if(instance == null){instance = new VisualFrontEnd();}
        return instance;
    }

    public void innit(){
        
    }


}

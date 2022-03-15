package yukongold.carin.requesthandler;

import yukongold.carin.gamestuff.Coor;

public class ModelSendAB {
    
    private int type;

    private Coor coor;

    public ModelSendAB(int type,Coor coor){
        this.type = type;
        this.coor = coor;
    }

    public int getType(){
        return this.type;
    }

    public Coor getCoor(){
        return this.coor;
    }
}

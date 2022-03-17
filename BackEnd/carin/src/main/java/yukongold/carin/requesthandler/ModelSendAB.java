package yukongold.carin.requesthandler;

import yukongold.carin.gamestuff.Coor;

public class ModelSendAB {
    
    private int type;

    private Coor coor;

    private boolean spawn;

    private int credit;

    public ModelSendAB(int type,Coor coor,boolean spawn,int credit){
        this.type = type;
        this.coor = coor;
        this.spawn = spawn;
        this.credit = credit;
    }

    public int getType(){
        return this.type;
    }

    public Coor getCoor(){
        return this.coor;
    }

    public void setSpawn(Boolean spawn){
        this.spawn = spawn;
    }

    public boolean getSpawn(){
        return this.spawn;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    public int getCredit(){
        return this.credit;
    }
}

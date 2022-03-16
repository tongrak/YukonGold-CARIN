package yukongold.carin.requesthandler;

public class ModelRequestAB {
    
    private int type;

    private boolean spawn;

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return this.type;
    }

    public void setSpawn(Boolean spawn){
        this.spawn = spawn;
    }

    public boolean getSpawn(){
        return this.spawn;
    }

}

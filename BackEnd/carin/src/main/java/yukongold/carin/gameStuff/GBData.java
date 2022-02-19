package yukongold.carin.gamestuff;

import java.util.Map;

public class GBData {
    private boolean clickPause;
    private boolean clickSpeed;
    private int currSpeed;
    private PlayerAction currRequest;
    private static Map<Coor, GamePiece> GPCoorMap;;
    private static GBData instance;
    private GBData(){}

    public static GBData getInstance(){
        if(instance == null){
            instance = new GBData();
            GPsStorage storage = GPsStorage.getInstance();
            GPCoorMap = storage.getMap();
        }
        return instance;
    }

    public PlayerAction getCurrRequest(){
        PlayerAction holder = null;
        if(currRequest != null){ holder = currRequest; currRequest = null;}
        return holder;
    }

    public void setClickPause(){
        this.clickPause = true;
    }

    public boolean isPlClickPause(){
        if(clickPause){
            this.clickPause = false;
            return true;
        }else return false;
    }

    public void setClickSpeed(){
        this.clickSpeed = true;
    }

    public boolean isPlClickSpeed(){
        if(clickSpeed){
            this.clickSpeed = false;
            return true;
        }else return false;
    }

    public Map<Coor, GamePiece> getMappingData(){
        return GPCoorMap;
    }

    public void setPlayerAction(PlayerAction PlAct){
        if(currRequest == null){currRequest = PlAct;}
    }



    


}

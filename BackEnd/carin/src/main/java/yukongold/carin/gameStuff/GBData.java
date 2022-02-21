package yukongold.carin.gamestuff;

import java.util.Map;

public class GBData {
    private boolean clickPause;
    private boolean clickSpeed;
    private int currSpeed;
    private PlayerAction currRequest;
    private static Map<Coor, GamePiece> GPCoorMap;
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

    /** Design for GameBoard(Mainloop) to access current player request
     * 
     */
    public PlayerAction getCurrRequest(){
        PlayerAction holder = null;
        if(currRequest != null){ holder = currRequest; currRequest = null;}
        return holder;
    }

    /** Design for GameMediator to pass on player requested for pause
     * 
     */
    public void setClickPause(){
        this.clickPause = true;
    }

    /**Design for GameBoard(Mainloop) to check if player request for pause or not
     * 
     * @return true if player request for pause. else false.
     */
    public boolean isPlClickPause(){
        if(clickPause){
            this.clickPause = false;
            return true;
        }else return false;
    }

    /**Design for GameMediator to pass player request on game's speed
     * 
     */
    public void setClickSpeed(){
        this.clickSpeed = true;
    }

    /**Design for GameBoard(MainLoop) to check if player request change gamespeed or not
     * 
     * @return true if player requested for game speed change 
     */
    public boolean isPlClickSpeed(){
        if(clickSpeed){
            this.clickSpeed = false;
            return true;
        }else return false;
    }


    /** getting Map object of current game data
     * 
     * @return a Map object represent a game data.
     */
    public Map<Coor, GamePiece> getMappingData(){
        return GPCoorMap;
    }

    /**Design for GameMediator to pass on player requested action(spawn / relocate)
     * 
     * @param PlAct - PlayerAction which had been analize of GameMediator
     */
    public void setPlayerAction(PlayerAction PlAct){
        if(currRequest == null){currRequest = PlAct;}
    }
}
package yukongold.carin.gamestuff;

public class GameBoard{
    private static GameBoard instance;
    private static GPsFactory GPsFac;
    private static GPsPlayer GPsPlay;
    private static GPsStorage GPsStore;
    private GBData theData;
    private GameBoard(){}

    public static GameBoard getInstance(){
        if(instance == null){
            instance = new GameBoard();
            GPsFac = GPsFactory.getInstance();
            GPsPlay = GPsPlayer.getInstance();
            GPsStore = GPsStorage.getInstance();
        }
        return instance;
    }


    public void startGame(){
        boolean metWinningCond = false;
        while(!metWinningCond){
            checkPlayerRequest();
            GPsPlay.startGPsTurn();
            metWinningCond = GPsStore.checkWinningCond();
        }
    }

    private void checkPlayerRequest(){
        PlayerAction holder = theData.getCurrRequest();
        if(holder !=  null){
            switch (holder.getClass().getName()) {
                case "SpawnAct"->{
                    SpawnAct spawnHolder = (SpawnAct) holder;
                    Antibody newAB = GPsFac.createNewAB(spawnHolder.getSelectedABType(), 100);
                    // TODO get config data
                    boolean check = GPsStore.setGPintoStorage(newAB, spawnHolder.getSelectedCoor());
                    if(!check) throw new RuntimeException("SpawnAct: can't spawn "); else GPsPlay.addGP(newAB);
                }
                case "RelocateAct"->{
                    RelocateAct relocateHolder = (RelocateAct) holder;
                    boolean check = GPsStore.relocateAB(relocateHolder.getSelectedCoor(), relocateHolder.getDestination());
                    if(!check) throw new RuntimeException("RelocateAct: can't relocated");
                }
            }
        }
    }

    public GBData init() {
        // TODO create the starting pharase of the game
        return theData;
        
    }

    public void pauseGame() {
        // TODO someHow pause the main while loop
        
    }

    public void speedChange() {
        // TODO someHow speed up/down the main loop
        
    }

    public GBData updateData() {
        // TODO sending essential data to resident GBData
        return null;
    }
    
}
 
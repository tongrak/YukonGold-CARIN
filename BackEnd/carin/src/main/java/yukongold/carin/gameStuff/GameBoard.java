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
                    Antibody newAB = GPsFac.createNewAB(spawnHolder.getSelectedABType());
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
        // TODO Auto-generated method stub
        return theData;
        
    }

    public void pauseGame() {
        // TODO Auto-generated method stub
        
    }

    public void speedChange() {
        // TODO Auto-generated method stub
        
    }

    public GBData updateData() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
 
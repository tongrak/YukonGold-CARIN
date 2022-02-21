package yukongold.carin.gamestuff;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//TODO Testing
//TODO creat a visual class to run the game on terminal


public class GameBoard{

    class MainLoop implements Runnable{

        @Override
        public void run() {
            mainLoop();
        }
    
    }
    private int speedIndex = 0;
    private Long[] speedRate = {1L, 2L, 3L};
    private boolean isPause = false;
    private static GameBoard instance;
    private GPsFactory GPsFac;
    private GPsPlayer GPsPlay;
    private GPsStorage GPsStore;
    private GBData theData;
    private GameBoard(){}

    public static GameBoard getInstance(){
        if(instance == null){
            instance = new GameBoard();
        }
        return instance;
    }

    public void start(){
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        final MainLoop ml = new MainLoop();
        executorService.scheduleAtFixedRate(ml, 0, speedRate[speedIndex], TimeUnit.SECONDS);
    }

    private void mainLoop(){
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

    public void init() {
        GPsFac = GPsFactory.getInstance();
        GPsPlay = GPsPlayer.getInstance();
        GPsStore = GPsStorage.getInstance();
        theData = GBData.getInstance();
        
    }

    public void pauseGame() {
        this.isPause = !this.isPause;
    }

    public void speedChange() {
        this.speedIndex++;
        if(speedIndex>3) this.speedIndex = 0;
    }

    // public GBData updateData() {
    // }
    
}
 
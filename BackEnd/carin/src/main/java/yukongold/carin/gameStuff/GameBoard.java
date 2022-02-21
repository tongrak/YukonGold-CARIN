package yukongold.carin.gamestuff;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//TODO Testing

public class GameBoard {

    // class MainLoop extends Thread{
    // @Override
    // public void run() {
    // mainLoop();
    // }
    // }
    private int speedIndex = 0;
    private int turnCounter = 0;
    private boolean metWinningCond = false;
    private int[] speedRate = { 1, 2, 3 };
    private boolean isPause = false;
    private static GameBoard instance;
    private static GPsFactory GPsFac;
    private static GPsPlayer GPsPlay;
    private static GPsStorage GPsStore;
    private static GBData theData;

    private GameBoard() {
    }

    public static GameBoard getInstance() {
        if (instance == null) {
            GPsFac = GPsFactory.getInstance();
            GPsPlay = GPsPlayer.getInstance();
            GPsStore = GPsStorage.getInstance();
            theData = GBData.getInstance();
            instance = new GameBoard();
        }
        return instance;
    }

    public void start() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // final MainLoop ml = new MainLoop();
        // ml.setDaemon(true);
        // executorService.scheduleAtFixedRate(ml, 0, speedRate[speedIndex],
        // TimeUnit.SECONDS);
        
        while (true) {
            mainLoop();
            try {
                Thread.sleep(speedRate[speedIndex]*1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void mainLoop() {
        boolean metWinningCond = false;
        if (!metWinningCond) {
            checkPlayerRequest();
            if (!isPause)
                GPsPlay.startGPsTurn();
            metWinningCond = GPsStore.checkWinningCond();
            System.out.println("current win con: " + metWinningCond);
            System.out.println("GB: is " + ((isPause) ? "pause" : "running") + " at turn:"
                    + ((isPause) ? turnCounter : turnCounter++));
        }
    }

    private void checkPlayerRequest() {
        PlayerAction holder = theData.getCurrRequest();
        if (holder != null) {
            if(holder.getClass().equals(SpawnAct.class)){
                SpawnAct spawnHolder = (SpawnAct) holder;
                Antibody newAB = GPsFac.createNewAB(spawnHolder.getSelectedABType());
                // TODO get config data
                boolean check = GPsStore.setGPintoStorage(newAB, spawnHolder.getSelectedCoor());
                if (!check)
                    throw new RuntimeException("SpawnAct: can't spawn ");
                else
                    GPsPlay.addGP(newAB);

            }else{
                RelocateAct relocateHolder = (RelocateAct) holder;
                    boolean check = GPsStore.relocateAB(relocateHolder.getSelectedCoor(),
                            relocateHolder.getDestination());
                    if (!check)
                        throw new RuntimeException("RelocateAct: can't relocated");
            }
        }
    }

    public void pauseGame() {
        this.isPause = !this.isPause;
    }

    public void speedChange() {
        this.speedIndex++;
        if (speedIndex > 3)
            this.speedIndex = 0;
    }

}

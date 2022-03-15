package yukongold.carin.gamestuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Controller;


@Controller
public class GameBoard implements Runnable {
    private Path VGene = Path.of("src/test/java/yukongold/carin/sampleGeneCode/sampleGeneCodeVirus.txt");
    private Path[] abArr = {
        Path.of("src/test/java/yukongold/carin/sampleGeneCode/sampleGeneCode.txt")
    };

    private int m;
    private int n;
    private float vSpawnposs;
    private int currCredit;
    private int spawnCost;
    private int relocateCost;
    private int virusHP;
    private int abHP;
    private int vDamage; 
    private int vGain; 
    private int abDamage; 
    private int abGain;
    private int plGain;

    private int turnCounter = 0;
    private boolean metWinningCond = false;
    private int speedIndex = 0;
    private int[] speedRate = {2, 1, 3};
    private boolean isPause = true;

    private static GameBoard instance;
    private static GPsFactory GPsFac;
    private static GPsPlayer GPsPlay;
    private static GPsStorage GPsStore;
    private static GPManager GPmanag;
    private static GBData theData;

    private GameBoard() {}

    public static GameBoard getInstance() {
        if (instance == null) {
            GPsFac = GPsFactory.getInstance();
            GPsPlay = GPsPlayer.getInstance();
            GPsStore = GPsStorage.getInstance();
            theData = GBData.getInstance();
            GPmanag = GPManager.getInstance(); 
            instance = new GameBoard();
        }
        return instance;
    }

    /** A method Overiding run() in Threads. Created so GameBoard object can be use as a Thread.
     * 
     */
    @Override
    public void run() {
        gameInit();
        while(!metWinningCond){
            try {
                mainLoop();
                Thread.sleep(speedRate[speedIndex]*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RuntimeException re){
                System.err.println(re);
            }
        }
        System.out.println("Game End");
    }

    /**A method representing mainloop of the game. Which mainly spawnVirus, let the all GP start its turn, update data for front-end, and check current game winning condition
     * 
     * @throws RuntimeException
     */
    private void mainLoop() throws RuntimeException {
        System.out.println("current win con: " + metWinningCond);
        System.out.println("GB: is " + ((isPause) ? "pause" : "running") + " at turn:"+ ((isPause) ? turnCounter : turnCounter++));
            checkPlayerRequest();
            if (!isPause){
                spawnVirus();
                GPsPlay.startGPsTurn();
                updatePlCredit();
                metWinningCond = GPsStore.checkWinningCond();
            }
            updateGBData();
    }

    /**Check player request from GameBoard data centre.
     * 
     */
    private void checkPlayerRequest(){
        PlayerAction[] holderArr = theData.getCurrRequest();
        if(theData.isPlClickPause())pauseGame();
        if(theData.isPlClickSpeed())speedChange();
        if (holderArr != null) {
            for (PlayerAction pA : holderArr) {
                if(pA.getClass().equals(SpawnAct.class)){
                    SpawnAct spawnHolder = (SpawnAct) pA;
                    if(this.currCredit - this.spawnCost > 0){
                        Antibody newAB = GPsFac.createNewAB(abArr[spawnHolder.getTypeIndex()-1]);
                        boolean check = GPsStore.setGPintoStorage(newAB, spawnHolder.getSelectedCoor());
                        if (!check)
                            System.out.println("GB.checkPlRequest: can't spawn AB at " + spawnHolder.getSelectedCoor().toString());
                        else{
                            GPsPlay.addGP(newAB);
                            this.currCredit -= this.spawnCost;
                        }
                    }else System.out.println("GB.checkPlRequest: not enough credit to spawn at " + spawnHolder.getSelectedCoor().toString());
                    
    
                }else{
                    RelocateAct relocateHolder = (RelocateAct) pA;
                    boolean check = GPmanag.relocateAB(
                        relocateHolder.getSelectedCoor(),
                        relocateHolder.getDestination()
                    );
                    if(!check) System.out.println("GB.checkPlRequest: can't relocated AB at " + relocateHolder.getSelectedCoor().toString());
                }
                
            }
        }
    }

    /**spawning one virus in to random coor according to defined spawning possibility.
     * 
     */
    public void spawnVirus(){
        if(((int)(Math.random() * 2))<this.vSpawnposs){
            Virus newV = GPsFac.createNewVirus(VGene);
            GPsStore.setGPintoStorage(newV, 
            new Coor((int)(Math.random() * (this.m)), (int)(Math.random() * (this.n))));
            GPsPlay.addGP(newV); 
        }
    }

    /**pause or unpause the game.
     * 
     */
    public void pauseGame() {
        this.isPause = !this.isPause;
    }

    /**Design for GBData class to get the current status of the game
     * 
     * @return true if the game is pause currently
     */
    public boolean getIsPause(){
        return this.isPause;
    }

    /**Change current game's speed into next pre-defined speed.
     * 
     */
    public void speedChange() {
        this.speedIndex++;
        if (speedIndex > 3)
            this.speedIndex = 0;
    }

    /**reading config file and setting essensials variable
     * 
     */
    private void setConfig(){
        Path configFilePath = Path.of("src/main/java/yukongold/carin/configfile/config.txt");
        Charset charset = StandardCharsets.US_ASCII;

        try(BufferedReader reader = 
            Files.newBufferedReader(configFilePath, charset)){
                String line;
                for (int i = 1; i <= 8; i++) {
                    if((line = reader.readLine()) != null){
                        int fst = 0;
                        int snd = 0;
                        if((i>2 && i<7) || i == 1){
                            String[] Splittedline = line.split(" ");
                            fst = Integer.parseInt(Splittedline[0]);
                            snd = Integer.parseInt(Splittedline[1]);
                        }
                        switch (i) {
                            case 1->{this.m = fst; this.n = snd;}
                            case 2->{this.vSpawnposs = Float.parseFloat(line);}
                            case 3->{this.currCredit = fst; this.spawnCost = snd;}
                            case 4->{this.virusHP = fst; this.abHP = snd;}
                            case 5->{this.vDamage = fst; this.vGain = snd;}
                            case 6->{this.abDamage = fst; this.abGain = snd;}
                            case 7->{this.relocateCost = Integer.parseInt(line);}
                            case 8->{this.plGain = Integer.parseInt(line);}
                        }

                    }else throw new Exception("GB.setConfig(): invalid config file");
                }
        }catch(IOException e){
            System.err.format("IOException: %s%n",e);
        }catch(Exception exc){
            System.err.format("Exception: %s%n",exc);
        }
    }

    /**updating current player credit according to virus dead count 
     * 
     */
    private void updatePlCredit(){
        this.currCredit += (theData.getVDeadCount()*this.plGain);
    }

    /**undating current game data to front-end
     * 
     */
    private void updateGBData(){
        theData.setCurrCredit(this.currCredit);
        theData.setCurrSpeed(speedRate[speedIndex]);
        theData.setPauseStatus(isPause);
    }

    /**initialize the game by getting config variables and setting it accross the game's class
     * 
     */
    private void gameInit(){
        setConfig();
        GPsStore.setMN(this.m, this.n);
        GPsFac.setGPsHP(this.virusHP, this.abHP);
        GPmanag.setGPsDamage(this.vDamage, this.abDamage);
        GPmanag.setGPsGain(this.vGain, this.abGain);
        GPmanag.setRelocateCost(this.relocateCost);
    }
}

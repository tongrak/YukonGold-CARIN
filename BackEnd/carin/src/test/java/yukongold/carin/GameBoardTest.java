package yukongold.carin;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.Coor;
import yukongold.carin.gamestuff.GBData;
import yukongold.carin.gamestuff.GPsFactory;
import yukongold.carin.gamestuff.GPsPlayer;
import yukongold.carin.gamestuff.GPsStorage;
import yukongold.carin.gamestuff.GameBoard;
import yukongold.carin.gamestuff.SpawnAct;

public class GameBoardTest {
    static GameBoard gb;
    static GBData gbData;
    static GPsStorage GPStore;
    static GPsFactory gpF;
    static GPsPlayer gpP;
    
    @BeforeAll
    static void setUp(){
        gb = GameBoard.getInstance();
        gbData = GBData.getInstance();
        GPStore = GPsStorage.getInstance();
        gpF = GPsFactory.getInstance();
        gpP = GPsPlayer.getInstance();
    }

    @Test
    void innit1stPhases(){
        Thread t0 = new Thread(gb);
        // SpawnAct newSpawnAct1 = new SpawnAct(new Coor(0,1), 1);
        // SpawnAct newSpawnAct2 = new SpawnAct(new Coor(0,0), 1);
        // gbData.addPlayerAction(newSpawnAct1);
        // gbData.addPlayerAction(newSpawnAct2);
        // t0.start();
        gbData.setClickPause();
        gb.run();
        // t0.setDaemon(true);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        
    }


}

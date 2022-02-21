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
import yukongold.carin.gamestuff.GamePiece;
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
        Path pAB = Path.of("src/test/java/yukongold/carin/Test/sampleGeneCode.txt");
        Path pV = Path.of("src/test/java/yukongold/carin/Test/sampleGeneCodeVirus.txt");
        GamePiece virus1 = gpF.createNewVirus(pV);
        gpP.addGP(virus1);
        GPStore.setGPintoStorage(virus1, new Coor(2,2));
        gbData.setPlayerAction(new SpawnAct(new Coor(10,10), pAB));
        gb.start();
        
    }


}

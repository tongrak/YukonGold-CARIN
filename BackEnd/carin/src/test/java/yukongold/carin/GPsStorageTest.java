package yukongold.carin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.Coor;
import yukongold.carin.gamestuff.GPsFactory;
import yukongold.carin.gamestuff.GPsStorage;

public class GPsStorageTest {
    static GPsStorage gpStore;
    static GPsFactory gpFac;

    @BeforeAll
    static void setUp(){
        Path fakePath = Path.of("/Help_Us_All");
        gpStore = GPsStorage.getInstance();
        gpFac = GPsFactory.getInstance();
        gpStore.setGPintoStorage(gpFac.createNewAB(fakePath), new Coor(10, 25)); //AB (10,25)
        gpStore.setGPintoStorage(gpFac.createNewVirus(fakePath), new Coor(5, 20)); //V (5,20)
        gpStore.setGPintoStorage(gpFac.createNewAB(fakePath), new Coor(6, 21)); //AB (6,21)
        gpStore.setGPintoStorage(gpFac.createNewVirus(fakePath), new Coor(7, 22)); //V (7,22)
    }

    @Test
    void winConCheck(){
        assertFalse(gpStore.checkWinningCond());
    }

    @Test
    void checkNearByMethod(){
        assertEquals(5, GPsStorage.nearbyInDirec(new Coor(5, 25), 13));
        assertEquals(1, GPsStorage.nearbyInDirec(new Coor(6, 21), 12));
        assertEquals(1, GPsStorage.nearbyInDirec(new Coor(6, 21), 16));
    }

    @Test
    void checkVirusMethod(){
        assertEquals(12, GPsStorage.nearestVirus(new Coor(6, 21)));
        assertEquals(36, GPsStorage.nearestVirus(new Coor(10, 25)));
    }
    
}

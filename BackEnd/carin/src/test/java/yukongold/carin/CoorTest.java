package yukongold.carin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.Coor;

public class CoorTest {

    @Test
    void theTest(){
        Coor c1 = new Coor(0,0);
        Coor c2 = new Coor(0,0);
        assertEquals(c1, c2);
    }
    
}

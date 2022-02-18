package yukongold.carin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import yukongold.carin.gamestuff.Coor;
import yukongold.carin.gamestuff.GPAction;
import yukongold.carin.gamestuff.GeneEvaluator;

public class GeneEvaluatorTest {
    // static Virus
    static GeneEvaluator evaluator;

    @BeforeAll
    static void setup(){
        evaluator = GeneEvaluator.getInstance();
    }

    @Test
    void moveCommtest(){
        Path moveCommPath = Path.of("src/test/java/yukongold/carin/Test/moveComm.txt");
        Path shootCommPath = Path.of("src/test/java/yukongold/carin/Test/shootComm.txt");

        GPAction expec01 = evaluator.getGPAction(moveCommPath, new Coor(0,0));
        GPAction expec02 = evaluator.getGPAction(shootCommPath, new Coor(0,0));

        

        System.out.println("Help us all");

        // assertEquals(expec01, evaluator.getGPAction(moveCommPath, new Coor(0,0)));
    }
    
}

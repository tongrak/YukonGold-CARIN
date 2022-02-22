package yukongold.carin.requesthandler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yukongold.carin.gamestuff.GBData;
import yukongold.carin.gamestuff.GameBoard;
import yukongold.carin.gamestuff.Coor;
import yukongold.carin.gamestuff.GamePiece;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**As the name might suggest, the "class" handle when player request game to 
 * start or manipulate game's speed (pause, slow, fast)
 */
interface RequestHandler{

    /**a order to initiate the game and let player in spawning phases
     * 
     * @return reply confirm the order
     */
    //// RequestHandler start();

    /**a order to kick start the game properly.
     * 
     * @return reply confirm the order
     */
    RequestHandler startGame();

    /**a order to pass game's data
     * 
     * @return json file represent all GP and its coor, plus current game speed.
     */
    RequestHandler update();

    /**a order to change game's speed
     * 
     * @return reply confirm the order
     */
    RequestHandler speedAct();

    /**a order to pause the game;
     * 
     * @return reply confirm the order
     */
    RequestHandler pauseAct();

    /**a order to change antibody coor
     * 
     * @return reply confirm the order
     */
    // RequestHandler relocateAct();

    /**a order to create a new antibody at coor passed
     * 
     * @return reply confirm the order
     */
    // RequestHandler spawnAct();

    /**a order to selecte a certain ClickSensitiveComponent
     * 
     * @return reply confirm the order
     */
    RequestHandler clickAct();
}

abstract class RequestHandlerAbs implements RequestHandler{
    // GameBoards instance

} 

@RestController
public class GameMediator {

    private GBData gbdata = GBData.getInstance();
    private Map<Coor, GamePiece> MapData = gbdata.getMappingData();

    @GetMapping("/start")
    public String startGame(){
        GameBoard gb = GameBoard.getInstance();
        Thread t0 = new Thread(gb);
        t0.start();
        return "Starting GameBoards";
    }

    @GetMapping("/test")
    public Map<String,GamePiece> test(){
        Map<String,GamePiece> toReturn = new HashMap<>();
        String[] k = {};
        GamePiece[] v = {};
        int count = 0;
        for (Coor key : MapData.keySet()) {
            k[count] = "(" + Integer.toString(key.getX()) + "," +
            Integer.toString(key.getY()) + ")";
            count++;
        }
        count = 0;
        for (GamePiece value : MapData.values()) {
            v[count] = value;
            count++;
        }
        count = 0;
        for(int i = 0;i < k.length;i++ ){
            toReturn.put(k[i],v[i]);
        }
        return toReturn;
    }

    // @PostMapping("/start")
    // public Map<String,String> start(@RequestBody String start) throws IOException{
    //     if(start == "true"){
    //         Map<Coor, GamePiece> mapData = gbdata.getMappingData();
    //         Map<String,String> toReturn;

    //         return null;
    //     }else{
    //         throw new IOException();
    //     }
    // }

    @PostMapping("/click")
    public String click(@RequestBody String request){
        return "Received : " + request;
    }
}
package yukongold.carin.requesthandler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yukongold.carin.gamestuff.GBData;
import yukongold.carin.gamestuff.GameBoard;
import yukongold.carin.gamestuff.Coor;
import yukongold.carin.gamestuff.GamePiece;

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

    private int selectedABtypeIndex = 0; //0-> non-selected 1->3 is selected
    private int click_count = 1;
    private String[] clickCoor1;
    private String[] clickCoor2;
    private Coor coor1;
    private Coor coor2;

    @GetMapping("/start")
    public String startGame(){
        GameBoard gb = GameBoard.getInstance();
        Thread t0 = new Thread(gb);
        t0.start();
        return "Starting GameBoards";
    }

    @GetMapping("/getdata")
    public GameData sendinData(){
        throw new RuntimeException("unimplement");
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

    @PostMapping("/pause")
    public void pause(){
        gbdata.setClickPause();
    }

    @PostMapping("/speed")
    public void speed(){
        gbdata.setClickSpeed();
    }

    @PostMapping("/click")
    public String click(@RequestBody ModelRequest request){
        if(click_count == 1){
            clickCoor1 = request.getCoor().split(",");
            coor1 = new Coor(Integer.parseInt(clickCoor1[0]),
            Integer.parseInt(clickCoor1[1]));
            click_count++;
        }else if(click_count == 2){
            clickCoor2 = request.getCoor().split(",");
            coor2 = new Coor(Integer.parseInt(clickCoor2[0]),
            Integer.parseInt(clickCoor2[1]));
            click_count = 1;
            gbdata.checkInput2Coor(coor1, coor2);
        }else{
            click_count = 1;
        }
        
        return "Click : " + click_count;
    }

    // private void checkClick(Coor coor){
    //     if(this.coor1 == null && this.coor2 == null){
            
    //     }
    // }
}
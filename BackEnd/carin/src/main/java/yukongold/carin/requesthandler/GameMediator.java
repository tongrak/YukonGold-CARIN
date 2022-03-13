package yukongold.carin.requesthandler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yukongold.carin.gamestuff.GBData;
import yukongold.carin.gamestuff.GameBoard;
import yukongold.carin.gamestuff.Coor;
import yukongold.carin.gamestuff.GamePiece;
import yukongold.carin.gamestuff.SpawnAct;
import yukongold.carin.gamestuff.Virus;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.print.DocFlavor.STRING;

/**As the name might suggest, the "class" handle when player request game to 
 * start or manipulate game's speed (pause, slow, fast)
 */
interface RequestHandler{

    /**a order to initiate the game and let player in spawning phases
     * 
     * @return reply confirm the order
     */
    RequestHandler start();

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
    private GameBoard mainGame = GameBoard.getInstance();

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

    @GetMapping("/test/spawn")
    public String spawningSampleGPS(){
        SpawnAct newSpawnAct = new SpawnAct(new Coor(13,13), 1);
        gbdata.setPlayerAction(newSpawnAct);
        return "Spawning: AB at 13,13";
    }

    @GetMapping("/getdata")
    public GameData sendingData(){
        String s;
        LinkedList<Pair<Pair<Integer, Integer>, String>> datall = new LinkedList<>();
        for(Coor key : MapData.keySet()){
            Pair<Integer,Integer> c = new Pair<>(key.getX(),key.getY());
            if(MapData.get(key).getClass().equals(Virus.class)){
                s = "virus" ;
            }else{
                s = "antibody";
            }
            Pair<Pair<Integer,Integer>,String> toPush = new Pair<>(c,s);
            datall.push(toPush);
        }
        return new GameData(mainGame.getIsPause(), gbdata.getCurrspeed(),datall,gbdata.getWinningStatus());
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

    @GetMapping("/pause")
    public boolean pause(){
        gbdata.setClickPause();
        return !mainGame.getIsPause();
    }

    @GetMapping("/speed")
    public int speed(){
        gbdata.setClickSpeed();
        return gbdata.getCurrspeed() ;
    }

    @PostMapping("/click")
    public String click(@RequestBody ModelRequest request){
        int i = 1;
        boolean isFirst = true;
        if(click_count == 1){
            isFirst = true;
            clickCoor1 = request.getCoor().split(",");
            coor1 = new Coor(Integer.parseInt(clickCoor1[0]),
            Integer.parseInt(clickCoor1[1]));
            click_count++;
        }else if(click_count == 2){
            isFirst = false;
            clickCoor2 = request.getCoor().split(",");
            coor2 = new Coor(Integer.parseInt(clickCoor2[0]),
            Integer.parseInt(clickCoor2[1]));
            click_count = 1;
            gbdata.checkInput2Coor(coor1, coor2);
        }else{
            click_count = 1;
        }
        if(!isFirst) i = 2;
        System.out.println(request.getCoor());
        return "Click : " + i;
    }

    @PostMapping("/ABSelected")
    public String selectingAB(){
        //when player click selecting one of three AB type to spawn in front-end shall post back to back-end with a integer (1,2,3) presenting a AB type. Then this method shall create a spawnAct object 
        //! seek GameMediator.spawningSampleGPS() for referent.
        throw new RuntimeException("Unimplemented");
    }
}
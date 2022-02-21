package yukongold.carin.requesthandler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yukongold.carin.gamestuff.GBData;

/**As the name might suggest, the "class" handle when player request game to 
 * start or manipulate game's speed (pause, slow, fast)
 */
interface RequestHandler{

    /**a order to initiate the game and let player in spawning phases
     * 
     * @return reply confirm the order
     */
    RequestHandler start();

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


    @GetMapping("/start")
    public String greet(){
        return "Hello Spring, you\'re soo cool.";
    }

    @GetMapping("/test")
    public GBData test(){
        GBData test = GBData.getInstance();
        return test.getMappingData();
    }

}
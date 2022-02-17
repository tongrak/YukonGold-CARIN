package yukongold.carin.requesthandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameMediator {


    @GetMapping("/start")
    public String greet(){
        return "Hello Spring, you\'re soo cool.";
    }

}
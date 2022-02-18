package yukongold.carin.gamestuff;

public class GPManager {
    private GPManager instance;
    private GPManager(){}
    public GPManager getInstance() {
        if(this.instance == null){
            this.instance = new GPManager();
        }
        return instance;
    }
}

// TODO a method that take in GPAction, process, and "act on it"
// TODO "send damage"(manipulate HP) to target GP
// TODO "move"(called storage to change coor) a target GP 
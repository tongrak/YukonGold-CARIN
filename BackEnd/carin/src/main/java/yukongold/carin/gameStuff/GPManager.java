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

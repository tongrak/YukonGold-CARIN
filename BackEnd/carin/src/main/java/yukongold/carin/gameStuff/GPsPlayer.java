package yukongold.carin.gamestuff;

import java.util.LinkedList;

public class GPsPlayer {
    private int gpCount = 0;
    private static LinkedList<GamePiece> GPLL;
    private static GPsPlayer instance;
    private GPsPlayer(){}

    public static GPsPlayer getInstance(){
        if(instance == null){
            instance = new GPsPlayer();
            GPLL = new LinkedList<>();
        }
        return instance;
    }

    public void startGPsTurn(){
        updateGPcount();
        for(int i = 0; i < gpCount; i++){
            GPLL.get(i).turnStart();
        }
    }

    public void addGP(GamePiece gp){
        GPLL.add(gp);
    }

    public void removeGP(GamePiece gp){
        GPLL.remove(gp);
        gpCount--;
    }

    public void updateGPcount(){
        this.gpCount = GPLL.size();
    }

}

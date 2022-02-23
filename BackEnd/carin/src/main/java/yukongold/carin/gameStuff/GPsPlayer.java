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
        GPsStorage GPsS = GPsStorage.getInstance(); 
        for(int i = 0; i < gpCount; i++){
            System.out.println(GPLL.get(i).getClass() + " is at " + GPsS.coorOfGP(GPLL.get(i)).getX() + "," + GPsS.coorOfGP(GPLL.get(i)).getY() );
            GPLL.get(i).turnStart(
                GPsS.coorOfGP(GPLL.get(i))
            );
        }
    }

    public void addGP(GamePiece gp){
        GPLL.add(gp);
    }

    public void removeGP(GamePiece gp){
        GPLL.remove(gp);
        gpCount--;
    }

    private void updateGPcount(){
        this.gpCount = GPLL.size();
    }

}

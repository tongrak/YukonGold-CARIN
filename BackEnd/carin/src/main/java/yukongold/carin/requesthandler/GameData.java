package yukongold.carin.requesthandler;

import java.util.LinkedList;

public class GameData {
    private boolean isPause;
    private int speed;
    private LinkedList<Pair<Pair<Integer,Integer>,String>> dataLL;
    private boolean winningStatus;

    public GameData(boolean isPause, 
            int speed, 
            LinkedList<Pair<Pair<Integer, Integer>, String>> dataLL,
            boolean winningStatus) {
        this.setPause(isPause);
        this.setSpeed(speed);
        this.setDataLL(dataLL);
        this.setWinnig(winningStatus);
    }

    public LinkedList<Pair<Pair<Integer,Integer>,String>> getDataLL() {
        return dataLL;
    }

    public void setDataLL(LinkedList<Pair<Pair<Integer,Integer>,String>> dataLL) {
        this.dataLL = dataLL;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }
    public boolean getWinningStatus(){
        return this.winningStatus;
    }

    public void setWinnig(boolean win) {
        this.winningStatus = win;
    }

}

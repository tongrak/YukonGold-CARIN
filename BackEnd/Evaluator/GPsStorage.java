import java.util.HashMap;
import java.util.Map;

interface GPsStorageInter{
    /** return nearest Virus's 8-directional coor from inputted GP.
     * @return nearest Virus in the form of 8-directional coor
     * */
    int nearestVirus(GamePiece gp);
    /** return nearest AB 8-directional coor from inputted GP.
     * @return nearest AB in the form of 8-directional coor
     * */
    int nearestAntiBody(GamePiece gp);
    /** return nearest GamePiece's 1-directional GP's coor from inputted GP.
     * @return nearest GP in given direction. if it a virus return 10x+1 else (AB) 10x+2; x = distance from host GP.
     * */
    int nearbyInDirec(int eightDirecCoor);

    /** return true if given Coor is occupied or not
     *
     * @return true if said Coor is occupied else false;
     * */
    boolean coorOccupied(Coor coor);

    /** check if given GP existed in the storage;
     * @return true if given GP existed in the storage, else false;
     * */
    boolean containGP(GamePiece gp);
}

public class GPsStorage implements GPsStorageInter {
    private GPsStorage instance;
    private GPsStorage(){}
    private Map<Coor, GamePiece> GPCoorMap;

    public GPsStorage getInstance(){
        if(this.instance == null){
            this.instance = new GPsStorage();
            this.GPCoorMap = new HashMap<>();
        }
        return this.instance;
    }


    @Override
    public int nearestVirus(GamePiece gp) {
        return 0;
    }

    @Override
    public int nearestAntiBody(GamePiece gp) {
        return 0;
    }

    @Override
    public int nearbyInDirec(int eightDirecCoor) {
        return 0;
    }

    @Override
    public boolean coorOccupied(Coor coor) {
        return false;
    }

    @Override
    public boolean containGP(GamePiece gp) {
        return false;
    }


}

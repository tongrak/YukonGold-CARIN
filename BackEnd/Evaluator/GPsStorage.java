import java.util.HashMap;
import java.util.Map;

public class GPsStorage {
    private GPsStorage instance;
    private GPsStorage(){}
    private static Map<Coor, GamePiece> GPCoorMap;

    public GPsStorage getInstance(){
        if(this.instance == null){
            this.instance = new GPsStorage();
            GPCoorMap = new HashMap<>();
        }
        return this.instance;
    }

    /** return nearest Virus's 8-directional coor from inputted GP.
     * @return nearest Virus in the form of 8-directional coor
     * */
    public static int nearestVirus(Coor gpCoor) {
        throw new RuntimeException("unimplemented");
    }
    /** return nearest AB 8-directional coor from inputted GP.
     * @return nearest AB in the form of 8-directional coor
     * */
    public static int nearestAntiBody(Coor gpCoor) {
        throw new RuntimeException("unimplemented");
    }
    /** return nearest GamePiece's 1-directional GP's coor from inputted GP.
     * @return nearest GP in given direction. if it a virus return 10x+1 else (AB) 10x+2; x = distance from host GP.
     * */
    public static int nearbyInDirec(Coor coor, int eightDirecCoor) {
        throw new RuntimeException("unimplemented");
    }
    /** return true if given Coor is occupied or not
     * @return true if said Coor is occupied else false;
     * */
    public static boolean coorOccupied(Coor coor) {
        return !GPCoorMap.containsKey(coor);
    }
    /** check if given GP existed in the storage;
     * @return true if given GP existed in the storage, else false;
     * */
    public static boolean containGP(GamePiece gp) {
        return GPCoorMap.containsValue(gp);
    }

    public static Coor coorOfGP(GamePiece gp) {
        if(!containGP(gp))return null;
        for(Coor c: GPCoorMap.keySet())if(gp.equals(GPCoorMap.get(c))) return c;
        return null;
    }

    /** adding or change already exist gp current coor iff given coor is not occupied;
     *
     * @return true if adding or change was successful;
     * */
    public static boolean setGPintoStorage(GamePiece gp, Coor coor){
        if(!containGP(gp)){
            if(!coorOccupied(coor)) { GPCoorMap.put(coor,gp); return true;}
            else return false;
        }else{
            if(!coorOccupied(coor)){
                GPCoorMap.remove(coorOfGP(gp));
                GPCoorMap.put(coor,gp);
                return true;
            }else return false;
        }
    }
}

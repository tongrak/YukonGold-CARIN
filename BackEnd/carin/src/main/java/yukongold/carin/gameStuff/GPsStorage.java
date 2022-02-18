package yukongold.carin.gamestuff;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// TODO Test it.
public class GPsStorage {
    private static GPsStorage instance;
    private GPsStorage(){}
    private static Map<Coor, GamePiece> GPCoorMap;

    public static GPsStorage getInstance(){
        if(instance == null){
            instance = new GPsStorage();
            GPCoorMap = new HashMap<>();
        }
        return instance;
    }

    public Map<Coor, GamePiece> getMap(){
        return GPCoorMap;
    }

    private int nearestGP(Coor gpCoor){
        // TODO work on this.
        int x = gpCoor.getX();
        int y = gpCoor.getY();
        return 0;

    }

    /** return nearest Virus's 8-directional coor from inputted GP.
     * @return nearest Virus in the form of 8-directional coor
     * */
    public static int nearestVirus(Coor gpCoor) {
        // TODO work on this.
        throw new RuntimeException("nearestVirus: unimplemented");
    }
    /** return nearest AB 8-directional coor from inputted GP.
     * @return nearest AB in the form of 8-directional coor
     * */
    public static int nearestAntiBody(Coor gpCoor) {
        // TODO work on this.
        throw new RuntimeException("nearestAntiBody: unimplemented");
    }
    /** return nearest GamePiece's 1-directional GP's coor from inputted GP.
     * @return nearest GP in given direction. if it a virus return 10x+1 else (AB) 10x+2; x = distance from host GP.
     * */
    public static int nearbyInDirec(Coor coor, int eightDirecCoor) {
        // TODO work on this.
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
    public boolean setGPintoStorage(GamePiece gp, Coor coor){
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

    public boolean relocateAB(Coor selected, Coor destination){
        return setGPintoStorage(GPCoorMap.get(selected), destination);
    }

    /**return true if current winning condition is met, Which is all GP on the boards consist only one side (AB or Virus) 
     * 
     * @return true if all GP on the boards consist only one side.
     */
    public boolean checkWinningCond(){
        int GPDiff = 0;
        if(GPCoorMap.keySet().size() == 0) return true;
        for(Coor c: GPCoorMap.keySet()){
            if(GPCoorMap.get(c).getClass().equals(Antibody.class)){
                GPDiff++;
            }else{
                GPDiff--;
            }
        }
        return Math.abs(GPDiff) == GPCoorMap.keySet().size();
    }

    public boolean removeGP(GamePiece gp){
        throw new RuntimeException("Unimplemented");
    }

    /**replace AB with new Virus at the same coor
     * 
     * @return true if replacment was succuessful.
     */
    public boolean replaceABwithVirus(Path virusGene, Coor ABCoor){
        throw new RuntimeException("Unimplemented");
    }
}

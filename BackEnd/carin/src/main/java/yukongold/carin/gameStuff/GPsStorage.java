package yukongold.carin.gamestuff;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// TODO Test it.
public class GPsStorage {
    private static int m; //maxX axis
    private static int n; //maxY axis
    private static GPsStorage instance;
    private GPsStorage(){}
    private static Map<Coor, GamePiece> GPCoorMap;

    public static GPsStorage getInstance(){
        if(instance == null){
            instance = new GPsStorage();
            GPCoorMap = new HashMap<>();
            m = 25;
            n = 25;
        }
        return instance;
    }
    /** setting m (max X) and n (max Y)
     * 
     * @param mIn for m
     * @param nIn for n
     */
    public void setMN(int mIn, int nIn){m = mIn; n = nIn;}

    public Map<Coor, GamePiece> getMap(){
        return GPCoorMap;
    }

    // ! O(n) could be O(ln)
    /** check if there is a GP at inpputed x and y;
     * 
     * @param x for x part of Coor
     * @param y for y part of Coor
     * @return true if there is GP exist at x,y.
     */
    private static boolean containXY(int x, int y){
        for(Coor c: GPCoorMap.keySet()){
            if(c.getX()==x && c.getY()==y)return true;
        }
        return false;
    }

    /** return a LinkedList of Coor which detected GP. Ordering from nearest to far. seaching pattern is as Proj spec (8direc)
     * 
     * @param gpCoor Coor of the host
     * @return LinkedList of Coor where GP was detected
     */
    private  static LinkedList<Coor> nearestGP(Coor gpCoor){
        int x = gpCoor.getX();
        int y = gpCoor.getY();
        int repeatitionCount = Math.max(x, y);
        LinkedList<Coor> toReturn = new LinkedList<>();
        for (int i = 1; i < repeatitionCount; i++) {
            if(containXY(x, y+i)) toReturn.add(new Coor(x, y+i));
            if(containXY(x+i, y+i)) toReturn.add(new Coor(x+i, y+i));
            if(containXY(x+i, y)) toReturn.add(new Coor(x+i, y));
            if(containXY(x+i, y-i)) toReturn.add(new Coor(x+i, y-i));
            if(containXY(x, y-i)) toReturn.add(new Coor(x, y-i));
            if(containXY(x-i, y-i)) toReturn.add(new Coor(x-i, y-i));
            if(containXY(x-i, y)) toReturn.add(new Coor(x-i, y));
            if(containXY(x-i, y+i)) toReturn.add(new Coor(x-i, y+i));
        }
        return toReturn;
    }

    /** return a distance form host coor to target coor in the form of 8direc
     * 
     * @param host host coor
     * @param target targeted coor
     * @return distance in a form of 8direc(xy which x is distance y is direction).
     */
    private static int distanceFromHost(Coor host, Coor target){
        int xDiff = target.getX() - host.getX();
        int yDiff = target.getY() -  host.getY();
        int distance = ((Math.abs(xDiff)>0)? Math.abs(xDiff):Math.abs(yDiff))*10;
        if(xDiff>0){
            return distance + ((yDiff>0)?2:(yDiff==0)?3:4);
        }else if(xDiff==0){
            return distance + ((yDiff>0)?1:5);
        }else{
            return distance + ((yDiff>0)?8:(yDiff==0)?7:6);
        }
    }

    /** return nearest Virus's 8-directional coor from inputted GP.
     * @return nearest Virus in the form of 8-directional coor
     * */
    public static int nearestVirus(Coor gpCoor) {
        LinkedList<Coor> holder = nearestGP(gpCoor);
        if(holder.size()==0)return 0;
        for(Coor c: nearestGP(gpCoor)){
            if(GPCoorMap.get(c).getClass().equals(Virus.class)){
                return distanceFromHost(gpCoor, c);
            }
        }
        return 0;
    }
    /** return nearest AB 8-directional coor from inputted GP.
     * @return nearest AB in the form of 8-directional coor
     * */
    public static int nearestAntiBody(Coor gpCoor) {
        LinkedList<Coor> holder = nearestGP(gpCoor);
        if(holder.size()==0)return 0;
        for(Coor c: nearestGP(gpCoor)){
            if(GPCoorMap.get(c).getClass().equals(Antibody.class)){
                return distanceFromHost(gpCoor, c);
            }
        }
        return 0;
    }
    /** return nearest GamePiece's 1-directional GP's coor from inputted GP.
     * @return nearest GP in given direction. if it a virus return 10x+1 else (AB) 10x+2; x = distance from host GP.
     * */
    public static int nearbyInDirec(Coor coor, int eightDirecCoor) {
        int x = coor.getX();
        int y = coor.getY();
        int repeatitionCount = Math.max(coor.getX(), Math.max(coor.getY(), Math.max(m-coor.getX(), n-coor.getY())));
        int distance = 0;
        for(int i = 1; i <= repeatitionCount; i++){
            switch (eightDirecCoor) {
                case 11->{if(containXY(x, y+i)) {distance = i; break;}}
                case 12->{if(containXY(x+i, y+i)) {distance = i; break;}}
                case 13->{if(containXY(x+i, y)) {distance = i; break;}}
                case 14->{if(containXY(x+i, y-i)) {distance = i; break;}}
                case 15->{if(containXY(x, y-i)) {distance = i; break;}}
                case 16->{if(containXY(x-i, y-i)) {distance = i; break;}}
                case 17->{if(containXY(x-i, y)) {distance = i; break;}}
                case 18->{if(containXY(x-i, y+i)) {distance = i; break;}}
            }
            if(distance>0){break;}
        }
        return distance;
    }
    /** return true if given Coor is occupied or not
     * @return true if said Coor is occupied else false;
     * */
    public boolean coorOccupied(Coor coor) {
        return GPCoorMap.containsKey(coor);
    }
    /** check if given GP existed in the storage;
     * @return true if given GP existed in the storage, else false;
     * */
    public boolean containGP(GamePiece gp) {
        return GPCoorMap.containsValue(gp);
    }

    public Coor coorOfGP(GamePiece gp) {
        if(!containGP(gp))return null;
        for(Coor c: GPCoorMap.keySet())if(gp.equals(GPCoorMap.get(c))) return c;
        return null;
    }

    /** adding or change already exist gp current coor iff given coor is not occupied;
     *
     * @return true if adding or change was successful;
     * */
    public boolean setGPintoStorage(GamePiece gp, Coor coor){
        if(coor.getX()>m || coor.getX()<0 || coor.getY() >n || coor.getY()<0) throw new RuntimeException("illegal GP setting: (" + coor.getX() + "," + coor.getY() + ")" );
        if(!containGP(gp)){
            if(!coorOccupied(coor)) { 
                GPCoorMap.put(coor,gp);
                 return true;}
            else return false;
        }else{
            if(!coorOccupied(coor)){
                GPCoorMap.remove(coorOfGP(gp));
                GPCoorMap.put(coor,gp);
                return true;
            }else return false;
        }
    }

    /**Relocating GP at selected Coor to Coor at destination
     * 
     * @param selected Coor of GP to be relocated
     * @param destination Coor of desinatate location 
     * @return true if relocation was successful
     */
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


    public void removeGP(GamePiece gp){
        if(GPCoorMap.containsValue(gp))GPCoorMap.remove(coorOfGP(gp));
    }

    /**replace AB with new Virus at the same coor
     * 
     * @return true if replacment was succuessful.
     */
    public void replaceABwithVirus(Path virusGene, Coor ABCoor){
        if(GPCoorMap.containsKey(ABCoor)){
            GPCoorMap.remove(ABCoor);
            GPsFactory GPFac = GPsFactory.getInstance();
            setGPintoStorage(GPFac.createNewVirus(virusGene), ABCoor);
        }

    }

    /**Getting GP at inputed Coor. If said Coor doesn't contain GP this will return null
     * 
     * @param atCoor Coor in question
     * @return GP at inputted Coor, or null if at Coor is empty.
     */
    public GamePiece getGPAt(Coor atCoor){
        if(GPCoorMap.containsKey(atCoor)){
            return GPCoorMap.get(atCoor);
        }else return null;
    }
}

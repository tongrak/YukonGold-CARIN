package yukongold.carin.gamestuff;

public class GPManager {
    private int ABDamage = 10;
    private int VirusDamage = 10;
    private int ABGain = 5;
    private int VirusGain = 5;
    private int relocateCost = 0;

    private static GPManager instance;
    private static GPsStorage GpStore;
    private static GPsPlayer gpP;
    private static GBData gbData;
    private GPManager(){}

    public static GPManager getInstance() {
        if(instance == null){
            instance = new GPManager();
            GpStore = GPsStorage.getInstance();
            gpP = GPsPlayer.getInstance();
            gbData = GBData.getInstance();
        }
        return instance;
    }

    public void setGPsDamage(int vDamage, int abDamage){
        this.ABDamage = abDamage;
        this.VirusDamage = vDamage;
    }

    public void setGPsGain(int vGain, int abGain){
        this.ABGain = abGain;
        this.VirusGain = vGain;
    }
    
    public void setRelocateCost(int relocateCost) {
        this.relocateCost = relocateCost;
    }

    /** Act what host request through passed GPAction and its Coor
     * 
     * @param action action host requested
     * @param coor host's current coor
     */
    public void actGPAct(GPAction action, Coor coor){
        System.out.println("GP at: " + coor.getX() +"," +coor.getY()+" act :" + action);
        int eightDirec = action.getDirection()%10;
        boolean isVirus = GpStore.getGPAt(coor).getClass().equals(Virus.class);
        if(action.getClass().equals(ShootAct.class)){
            switch (eightDirec) {
                case 1->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX(), coor.getY()+1)), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 2->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX()+1, coor.getY()+1)), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 3->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX()+1, coor.getY())), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 4->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX()+1, coor.getY()-1)), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 5->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX(), coor.getY()-1)), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 6-> {
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX()-1, coor.getY()-1)), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 7->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX()-1, coor.getY())), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
                case 8->{
                    shootingGP(
                        GpStore.getGPAt(new Coor(coor.getX()-1, coor.getY()+1)), 
                        (isVirus)? VirusDamage: ABDamage,
                        coor);
                }
            }
        }else if(action.getClass().equals(MoveAct.class)){
            switch (eightDirec) {
                case 1->{movingGP(coor, new Coor(coor.getX(), coor.getY()+1));}
                case 2->{movingGP(coor, new Coor(coor.getX()+1, coor.getY()+1));} 
                case 3->{movingGP(coor, new Coor(coor.getX()+1, coor.getY()));} 
                case 4->{movingGP(coor, new Coor(coor.getX()+1, coor.getY()-1));} 
                case 5->{movingGP(coor, new Coor(coor.getX(), coor.getY()-1));} 
                case 6->{movingGP(coor, new Coor(coor.getX()-1, coor.getY()-1));} 
                case 7->{movingGP(coor, new Coor(coor.getX()-1, coor.getY()));} 
                case 8->{movingGP(coor, new Coor(coor.getX()-1, coor.getY()+1));} 
            }

        }
    }

    /**Manipulate requested gp's hp with inputted damage. Require host Coor to work properly.
     * 
     * @param gp GP taken the Damage
     * @param damage amount of Damage
     * @param hostCoor Coor of host
     */
    private void shootingGP(GamePiece gp, int damage, Coor hostCoor){
        if(!gp.hpManipulate(damage*(-1))){
            if(GpStore.getGPAt(hostCoor).getClass().equals(Virus.class))
            GpStore.getGPAt(hostCoor).hpManipulate(VirusGain);
        }else{ //gp is dead
            if(GpStore.getGPAt(hostCoor).getClass().equals(Virus.class)){
                GpStore.replaceABwithVirus(
                    GpStore.getGPAt(hostCoor).getTheGeneCode(), 
                    GpStore.coorOfGP(gp));
            }else{
                GpStore.removeGP(gp);
                GpStore.getGPAt(hostCoor).hpManipulate(ABGain);
                gbData.increaseVDeadCount();
            }
            gpP.removeGP(gp);
        }
    }

    /**Moving GP at host Coor to destinated COor
     * 
     * @param hostCoor Coor of the host
     * @param destinationCoor Coor which host is traveling to
     */
    private boolean movingGP(Coor hostCoor, Coor destinationCoor){
        if(destinationCoor.getX()<0||destinationCoor.getX()>25||destinationCoor.getY()<0||destinationCoor.getY()>25 )return false;
        return GpStore.relocateAB(hostCoor, destinationCoor);
    }

    public boolean relocateAB(Coor hostCoor, Coor destinationCoor){
        if(GpStore.getGPAt(hostCoor).hpManipulate(this.relocateCost*(-1))){
            GpStore.removeGP(GpStore.getGPAt(hostCoor));
            gpP.removeGP(GpStore.getGPAt(hostCoor));
            return false;
        }else{
            return movingGP(hostCoor, destinationCoor);
        }
    }
}
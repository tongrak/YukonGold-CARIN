package yukongold.carin.gamestuff;

public interface StatementBox{
    /** Each Statement lead to termination some way or another.
     * Which, said termination could be an Action for GP.
     *
     * @return the 1st GPAction detected in the statement or null if none have detected.
     * */
    GPAction getGPAction(Coor theCoor) throws SyntaxError; //if return null == not terminable
}
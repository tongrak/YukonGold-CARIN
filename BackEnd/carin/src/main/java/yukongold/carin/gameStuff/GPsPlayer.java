package yukongold.carin.gamestuff;

import java.util.LinkedList;

interface GPsPlayerInter{
    void turnStart();
    void addGP(GamePiece gp);
    void removeGP(GamePiece gp);
}

abstract class GPsPlayerAbs{
    LinkedList<GamePiece> GPlist;
}

public class GPsPlayer {
    
}

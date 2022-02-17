package yukongold.carin.gamestuff;

interface GPAction{
    int getDirection();
}

public abstract class GPActAbs implements GPAction{
    protected final int direction;

    public GPActAbs(int direction){
        this.direction = direction;
    }

    @Override
    public int getDirection() {
        return this.direction;
    }
}

class MoveAct extends GPActAbs {
    public MoveAct(int direction){
        super(direction);
    }
}

class ShootAct extends GPActAbs{
    public ShootAct(int direction){
        super(direction);
    }
}

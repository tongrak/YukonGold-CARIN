package yukongold.carin.gamestuff;

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

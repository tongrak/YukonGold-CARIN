package yukongold.carin.requesthandler;

public class Pair<T, Y> {
    private final T fst;
    private final Y snd;

    public Pair(T fst, Y snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public T getFst(){
        return fst;
    }

    public Y getSnd(){
        return snd;
    }
}

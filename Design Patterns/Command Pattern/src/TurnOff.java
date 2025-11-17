public class TurnOff implements Command{
    private final Tv tv;

    public TurnOff(Tv tv){
        this.tv = tv;
    }

    @Override
    public void execute(){
        tv.turnOff();
    }
}

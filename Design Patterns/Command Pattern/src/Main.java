public class Main {
    public static void main(String[]  main){
        Tv tv = new Tv();
        Command turnOnCommand = new TurnOnCommand(tv);
        Command turnOffCommand = new TurnOff(tv);
        Command volumeChange = new AdjustVolumeCommand(tv,5);
        TvRemote tvRemote = new TvRemote();
        tvRemote.setOnCommand(turnOnCommand);
        tvRemote.setOffCommand(turnOffCommand);
        tvRemote.setVolumeChange(volumeChange);
        tvRemote.pressOnCommand();
        tvRemote.pressOffCommand();
        tvRemote.pressVolumeChangeCommand();
    }
}

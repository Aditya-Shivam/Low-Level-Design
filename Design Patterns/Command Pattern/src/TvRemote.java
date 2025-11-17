public class TvRemote {
    private Command onCommand;
    private Command offCommand;
    private Command volumeChange;
    private Command channelChange;

    public void setOffCommand(Command offCommand) {
        this.offCommand = offCommand;
    }

    public void setVolumeChange(Command volumeChange) {
        this.volumeChange = volumeChange;
    }

    public void setChannelChange(Command channelChange) {
        this.channelChange = channelChange;
    }

    public void setOnCommand(Command onCommand) {
        this.onCommand = onCommand;
    }
    public void pressOnCommand(){
        onCommand.execute();
    }
    public void pressOffCommand(){
        offCommand.execute();
    }
    public void pressChannelChangeCommand(){
        channelChange.execute();
    }
    public void pressVolumeChangeCommand(){
        volumeChange.execute();
    }
}

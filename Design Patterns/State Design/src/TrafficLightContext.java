public class TrafficLightContext {
    private TrafficLightState trafficLightState;

    public TrafficLightContext(){
        this.trafficLightState = new RedState();
    }

    public void setState(TrafficLightState trafficLightState){
        this.trafficLightState = trafficLightState;
    }

    public String getColor(){
        return this.trafficLightState.getColor();
    }

    public void next(){
        this.trafficLightState.next(this);
    }
}

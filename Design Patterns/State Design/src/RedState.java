public class RedState implements TrafficLightState{
    private final String color = "RED";

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void next(TrafficLightContext context) {
        context.setState(new YellowState());
    }
}

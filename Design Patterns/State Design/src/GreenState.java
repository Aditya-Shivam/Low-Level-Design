public class GreenState implements TrafficLightState {
    private final String color = "GREEN";

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void next(TrafficLightContext context) {
        context.setState(new RedState());
    }
}

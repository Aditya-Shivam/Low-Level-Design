public class YellowState implements TrafficLightState {
    private final String color = "YELLOW";

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void next(TrafficLightContext context) {
        context.setState(new GreenState());
    }
}

public class Main {
    public static void main(String[] args){
        TrafficLightContext trafficLightContext = new TrafficLightContext();
        for(int i = 0; i < 9 ; i++){
            System.out.println(trafficLightContext.getColor());
            trafficLightContext.next();
        }

    }
}

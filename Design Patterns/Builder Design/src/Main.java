import java.util.Arrays;

public class Main {
    public static void main(String[] args){
       Car newCar = new Car.CarBuilder()
               .setEngine("ABCD1")
               .setWheels(3)
               .build();
        System.out.println(newCar);

        Car newCar2 = new Car.CarBuilder()
                .build();
        System.out.println(newCar2);

    }
}

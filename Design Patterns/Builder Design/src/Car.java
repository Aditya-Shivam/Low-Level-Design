public class Car {
    private String engine;
    private int wheels;
    private String color;
    private boolean hasSunRoof;
    private boolean hasNavigationSystem;

    private Car(CarBuilder carBuilder) {
        this.color = carBuilder.color;
        this.engine = carBuilder.engine;
        this.hasNavigationSystem = carBuilder.hasNavigationSystem;
        this.hasSunRoof = carBuilder.hasSunRoof;
        this.wheels = carBuilder.wheels;
    }
    // CAR BUILDER CLASS
    public static class CarBuilder{
        private String engine;
        private int wheels = 4;
        private String color = "BLACK";
        private boolean hasSunRoof = false;
        private boolean hasNavigationSystem = true;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setHasSunRoof(boolean hasSunRoof) {
            this.hasSunRoof = hasSunRoof;
            return this;
        }

        public CarBuilder setHasNavigationSystem(boolean hasNavigationSystem) {
            this.hasNavigationSystem = hasNavigationSystem;
            return this;
        }

        public Car build(){
            return new Car(this);
        }
    }

    // GETTER
    public String getColor() {
        return color;
    }

    public String getEngine() {
        return engine;
    }

    public boolean isHasNavigationSystem() {
        return hasNavigationSystem;
    }

    public boolean isHasSunRoof() {
        return hasSunRoof;
    }

    public int getWheels() {
        return wheels;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheels=" + wheels +
                ", color='" + color + '\'' +
                ", hasSunRoof=" + hasSunRoof +
                ", hasNavigationSystem=" + hasNavigationSystem +
                '}';
    }
}

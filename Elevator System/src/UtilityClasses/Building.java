package UtilityClasses;

public class Building {
    private String name;
    private int numOfFloors;
    private ElevatorController elevatorController;

    public Building(String name, int numOfFloors, int numOfElevators) {
        this.name = name;
        this.numOfFloors = numOfFloors;
        this.elevatorController = new ElevatorController(numOfElevators,numOfFloors);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }

    public void setElevatorController(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }
}

package UtilityClasses;

import Command.ElevatorRequest;
import CommonEnums.Direction;
import Strategy.ScanScheduling;
import Strategy.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;
    private List<Floor> floors;
    private SchedulingStrategy schedulingStrategy;

    public ElevatorController(int numberOfElevators, int numberOfFloors){
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.schedulingStrategy = new ScanScheduling();
        for(int i = 1; i<=numberOfElevators; i++){
            elevators.add(new Elevator(i));
        }
        for(int i = 1; i<=numberOfFloors; i++){
            floors.add(new Floor(i));
        }
    }
    public void requestElevator(int elevatorId, int floor, Direction direction){

        System.out.println(
                "External request: Floor " + floor + ", Direction " + direction);
        Elevator elevator = elevators.get(elevatorId);
        elevator.addRequest(new ElevatorRequest(elevatorId,floor,direction,false));
        System.out.println("Assigned elevator " + elevatorId
                + " to floor " + floor);
    }

    public void requestFloor(int elevatorId,int floorNumber){
        Elevator elevator = elevators.get(elevatorId);
        System.out.println("Internal request: Elevator " + elevator.getId()
                + " to floor " + floorNumber);
        Direction direction = elevator.getCurrentFloor() > floorNumber ? Direction.DOWN : Direction.UP;
        elevator.addRequest(new ElevatorRequest(elevatorId,floorNumber,direction,true));
    }
    public void step(){
        for(Elevator elevator : elevators){
            if(elevator.getRequests().isEmpty()) continue;
            int nextStop = schedulingStrategy.getNextFloor(elevator);
            if(nextStop != elevator.getCurrentFloor())
                elevator.moveToNextStop(schedulingStrategy.getNextFloor(elevator));
        }
    }

    // GETTERS AND SETTERS
    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void setSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
        this.schedulingStrategy = schedulingStrategy;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public SchedulingStrategy getSchedulingStrategy() {
        return schedulingStrategy;
    }


}

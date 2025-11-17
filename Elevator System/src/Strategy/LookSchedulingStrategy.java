package Strategy;

import Command.ElevatorRequest;
import CommonEnums.Direction;
import UtilityClasses.Elevator;

import java.util.Queue;

public class LookSchedulingStrategy implements SchedulingStrategy{
    @Override
    public int getNextFloor(Elevator elevator) {
        Queue<ElevatorRequest> elevatorRequests = elevator.getRequests();
        int currentFloor = elevator.getCurrentFloor();
        if(elevatorRequests.isEmpty()) return currentFloor;
        int desiredFloor = elevatorRequests.peek().getFloor();

        if(desiredFloor == currentFloor) return currentFloor;
        Direction desiredDirection = desiredFloor > currentFloor ? Direction.UP  : Direction.DOWN;
        Integer nextFloor = null;
        for(ElevatorRequest elevatorRequest : elevatorRequests){
            int requestedFloor = elevatorRequest.getFloor();
            if(desiredDirection == Direction.UP && requestedFloor > currentFloor && requestedFloor <= desiredFloor){
                if(elevatorRequest.isInternalRequest() || (!elevatorRequest.isInternalRequest() && elevatorRequest.getRequestDirection() == Direction.UP)){
                    if(nextFloor == null || requestedFloor < nextFloor){
                        nextFloor = requestedFloor;
                    }
                }
            } else if(desiredDirection == Direction.DOWN && requestedFloor < currentFloor && requestedFloor >= desiredFloor){
                if(elevatorRequest.isInternalRequest() || (!elevatorRequest.isInternalRequest() && elevatorRequest.getRequestDirection() == Direction.DOWN)){
                    if(nextFloor == null || requestedFloor > nextFloor){
                        nextFloor = requestedFloor;
                    }
                }
            }
        }
        return  nextFloor != null ? nextFloor : desiredFloor;

    }
}

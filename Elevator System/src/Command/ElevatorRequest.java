package Command;

import CommonEnums.Direction;


public class ElevatorRequest{
    private int elevatorId;
    private int floor;
    private Direction requestDirection;
    private boolean isInternalRequest;

    public ElevatorRequest(int elevatorId, int floor, Direction requestDirection, boolean isInternalRequest) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.requestDirection = requestDirection;
        this.isInternalRequest = isInternalRequest;
    }

    public int getFloor() {
        return floor;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public Direction getRequestDirection() {
        return requestDirection;
    }

    public boolean isInternalRequest() {
        return isInternalRequest;
    }

}

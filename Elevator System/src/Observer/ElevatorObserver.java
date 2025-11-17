package Observer;

import CommonEnums.ElevatorState;
import UtilityClasses.Elevator;

public interface ElevatorObserver {
    void onElevatorStateChange(Elevator elevator, ElevatorState state);
    void onElevatorFloorChange(Elevator elevator, int floor);
}

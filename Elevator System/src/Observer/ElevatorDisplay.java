package Observer;

import CommonEnums.ElevatorState;
import UtilityClasses.Elevator;

public class ElevatorDisplay implements ElevatorObserver{
    @Override
    public void onElevatorStateChange(Elevator elevator, ElevatorState state) {
        System.out.println("Elevator " + elevator.getId() + " state changed to " + state);
    }

    @Override
    public void onElevatorFloorChange(Elevator elevator, int floor) {
        System.out.println("Elevator " + elevator.getId() + " moved to floor " + floor);
    }
}

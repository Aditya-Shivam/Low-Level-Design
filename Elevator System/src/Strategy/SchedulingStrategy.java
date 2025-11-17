package Strategy;

import UtilityClasses.Elevator;

public interface SchedulingStrategy {
    int getNextFloor(Elevator Elevator);
}

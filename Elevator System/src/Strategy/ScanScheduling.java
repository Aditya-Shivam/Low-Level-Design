package Strategy;

import Command.ElevatorRequest;
import CommonEnums.Direction;
import UtilityClasses.Elevator;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ScanScheduling implements SchedulingStrategy{
    @Override
    public int getNextFloor(Elevator elevator) {
        Queue<ElevatorRequest> requests = elevator.getRequests();
        int currentFloor = elevator.getCurrentFloor();
        Direction elevatorDirection = elevator.getDirection();

        if(requests.isEmpty()) return currentFloor;

        PriorityQueue<ElevatorRequest> upQueue = new PriorityQueue<>((a,b)-> a.getFloor() - b.getFloor());
        PriorityQueue<ElevatorRequest> downQueue = new PriorityQueue<>((b,a)-> a.getFloor() - b.getFloor());

        while(!requests.isEmpty()){
            ElevatorRequest elevatorRequest = requests.poll();
            int floor = elevatorRequest.getFloor();
            if(currentFloor < floor) upQueue.add(elevatorRequest);
            else downQueue.add(elevatorRequest);
        }

        if(elevatorDirection == Direction.IDLE){
            int nearestUpwardDirection = !upQueue.isEmpty() ? upQueue.poll().getFloor() : -1;
            int nearestDownwardDirection = !downQueue.isEmpty() ? downQueue.poll().getFloor() : -1;

            if(nearestUpwardDirection == -1){
                elevator.setDirection(Direction.DOWN);
                return nearestDownwardDirection;
            } else if(nearestDownwardDirection == -1){
                elevator.setDirection(Direction.UP);
                return nearestUpwardDirection;
            }
            if(Math.abs(currentFloor - nearestUpwardDirection) >= Math.abs(currentFloor - nearestDownwardDirection)){
                elevator.setDirection(Direction.DOWN);
                return nearestDownwardDirection;
            } else{
                elevator.setDirection(Direction.UP);
                return nearestUpwardDirection;
            }
        }

        if(elevatorDirection == Direction.UP){
            return !upQueue.isEmpty() ? upQueue.poll().getFloor() : switchDirection(elevator,downQueue);
        }
            return !downQueue.isEmpty() ? downQueue.poll().getFloor() : switchDirection(elevator,upQueue);
    }


    private int switchDirection(Elevator elevator, PriorityQueue<ElevatorRequest> queue) {
        elevator.setDirection(elevator.getDirection() == Direction.UP ? Direction.DOWN : Direction.UP);
        return queue.isEmpty() ? elevator.getCurrentFloor() : queue.poll().getFloor();
    }
    }

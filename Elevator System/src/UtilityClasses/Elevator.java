package UtilityClasses;

import Command.ElevatorRequest;
import CommonEnums.Direction;
import CommonEnums.ElevatorState;
import Observer.ElevatorObserver;

import javax.lang.model.element.ElementVisitor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private Queue<ElevatorRequest> requests;
    private List<ElevatorObserver> observers;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.requests = new LinkedList<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver observer){
        observers.add(observer);
    }
    
    public void removeObserver(ElevatorObserver observer){
        observers.remove(observer);
    }
    
    public void notifyStateChange(ElevatorState state){
        for(ElevatorObserver elevatorObserver : observers){
            elevatorObserver.onElevatorStateChange(this,state);
        }
    }
    public void notifyFloorChange(int floor){
        for(ElevatorObserver elevatorObserver : observers){
            elevatorObserver.onElevatorFloorChange(this,floor);
        }
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }
    
    public void addRequest(ElevatorRequest request){
        if(!requests.contains(request)){
            requests.add(request);
        }
        
        int requestedFloor = request.getFloor();
        if(state == ElevatorState.IDLE && !requests.isEmpty()){
            if(requestedFloor > currentFloor){
                direction = Direction.UP;
            } else {
                direction = Direction.DOWN;
            }
            setState(ElevatorState.MOVING);
        }
    }
    
    public void moveToNextStop(int nextFloor){
        if(state != ElevatorState.MOVING){
            return;
        }
        while(currentFloor != nextFloor){
            if(direction == Direction.UP){
                currentFloor++;
            } else {
                currentFloor--;
            }
            notifyFloorChange(currentFloor);
            if(currentFloor == nextFloor){
                completeArrival();
            }
        }
    }

    private void completeArrival() {
        setState(ElevatorState.STOPPED);
        requests.removeIf(r -> currentFloor == r.getFloor());
        if(requests.isEmpty()){
            setState(ElevatorState.IDLE);
            direction = Direction.IDLE;
        } else {
            setState(ElevatorState.MOVING);
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public Queue<ElevatorRequest> getRequests() {
        return new LinkedList<>(requests);
    }

    public List<ElevatorObserver> getObservers() {
        return new ArrayList<>(observers);
    }
}

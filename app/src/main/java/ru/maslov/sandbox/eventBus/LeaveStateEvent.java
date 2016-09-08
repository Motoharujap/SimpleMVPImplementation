package ru.maslov.sandbox.eventBus;

/**
 * Created by Администратор on 19.08.2016.
 */
public class LeaveStateEvent {
    //name of a presenter class which view is going to be left (closed).
    //you should always you class.getSimpleName();
    public String className;

    public LeaveStateEvent(String className){
        this.className = className;
    }
}

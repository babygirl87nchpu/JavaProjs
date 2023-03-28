package edu.vt.cs5044;
/**
 This class is called MinivanSlidingDoor and it describes 
 *  all the properties the ways that the door can open and close.
 *  The door is governed entirely by the operational rules.
 *  Operational rules such open or close, locked or unlocked, 
 *  child safe engaged or disengage
 *
 * @author SamIAm
 * @version Feb 22, 2023
 *
 */
public class MinivanSlidingDoor {

    /**
     * Cls/Opn: Closed or Opened Unl/Lck: Unlocked or Locked CSE/CSD: Childsafe is engaged or
     * Disengagaed Prk/Rel: Gear is PARK or non-PARK
     * 
     * 1 open door 2 engage CS 3 Release gear (any non-PARK gear) 4 close door
     * 
     * open door via ___ Result incorrect OR status isOpen()
     */
    private boolean doorOpen;
    private boolean doorLocked;
    private boolean childSafteyEngaged;
    private Gear gear;

    /**
     * This method sets all the initial values of the private fields
     *
     */
    public MinivanSlidingDoor() {
        doorOpen = false;
        doorLocked = false;
        childSafteyEngaged = false;
        gear = Gear.PARK;
    }

    /**
     *
     * get the current state of the door (open or closed).
     * 
     * @return true or false
     */
    public boolean isOpen() {
        if (doorOpen)
            return true;
        else
            return false;
    }

    /**
     * get the current state of the door
     * 
     * @return boolean (True ro false)
     */
    public boolean isLocked() {
        if (doorLocked)
            return true;
        else
            return false;
    }

    /**
     * get the current state of the childSafe (enabled or disabled).
     * 
     * @return whether the childSafe is activated or not
     */
    public boolean isChildSafe() {
        return childSafteyEngaged;
    }

    /**
     * get the current state of the gear (PARK, REVERSE, NEUTRAL, DRIVE).
     * 
     * @return the state of the gear
     */
    public Gear getGear() {
        return gear;
    }

    /**
     * This method sets the gear to PARK, REVERSE, NEUTRAL, or DRIVE.
     * 
     * @param requestedGear is parameter for requesting a gear
     * @return Result of the gear state.
     */
    public Result setGear(Gear requestedGear) {
        if (requestedGear == gear) {
            return Result.NO_ACTION;
        } else if (requestedGear == Gear.PARK) {
            gear = Gear.PARK;
            return Result.GEAR_PARKED;
        } else if (gear == Gear.PARK) {
            return processGearEqualsPark(requestedGear);
        } else {
            return processGearNotEqualsPark(requestedGear);
        }
    }

    /**
     * This method allows child safe to be engage or disengage
     * 
     * @param requestedEngage is a parameter for whether to engage child safe or not
     * @return Result the state of the child safe or whether if it is inaccessible.
     */
    public Result setChildSafe(boolean requestedEngage) {
        if (!doorOpen) {
            return Result.CHILDSAFE_INACCESSIBLE;
        } else if (requestedEngage == childSafteyEngaged) {
            return Result.NO_ACTION;
        } else if (requestedEngage) {
            childSafteyEngaged = true;
            return Result.CHILDSAFE_ENGAGED;
        } else {
            childSafteyEngaged = false;
            return Result.CHILDSAFE_DISENGAGED;
        }
    }

    /**
     * This method pushes the locked button. Nothing will happen if the door is already locked. The
     * door will be locked once the button is pushed.
     *
     * @return Result that the door is locked or no action if it is already locked.
     */
    public Result pushLockButton() {
        if (doorLocked) {
            return Result.NO_ACTION;
        }
        doorLocked = true;
        return Result.DOOR_LOCKED;
    }

    /**
     * This method pushes the unlocked button. Nothing will happen if the door is already unlocked.
     * The door will be unlocked once the button is pushed.
     *
     * @return Result that the door is unlocked or no action if it is already unlocked.
     */
    public Result pushUnlockButton() {
        if (doorLocked) {
            doorLocked = false;
            return Result.DOOR_UNLOCKED;
        }
        return Result.NO_ACTION;
    }

    /**
     * This method pushes the dash board button.
     * 
     * The user can choose the door to be opened or closed by using the dash board button.
     *
     * @param direction is a parameter that decides the door to be opened or closed
     * @return the door request whether they want it opened or closed
     */
    public Result pushDashboardButton(Direction direction) {
        return processDoorRequest(direction);
    }

    /**
     * This method pushes the inside handle button.
     * 
     * The user can choose the door to be opened or closed by using the inside handle button. The
     * door can not be opened if the child safe is engaged.
     *
     * @param direction is a parameter that decides the door to be opened or closed
     * @return the process to be opened or closed from inside
     */
    public Result pushInsideHandle(Direction direction) {
        if (direction == Direction.OPEN) {
            return processOpenInside();
        }
        if (direction == Direction.CLOSE) {
            return processClose();
        }
        return Result.INVALID_PARAMETER;
    }

    /**
     * This method pushes the outside handle button.
     * 
     * The user can choose the door to be opened or closed by using the outside handle button.
     *
     * @param direction is a parameter that decides the door to be opened or closed
     * @return processDoorRequest method.
     */
    public Result pushOutsideHandle(Direction direction) {
        return processDoorRequest(direction);
    }

    /**
     * This helper method removes redundancy on pushDashboardButton and pushOutsideHandle methods.
     * User chooses whether they want the door to be opened or closed.
     * 
     * @param direction is a parameter that decides the door to be opened or closed
     * @return The process to be opened or closed.
     */
    private Result processDoorRequest(Direction direction) {
        if (direction == Direction.OPEN) {
            return processOpen();
        }
        if (direction == Direction.CLOSE) {
            return processClose();
        }
        return Result.INVALID_PARAMETER;
    }

    /**
     * This is a helper method that decides whether the door can be opened or not.
     * 
     * @return Result saying the why the door can not be opened or if it will be opened.
     */
    private Result processOpen() {
        if (gear != Gear.PARK) {
            return Result.OPEN_REFUSED_GEAR;
        }
        if (doorLocked) {
            return Result.OPEN_REFUSED_LOCK;
        }
        if (!doorOpen) {
            doorOpen = true;
            return Result.DOOR_OPENED;
        }
        {
            return Result.NO_ACTION;
        }
    }

    /**
     * This is a helper method that decides whether the door can be closed or not.
     * 
     * @return Result that door will be closed and no action is done if the door is already closed.
     */
    private Result processClose() {
        if (!doorOpen) {
            return Result.NO_ACTION;
        }
        doorOpen = false;
        return Result.DOOR_CLOSED;
    }

    /**
     * This is a helper method that decides whether the door can be opened or not from the inside.
     * 
     * @return The processOpen method and open refused child safe statement.
     */
    private Result processOpenInside() {
        if (!childSafteyEngaged) {
            return processOpen();
        }
        return Result.OPEN_REFUSED_CHILDSAFE;
    }

    /**
     * This is a helper method for PARK gear to be released.
     * 
     * @param requestedGear is a gear that is requested either PARK, REVERSE, NEUTRAL, and DRIVE
     * @return Result the state of the gear
     */
    private Result processGearEqualsPark(Gear requestedGear) {
        if (requestedGear == Gear.REVERSE) {
            gear = Gear.REVERSE;
            return Result.GEAR_RELEASED;
        }
        if (requestedGear == Gear.NEUTRAL) {
            gear = Gear.NEUTRAL;
            return Result.GEAR_RELEASED;
        }
        if (requestedGear == Gear.DRIVE) {
            gear = Gear.DRIVE;
            return Result.GEAR_RELEASED;
        } else {
            return Result.INVALID_PARAMETER;
        }
    }

    /**
     * This is a helper method for Non PARK gear to be changed.
     * 
     * @param requestedGear is a gear that is requested either PARK, REVERSE, NEUTRAL, and DRIVE
     * @return Result the state of the gear
     */
    private Result processGearNotEqualsPark(Gear requestedGear) {
        if (requestedGear == Gear.REVERSE) {
            gear = Gear.REVERSE;
            return Result.GEAR_CHANGED;
        }
        if (requestedGear == Gear.NEUTRAL) {
            gear = Gear.NEUTRAL;
            return Result.GEAR_CHANGED;
        }
        if (requestedGear == Gear.DRIVE) {
            gear = Gear.DRIVE;
            return Result.GEAR_CHANGED;
        } else {
            return Result.INVALID_PARAMETER;
        }
    }
}

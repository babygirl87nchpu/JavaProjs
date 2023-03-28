package edu.vt.cs5044;

public class MinivanSlidingDoorTester {

    public static void main(String[] args) {

        // Test 1
        MinivanSlidingDoor minivanDoor = new MinivanSlidingDoor();

        System.out.println("Testing newly constructed minivan door object");
        // tested
        System.out.println("Open? Expected false, Actual: " + minivanDoor.isOpen());
        System.out.println("Locked? Expected false, Actual: " + minivanDoor.isLocked());
        System.out.println("ChildSafe? Expected false, Actual: " + minivanDoor.isChildSafe());
        System.out.println("Gear? Expected Park, Actual: " + minivanDoor.getGear());
        System.out.println("");

        Result res = minivanDoor.pushLockButton();
        // tested
        System.out.println("result: expect: DOOR_LOCKED; actual: " + res);
        System.out.println("locked? expect: true; actual: " + minivanDoor.isLocked());

        //tested
        System.out.println();
        System.out.println("Lock the door");

        res = minivanDoor.pushLockButton(); // mutator

        //tested
        System.out.println("result: expect:  + NO_ACTION; actual: " + res);
        System.out.println("locked? expect: true; actual: " + minivanDoor.isLocked());

        //tested
        System.out.println();
        System.out.println("Unlock the LOCKED door");

        res = minivanDoor.pushUnlockButton(); // mutator

        //tested
        System.out.println("result: expect: DOOR_UNLOCKED; actual: " + res);
        System.out.println("locked? expect: false; actual: " + minivanDoor.isLocked());

        System.out.println();
        System.out.println("Unlock the UNLOCKED door");

        //tested
        res = minivanDoor.pushUnlockButton(); // mutator

        
        //eseted
        System.out.println("result: expect:  + NO_ACTION; actual: " + res);
        System.out.println("locked? expect: false; actual: " + minivanDoor.isLocked());

        System.out.println();
        System.out.println("Open door by pushing the dashboard button while it is on gear PARK");

        //tested
        minivanDoor.setGear(Gear.PARK);
        res = minivanDoor.pushDashboardButton(Direction.OPEN);

        System.out.println("result: expect: true; actual: " + minivanDoor.isOpen());

        MinivanSlidingDoor doorGear = new MinivanSlidingDoor();
        System.out.println();
        System.out.println("Open door by pushing the dashboard button while it is on gear DRIVE");

        doorGear.setGear(Gear.DRIVE);
        res = doorGear.pushDashboardButton(Direction.OPEN);

        System.out.println("result: expect: false; actual: " + doorGear.isOpen());

        //tested
        System.out.println();
        System.out.println("setup[Closed,Locked,CSD,Release] then open by INSIDE");
        MinivanSlidingDoor msd_inside = new MinivanSlidingDoor();
        msd_inside.pushLockButton();
        msd_inside.setGear(Gear.DRIVE);
        res = msd_inside.pushInsideHandle(Direction.OPEN);
        System.out.println("Result Statement expected: " + Result.OPEN_REFUSED_GEAR+ "actual:" + res);

        System.out.println();
        System.out.println("setup[Closed,Locked,CSD,Release] then open by INSIDE, then close; event incorrect");
        msd_inside.pushLockButton();
        msd_inside.setGear(Gear.DRIVE);
        msd_inside.pushInsideHandle(Direction.OPEN);
        res = msd_inside.pushInsideHandle(Direction.CLOSE);
        System.out.println("Result Statement expected: " + Result.NO_ACTION + " actual:" + res);

        //testing
        System.out.println();
        System.out.println("Open the inside door while child safe is engaged");
        MinivanSlidingDoor eChildSafe = new MinivanSlidingDoor();
        eChildSafe.pushInsideHandle(Direction.OPEN);
        eChildSafe.setChildSafe(true);
        res = eChildSafe.pushInsideHandle(Direction.OPEN);
        System.out.println("Result: expect: true; actual: " + eChildSafe.isChildSafe());
        System.out.println("Result: expect: OPEN_REFUSED_CHILDSAFE ; actual:" + res);

        //tested
        System.out.println();
        System.out.println("Disengage child safe");
        res = eChildSafe.setChildSafe(false);
        System.out.println("Result: expect: CHILDSAFE_DISENGAGED ; actual:" + res);

    }

}

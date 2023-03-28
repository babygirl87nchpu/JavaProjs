package edu.vt.cs5044;

//CHECKSTYLE:OFF
@SuppressWarnings("javadoc")
public class ShuttleBatteryMonitorTester {

    public static void main(String... args) {
        System.out.println("P1 Tester (Spring 2023)");

        ShuttleBatteryMonitor sbm = new ShuttleBatteryMonitor(9, 8, 12, 4500);
        sbm.travelTo(5);
        sbm.loadPassengers(3);
        sbm.travelTo(9);
        sbm.loadPassengers(1);
        sbm.loadPassengers(1);
        sbm.travelTo(18);

        System.out.println();
        System.out.println("Sample Test Case - Part 1");
        System.out.println("sbm(9,8,12,4500)t(5)l(3)t(9)l(1)l(1)t(18)");
        System.out.println("Loc     expected: 18;    actual: " + sbm.getLocation());
        System.out.println("#Pass   expected: 5;     actual: " + sbm.getPassengerCount());
        System.out.println("%Charge expected: 88.2;  actual: " + sbm.getChargeRemaining());
        System.out.println("U/T     expected: 176.0; actual: " + sbm.getAverageUsagePerTrip());
        System.out.println("#Trips  expected: 22;    actual: " + sbm.getEstimatedTripsRemaining());

        sbm.loadPassengers(-4);
        sbm.travelTo(-3, 5, 9);
        sbm.recharge();
        sbm.travelTo(-3);
        sbm.loadPassengers(-1);

        System.out.println();
        System.out.println("Sample Test Case - Part 2 ");
        System.out.println("sbm(9,8,12,4500)t(5)l(3)t(9)l(1)l(1)t(18)l(-4)t(-3,5,9)r()t(-3)l(-1)");
        System.out.println("Loc expected: -3; actual: " + sbm.getLocation());
        System.out.println("#Pass expected: 0; actual: " + sbm.getPassengerCount());
        System.out.println("%Charge expected: 99.4; actual: " + sbm.getChargeRemaining());
        System.out.println("U/T expected: 128.5; actual: " + sbm.getAverageUsagePerTrip());
        System.out.println("#Trips expected: 34; actual: " + sbm.getEstimatedTripsRemaining());

        // TODO: Place your additional test cases here...
        sbm.travelTo(4);
        sbm.loadPassengers(4);
        sbm.travelTo(8);
        sbm.loadPassengers(2);
        sbm.loadPassengers(3);
        sbm.travelTo(20);

        System.out.println();
        System.out.println("Sample Test Case 2 - Part 1");
        System.out.println("sbm(6,7,18,4800)t(5)l(3)t(9)l(1)l(1)t(18)");
        System.out.println("Loc     expected: 20;    actual: " + sbm.getLocation());
        System.out.println("#Pass   expected: 9;     actual: " + sbm.getPassengerCount());
        System.out.println("%Charge expected: ?;  actual: " + sbm.getChargeRemaining());
        System.out.println("U/T     expected: ?; actual: " + sbm.getAverageUsagePerTrip());
        System.out.println("#Trips  expected: ?;    actual: " + sbm.getEstimatedTripsRemaining());

        sbm.loadPassengers(-2);
        sbm.travelTo(4);
        sbm.recharge();
        sbm.travelTo(6);
        sbm.loadPassengers(-2);

        System.out.println();
        System.out.println("Sample Test Case 2 - Part 2 ");
        System.out.println("sbm(6,7,18,4800)t(5)l(3)t(9)l(1)l(1)t(18)l(-2)t(4)r()t(6)l(-2)");
        System.out.println("Loc expected: 6; actual: " + sbm.getLocation());
        System.out.println("#Pass expected: 5; actual: " + sbm.getPassengerCount());
        System.out.println("%Charge expected: ?; actual: " + sbm.getChargeRemaining());
        System.out.println("U/T expected: ?; actual: " + sbm.getAverageUsagePerTrip());
        System.out.println("#Trips expected: ?; actual: " + sbm.getEstimatedTripsRemaining());

        sbm.travelTo(4);
        sbm.loadPassengers(1);
        sbm.travelTo(4);
        sbm.loadPassengers(3);
        sbm.loadPassengers(4);
        sbm.travelTo(22);

        System.out.println();
        System.out.println("Sample Test Case 3 - Part 1");
        System.out.println("sbm(6,7,10,4500)t(4)l(1)t(4)l(3)l(4)t(22)");
        System.out.println("Loc     expected: 22;    actual: " + sbm.getLocation());
        System.out.println("#Pass   expected: 5;     actual: " + sbm.getPassengerCount());
        System.out.println("%Charge expected: 88.2;  actual: " + sbm.getChargeRemaining());
        System.out.println("U/T     expected: 176.0; actual: " + sbm.getAverageUsagePerTrip());
        System.out.println("#Trips  expected: 22;    actual: " + sbm.getEstimatedTripsRemaining());

        sbm.loadPassengers(-3);
        sbm.travelTo(-3, 5, 8);
        sbm.recharge();
        sbm.travelTo(4);
        sbm.loadPassengers(1);

        System.out.println();
        System.out.println("Sample Test Case 3 - Part 2 ");
        System.out.println("sbm(9,8,12,4500)t(5)l(3)t(9)l(1)l(1)t(18)l(-3)t(-3,5,8)r()t(4)l(1)");
        System.out.println("Loc expected: 4; actual: " + sbm.getLocation());
        System.out.println("#Pass expected: 1; actual: " + sbm.getPassengerCount());
        System.out.println("%Charge expected: 99.4; actual: " + sbm.getChargeRemaining());
        System.out.println("U/T expected: 128.5; actual: " + sbm.getAverageUsagePerTrip());
        System.out.println("#Trips expected: 34; actual: " + sbm.getEstimatedTripsRemaining());
    }
}

package ua.com.juja.core.week4.part2.lab30Liner;

import java.util.Arrays;

public class Liner extends AbstractShip {

    private int passengers;
    public static final float DEFAULT_RENTAL = 1000;

    public Liner(String name, float length, float width, float displacement, int passengers) {
        super(name, length, width, displacement);
        this.passengers = passengers;
    }

    @Override
    public float calculatePayment() {
        return passengers * DEFAULT_RENTAL;
    }

    public float calculatePayment(float rentTax) {
        if (rentTax > 0) {
            return passengers * rentTax;
        } else {
            return calculatePayment();
        }
    }
}

abstract class AbstractShip implements Comparable<AbstractShip> {
    private String name;
    private float length;
    private float width;
    private float displacement;

    public AbstractShip(String name, float length, float width, float displacement) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.displacement = displacement;
    }

    public abstract float calculatePayment();

    public String toPrint() {
        return "Name=" + name +
                "Length=" + length +
                "Width=" + width +
                "Displacement=" + displacement;
    }

    public int compareTo(AbstractShip compareShip) {

        float compareRental = compareShip.calculatePayment();

        return (int)(this.calculatePayment() - compareRental);
    }

    public String getName() {
        return name;
    }
}


class Tanker extends AbstractShip {
    private float volume;
    public static final float DEFAULT_RENTAL = 250;

    public Tanker(String name, float length, float width, float displacement, float volume) {
        super(name, length, width, displacement);
        this.volume = volume;
    }

    @Override
    public float calculatePayment() {
        return volume * DEFAULT_RENTAL;
    }

    public float calculatePayment(float rentTax) {
        if (rentTax > 0) {
            return volume * rentTax;
        } else {
            return calculatePayment();
        }
    }
}

class Cargo extends AbstractShip {
    private float tonnage;
    public static final float DEFAULT_RENTAL=550;

    public Cargo(String name, float length, float width, float displacement, float tonnage) {
        super(name, length, width, displacement);
        this.tonnage = tonnage;
    }

    @Override
    public float calculatePayment() {
        return tonnage * DEFAULT_RENTAL;
    }

    public float calculatePayment(float rentTax) {
        if (rentTax > 0) {
            return tonnage * rentTax;
        } else {
            return calculatePayment();
        }
    }
}

class OdessaSeaPort {

    public static String sortSumPaymentAsc(AbstractShip[] arrayShips) {
        String result = "";

        if ( arrayShips == null ) {
            return result;
        }

        Arrays.sort(arrayShips);

        for ( int i = 0; i < arrayShips.length; i++ ) {
            result += arrayShips[i].getName() + "=" + arrayShips[i].calculatePayment();
        }

        return result;
    }

    public static void main(String[] args) {

        String testLinerName = "TestLinerName";
        float testLinerLength = 1000;
        float testLinerWidth = 1000;
        float testLinerDisplacement = 1000;
        int testLinerPassengers = 100;
        String testCargoName = "TestCargoName";
        float testCargoLength = 1000;
        float testCargoWidth = 1000;
        float testCargoDisplacement = 1000;
        float testCargoTonnage = 100;
        String testTankerName = "TestTankerName";
        float testTankerLength = 1000;
        float testTankerWidth = 1000;
        float testTankerDisplacement = 1000;
        float testTankerVolume = 100;

        String expectedSortedShipString = "TestTankerName=25000.0TestCargoName=55000.0TestLinerName=100000.0";

        // check arrays length > 0
        AbstractShip[] arrayShips = {
                new Liner(testLinerName, testLinerLength, testLinerWidth, testLinerDisplacement, testLinerPassengers),
                new Cargo(testCargoName, testCargoLength, testCargoWidth, testCargoDisplacement, testCargoTonnage),
                new Tanker(testTankerName, testTankerLength, testTankerWidth, testTankerDisplacement, testTankerVolume)
        };

        String actualSortedShipString = OdessaSeaPort.sortSumPaymentAsc(arrayShips);

        if (!(actualSortedShipString.equals(expectedSortedShipString)))
            throw new AssertionError("Expected to be printed " + expectedSortedShipString + " but found " + actualSortedShipString);

        System.out.print("OK");
    }
}


package ua.com.juja.core.week4.part2.lab29SeaPortQueue;

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

abstract class AbstractShip {
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

    public String getName() {
        return name;
    }
}
interface SeaPortQueue {
    public final static int LENGTH_QUEUE_SHIP = 3;
    public int addShipToEndQueue(AbstractShip ship);
    public int removeShipFromBeginQueue();
    public String printQueueShip();
}

class OdessaSeaPort implements SeaPortQueue {
    private static final int NO_SHIP_IN_ARRAY = -1;
    private int indexShipInPort = NO_SHIP_IN_ARRAY;
    private AbstractShip[] arrayShip = new AbstractShip[LENGTH_QUEUE_SHIP];


    @Override
    public int addShipToEndQueue(AbstractShip ship) {
        if ( indexShipInPort == LENGTH_QUEUE_SHIP - 1 ) {
            return -1;
        }

        indexShipInPort += 1;
        arrayShip[indexShipInPort] = ship;

        return indexShipInPort;
    }

    @Override
    public int removeShipFromBeginQueue() {
        if ( indexShipInPort < 0 ) {
            return -1;
        }

        for ( int i = 0; i < indexShipInPort; i++ ) {
            arrayShip[i] = arrayShip[i + 1];
        }
        arrayShip[indexShipInPort] = null;

        indexShipInPort -= 1;

        return 1;
    }

    @Override
    public String printQueueShip() {
        String result = "";

        if ( indexShipInPort < 0 ) {
            return "QueueEmpty";
        }

        for ( int i = 0; i <= indexShipInPort; i++ ) {
            result += "{" + arrayShip[i].toPrint() + "};";
        }

        return result;
    }

    public static void main(String[] args) {
        //check print empty queue
        String testLinerName = "NameTestLiner";
        float testLinerLength = 1000;
        float testLinerWidth = 1000;
        float testLinerDisplacement = 1000;
        int testLinerPassengers = 100;
        String testCargoName = "NameTestCargo";
        float testCargoLength = 1000;
        float testCargoWidth = 1000;
        float testCargoDisplacement = 1000;
        float testCargoTonnage = 100;
        String testTankerName = "NameTestTanker";
        float testTankerLength = 1000;
        float testTankerWidth = 1000;
        float testTankerDisplacement = 1000;
        float testTankerVolume = 100;

        String expectedResultPrintShipQueueAfterRemove = "{Name=NameTestLinerLength=1000.0Width=1000.0Displacement=1000.0};{Name=NameTestTankerLength=1000.0Width=1000.0Displacement=1000.0};";

        int expectedSuccessfulStatusRemoveShipInQueue = 1;

        AbstractShip testLiner = new Liner(testLinerName, testLinerLength, testLinerWidth, testLinerDisplacement, testLinerPassengers);
        AbstractShip testCargo = new Cargo(testCargoName, testCargoLength, testCargoWidth, testCargoDisplacement, testCargoTonnage);
        AbstractShip testTanker = new Tanker(testTankerName, testTankerLength, testTankerWidth, testTankerDisplacement, testTankerVolume);

        OdessaSeaPort odessaSeaPort = new OdessaSeaPort();


        odessaSeaPort.addShipToEndQueue(testCargo);
        odessaSeaPort.addShipToEndQueue(testLiner);
        odessaSeaPort.addShipToEndQueue(testTanker);


        int actualSuccessfulStatusRemoveShipInQueue = odessaSeaPort.removeShipFromBeginQueue();
        if (actualSuccessfulStatusRemoveShipInQueue != expectedSuccessfulStatusRemoveShipInQueue)
            throw new AssertionError("Successful status remove ship in queue 1 but found " + actualSuccessfulStatusRemoveShipInQueue);

        String actualPrintShipQueueAfterRemove = odessaSeaPort.printQueueShip();
        if(!(actualPrintShipQueueAfterRemove.equals(expectedResultPrintShipQueueAfterRemove)))
            throw new AssertionError("Expected to be printed " + expectedResultPrintShipQueueAfterRemove + " but found " + actualPrintShipQueueAfterRemove);


        System.out.print("OK");
    }
}


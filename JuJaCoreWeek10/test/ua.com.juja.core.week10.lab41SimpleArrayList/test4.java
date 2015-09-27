package ua.com.juja.core.week10.lab41SimpleArrayList;

public class test4 {
    public static void main(String[] args) {
        //first array list
        SimpleArrayList<Integer> firstSimpleArrayList = new SimpleArrayList<>();

        //second array list
        SimpleArrayList<Integer> secondSimpleArrayList = new SimpleArrayList<>();


        //check
        if (!(firstSimpleArrayList.hashCode()==secondSimpleArrayList.hashCode()))
            throw new AssertionError("Two empty simpleArrayList with identical type should be equals");


        System.out.print("OK");
    }
}

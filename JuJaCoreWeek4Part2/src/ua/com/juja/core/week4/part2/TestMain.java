package ua.com.juja.core.week4.part2;

public class TestMain {
    //не компилируется: println "не видит" k (других ошибок нет) ?????? +
    //не компилируется: inner класс не может содержать статических членов + возможно другие ошибки
   /* static int k;
    class X {
        int p;
        static class Y {
            public void f() {
                System.out.println(k);
                System.out.println(p);
            }
        }
    }
*/
    //


    public static void f() {
        Object ref = new Object() {};
    }

    public static void main(String[] args) {
        //Outer.Inner ref = new Outer().new Inner();
    }
}

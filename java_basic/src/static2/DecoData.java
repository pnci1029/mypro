package static2;

public class DecoData {
    private int instanceValue;
    private static int staticValue;

    public static void staticCall() {
//        instancevalue++;      compile error
//        instanceMethod();      compile error

        staticMethod();
        staticValue++;
    }

    public void instanceMethod() {
        System.out.println("instanceValue = " + instanceValue);
    }

    public static void staticMethod() {
        System.out.println("staticValue = " + staticValue);
    }
}

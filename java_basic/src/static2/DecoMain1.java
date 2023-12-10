package static2;

import static1.Data2;

public class DecoMain1 {
    public static void main(String[] args) {
        Deco1 deco1 = new Deco1();
        String hello = deco1.deco1("hello");
        System.out.println(hello);

        String hello2 = Deco2.deco1(hello);
        System.out.println(hello2);
    }
}

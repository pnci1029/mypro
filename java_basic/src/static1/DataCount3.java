package static1;

public class DataCount3 {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Data2 data1 = new Data2("test",counter);
        System.out.println("data1.count = " + counter.count);

        Data2 data2 = new Data2("test",counter);
        System.out.println("data1.count = " + counter.count);

        Data2 data3 = new Data2("test",counter);
        System.out.println("data1.count = " + counter.count);

    }
}

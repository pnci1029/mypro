package static2;

public class Calculate {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        // private 으로 인스턴스 생성 차단 (메모리 누수)
//        MathUtils mathUtils = new MathUtils();
        System.out.println("MathUtils.sum(values) = "+ MathUtils.sum(values));
        System.out.println("MathUtils.average(values) = " + MathUtils.average(values));
        System.out.println("MathUtils.min(values) = " + MathUtils.min(values));
        System.out.println("MathUtils.max(values) = " + MathUtils.max(values));
    }
}

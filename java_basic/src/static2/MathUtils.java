package static2;

import java.util.Arrays;

public class MathUtils {
    private MathUtils(){}

    public static int sum(int[] values) {
        return Arrays.stream(values).sum();
    }

    public static int average(int[] values) {
        return sum(values) / values.length;
    }

    public static int min(int[] values) {
        int min = 10000;
        for (int value : values) {
            min = Math.min(value, min);
        }
        return min;
    }

    public static int max(int[] values) {
        int max = 0;
        for (int value : values) {
            max = Math.min(value, max);
        }
        return max;
    }
}

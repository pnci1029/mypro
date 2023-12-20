package com.study.bookprac.part2;

public class Item34 {
    // before
    public static class before{
        public static final int APPLE_FUJI = 0;
        public static final int APPLE_PIPPIN = 1;
        public static final int APPLE_GRANNY_SMITH = 2;

        public static final int ORANGE_NAVEL = 0;
        public static final int ORANGE_TEMPLE = 1;
        public static final int ORANGE_BLOOD = 2;
    }

    // after
    public static class after{
        public enum APPLE {FUJI, PIPPIN, GRANNY_SMITH}
        public enum ORANGE{NAVEL, TEMPLE, BLOOD}
    }
}

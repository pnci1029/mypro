package com.study.bookprac.part2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Item36 {

    @NoArgsConstructor @AllArgsConstructor
    public static class Before{
        public static final int StyleBold = 1 << 0; // 1
        public static final int StyleItalic = 1 << 1; // 2
        public static final int StyleUnderLine = 1 << 2; // 4
        public static final int StyleStrikethrough = 1 << 3; // 8
    }

    public static class After{
        public enum Style{Bold,Italic,UnderLine, StrikeThrough,}

    }
}

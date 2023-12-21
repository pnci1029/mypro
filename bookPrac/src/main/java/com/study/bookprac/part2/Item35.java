package com.study.bookprac.part2;

public class Item35 {
    // before
    public enum before{
        Solo, Duet, Quartet, Quintet, Sextet, Septet, Octet, Nonet, Dectet;

        public int numberOfMusicians() {
            return ordinal() + 1;
        }
    }

    // after
    public enum after {
        Solo(1), Duet(2), Quartet(3), Quintet(4), Sextet(5), Septet(6), Octet(7), Nonet(8), Dectet(9),
        ;

        private final int numberOfMusicians;
        after(int size) {
            this.numberOfMusicians = size;
        }

        public int numberOfMusicians() {
            return numberOfMusicians();
            }
        }
}

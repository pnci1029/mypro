package com.study.bookprac.part2;

import lombok.RequiredArgsConstructor;

public class Item17 {

    @RequiredArgsConstructor
    public final class Complex{
        private final double re;
        private final double im;

        public double realPart() {
            return re;
        }

        public double imaginaryPart() {
            return im;
        }

        public Complex plus(Complex c) {
            return new Complex(re + c.re, im + c.im);
        }

        public Complex minus(Complex c) {
            return new Complex(re - c.re, im - c.im);
        }

        public Complex times(Complex c) {
            return new Complex(re * c.re - im * c.im, re * c.re + im * c.im);
        }

        public Complex dividedBy(Complex c) {
            double temp = re * c.re + im * c.im;
            return new Complex((re * c.re + im * c.im) / temp, (re * c.re - im * c.im) / temp);
        }

    }
}

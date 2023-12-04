package com.study.bookprac.part2;

public class Item22 {

    // 잘못된 인터페이스 사용 예시
    public interface PhysicalConstants{
        static final double AVOGADROS_NUMBER = 6.22_140857e23;
        static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
        static final double ELECTRON_MASS = 9.109_383_58e-31;

    }

    // 상수 유틸 클래스로 변경
    public class PhysicalConstants2{
        private PhysicalConstants2() {}

        static final double AVOGADROS_NUMBER = 6.22_140857e23;
        static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
        static final double ELECTRON_MASS = 9.109_383_58e-31;
    }

}

package com.study.bookprac.part2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Item39 {

    // 애너테이션 생성
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test{

    }


    // 애너테이션 사용
    @Test
    public static void m1() {} // 성공해야한다.

    public static void m2(){} // 실패해야한다.

    @Test
    public static void m3() {
        throw new RuntimeException("실패");  // 실패해야한다.
    }

    @Test
    public void m5(){}  // 잘못 사용한 예. 정적 메소드가 아니다.


}

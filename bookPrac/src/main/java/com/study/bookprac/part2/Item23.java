package com.study.bookprac.part2;

public class Item23 {

    // 개선 전
    static class Figure{
        enum Shape {Rectangle, Circle,}

        //태그 필드
        Shape shape;

        //사각형일때만 쓰이는 변수
        double length;
        double width;

        //원일때만 쓰이는 변수
        double radius;

        //원 용 생성자
        Figure(double radius) {
            shape = Shape.Circle;
            this.radius = radius;
        }

        //사각형 용 생성자
        Figure(double length, double width) {
            shape = Shape.Rectangle;
            this.length = length;
            this.width = width;
        }
    }


    // 개선 후
    abstract static class Figures{
        abstract double area();
    }

    static class Circle extends Figures {
        double radius;

        Circle(double radius)
        {
            this.radius = radius;
        }
        @Override
        double area() {
            return Math.PI * (radius * radius);
        }
    }

    static class Rectangle extends Figures{
        double length;
        double width;

        @Override
        double area() {
            return length * width;
        }
    }

}

package cleancode.minesweeper.tobe;

public class Cell {
    private final String sign;

    private Cell(String sign) {
        this.sign = sign;
    }

    // 정적 팩토리 메서드 패턴
    public static Cell of(String sign) {
        return new Cell(sign);
    }
}
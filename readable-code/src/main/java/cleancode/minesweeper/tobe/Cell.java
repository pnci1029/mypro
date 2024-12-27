package cleancode.minesweeper.tobe;

public class Cell {
    private final String sign;
    private static final String FLAG_SIGN = "⚑";
    private static final String MINE_SIGN = "☼";
    private static final String OPEND_LAND_SIGN = "■";
    private static final String UNOPEND_LAND_SIGN = "□";

    private Cell(String sign) {
        this.sign = sign;
    }

    // 정적 팩토리 메서드 패턴
    public static Cell of(String sign) {
        return new Cell(sign);
    }

    public static Cell ofFlag() {
        return of(FLAG_SIGN);
    }

    public static Cell ofMine() {
        return of(MINE_SIGN);
    }

    public static Cell ofOpenedLand() {
        return of(OPEND_LAND_SIGN);
    }

    public static Cell ofClosed() {
        return of(UNOPEND_LAND_SIGN);
    }

    public static Cell ofNearbyLandmineCount(int count) {
        return of(String.valueOf(count));
    }

    public String getSign() {
        return this.sign;
    }

    public boolean isClosed() {
        return UNOPEND_LAND_SIGN.equals(this.sign);
    }

    public boolean doesNotClosed() {
        return !isClosed();
    }
}

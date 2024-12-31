package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String MINE_SIGN = "☼";
    private static final String EMPTY_SIGN = "■";
    private static final String UNCHECKED_SIGN = "□";

    private final String sign;
    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(String sign, int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.sign = sign;
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    // 정적 팩토리 메서드 패턴
    public static Cell of(String sign, int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(sign, nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return of("", 0, false, false, false);
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public static Cell ofFlag() {
        return of(FLAG_SIGN);
    }

    public static Cell ofMine() {
        return of(MINE_SIGN);
    }

    public static Cell ofOpenedLand() {
        return of(EMPTY_SIGN);
    }

    public static Cell ofClosed() {
        return of(UNCHECKED_SIGN);
    }

    public static Cell ofNearbyLandmineCount(int count) {
        return of(String.valueOf(count));
    }

    public String getSign() {
        return this.sign;
    }

    public boolean isClosed() {
        return UNCHECKED_SIGN.equals(this.sign);
    }

    public boolean doesNotClosed() {
        return !isClosed();
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }
}

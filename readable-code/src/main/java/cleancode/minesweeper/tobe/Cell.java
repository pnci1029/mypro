package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String MINE_SIGN = "☼";
    private static final String EMPTY_SIGN = "■";
    private static final String UNCHECKED_SIGN = "□";

    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    // 정적 팩토리 메서드 패턴
    public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public static Cell create() {
        return of( 0, false, false, false);
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

    public String getSign() {
        // 열린경우
        if (isOpened) {
            if (isLandMine) {
                return MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }

        // 닫힌경우
        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCount != 0;
    }
}

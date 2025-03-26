package cleancode.minesweeper.tobe;

import java.util.Random;

public class GameBoard {
    private final Cell[][] board;
    public static final int MINE_COUNT = 10;

    public GameBoard(int rowSize, int columnSize) {
        board = new Cell[rowSize][columnSize];
    }

    public void initializeGame() {
        int rowSize = board.length;
        int columnSize = board[0].length;

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                board[row][column] = Cell.create();
            }
        }

        for (int i = 0; i < MINE_COUNT; i++) {
            int landMineColumn = new Random().nextInt(columnSize);
            int landMineRow = new Random().nextInt(rowSize);
            board[landMineRow][landMineColumn].turnOnLandMine();
        }

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                if (isLandMinedCell(row, column)) {
                    continue;
                }
                int count = countNearbyLandMines(row, column);
                board[row][column].updateNearbyLandMineCount(count);
            }
        }
    }

    public String getSign(int row, int column) {
        Cell cell = board[row][column];
        return cell.getSign();
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColumnSize() {
        return board[0].length;
    }

    public int countNearbyLandMines(int row, int column) {
        int rowSize = board.length;
        int columnSize = board[0].length;

        int count = 0;
        if (row - 1 >= 0 && column - 1 >= 0 && isLandMinedCell(row - 1, column - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMinedCell(row - 1, column)) {
            count++;
        }
        if (row - 1 >= 0 && column + 1 < columnSize && isLandMinedCell(row - 1, column + 1)) {
            count++;
        }
        if (column - 1 >= 0 && isLandMinedCell(row, column - 1)) {
            count++;
        }
        if (column + 1 < columnSize && isLandMinedCell(row, column + 1)) {
            count++;
        }
        if (row + 1 < rowSize && column - 1 >= 0 && isLandMinedCell(row + 1, column - 1)) {
            count++;
        }
        if (row + 1 < rowSize && isLandMinedCell(row + 1, column)) {
            count++;
        }
        if (row + 1 < rowSize && column + 1 < columnSize && isLandMinedCell(row + 1, column + 1)) {
            count++;
        }
        return count;
    }

    private boolean isLandMinedCell(int selectedRowIndex, int selectedColumnIndex) {
        return board[selectedRowIndex][selectedColumnIndex].isLandMine();
    }
}

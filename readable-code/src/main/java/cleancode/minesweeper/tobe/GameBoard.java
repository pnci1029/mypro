package cleancode.minesweeper.tobe;

public class GameBoard {
    private static final Cell[][] board;

    public GameBoard(int rowSize, int columnSize) {
        board = new Cell[rowSize][columnSize];
    }
}

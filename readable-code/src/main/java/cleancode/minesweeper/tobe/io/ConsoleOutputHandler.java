package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.Cell;

public class ConsoleOutputHandler {
    public void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public void showBoard(Cell[][] board) {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < board.length; row++) {
            System.out.printf("%d  ", row + 1);
            for (int column = 0; column < board[0].length; column++) {
                System.out.print(board[row][column].getSign() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

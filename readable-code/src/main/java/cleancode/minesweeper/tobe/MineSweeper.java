package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    public static final int BOARD_COLUMN_SIZE = 8;
    public static final int BOARD_ROW_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);
    private static final Cell[][] BOARD = new Cell[BOARD_ROW_SIZE][BOARD_COLUMN_SIZE];
    public static final int MINE_COUNT = 10;

    private final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();


    private int gameStatus = 0;


    public void run() {
        // 시작 알림 텍스트
        consoleOutputHandler.showGameStartComments();
        // 게임 초기화
        initializeGame();

        while (true) {
            try {

                // 보드를 그리는 부분
                consoleOutputHandler.showBoard(BOARD);

                if (doesUserWinTheGmae()) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    consoleOutputHandler.printGameLosingComment();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserInputFromUser(SCANNER);

                actOnCell(cellInput, userActionInput);
            } catch (GameException e) {
                consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printSimpleMessage("프로그램에 문제가 생겼습니다.");
            }

        }
    }

    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColumnIndex = getSelectedUserIndex(cellInput);
        int selectedRowIndex = getSelectedRowIndex(cellInput);

        if (doesUserChooseToPlantFlag(userActionInput)) {
            BOARD[selectedRowIndex][selectedColumnIndex].flag();
            ;
            checkIfGameIsOver();
            return;
        }

        if (doesUserPlantToOpenCell(userActionInput)) {
            if (isLandMinedCell(selectedRowIndex, selectedColumnIndex)) {
                BOARD[selectedRowIndex][selectedColumnIndex].open();
                changeGameStatusToLose();
                return;
            }

            open(selectedRowIndex, selectedColumnIndex);
            checkIfGameIsOver();
            return;
        }

        System.out.println("잘못된 번호를 선택하셨습니다.");
    }

    private void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private boolean isLandMinedCell(int selectedRowIndex, int selectedColumnIndex) {
        return BOARD[selectedRowIndex][selectedColumnIndex].isLandMine();
    }

    private boolean doesUserPlantToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        return convertRowFrom(cellInputRow);
    }

    private int getSelectedUserIndex(String cellInput) {
        char cellInputColumn = cellInput.charAt(0);
        return convertColumnFrom(cellInputColumn);
    }

    private String getUserInputFromUser(Scanner scanner) {
        consoleOutputHandler.printCommentForSelectingCell();
        return SCANNER.nextLine();
    }

    private String getCellInputFromUser() {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private boolean doesUserWinTheGmae() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOver() {
        boolean isAllChecked = isAllCellChecked();
        if (isAllChecked) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private boolean isAllCellChecked() {
        return Arrays.stream(BOARD)
                .flatMap(Arrays::stream)
                .allMatch(Cell::isChecked);
    }

    private int convertRowFrom(char cellInputRow) {
        int rowIndex = Character.getNumericValue(cellInputRow) - 1;
        if (rowIndex >= BOARD_ROW_SIZE) {
            throw new GameException("잘못된 입력입니다.");
        }
        return rowIndex;
    }

    private int convertColumnFrom(char cellInputColumn) {
        switch (cellInputColumn) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            default:
                throw new GameException("잘못된 입력입니다.");
        }
    }

    private void initializeGame() {
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int column = 0; column < BOARD_COLUMN_SIZE; column++) {
                BOARD[row][column] = Cell.create();
            }
        }

        for (int i = 0; i < MINE_COUNT; i++) {
            int col = new Random().nextInt(10);
            int row = new Random().nextInt(8);
            BOARD[row][col].turnOnLandMine();
        }

        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int column = 0; column < BOARD_COLUMN_SIZE; column++) {
                if (isLandMinedCell(row, column)) {
                    continue;
                }
                int count = countNearbyLandMines(row, column);
                BOARD[row][column].updateNearbyLandMineCount(count);
            }
        }
    }

    private int countNearbyLandMines(int row, int column) {
        int count = 0;
        if (row - 1 >= 0 && column - 1 >= 0 && isLandMinedCell(row - 1, column - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMinedCell(row - 1, column)) {
            count++;
        }
        if (row - 1 >= 0 && column + 1 < 10 && isLandMinedCell(row - 1, column + 1)) {
            count++;
        }
        if (column - 1 >= 0 && isLandMinedCell(row, column - 1)) {
            count++;
        }
        if (column + 1 < 10 && isLandMinedCell(row, column + 1)) {
            count++;
        }
        if (row + 1 < 8 && column - 1 >= 0 && isLandMinedCell(row + 1, column - 1)) {
            count++;
        }
        if (row + 1 < 8 && isLandMinedCell(row + 1, column)) {
            count++;
        }
        if (row + 1 < 8 && column + 1 < 10 && isLandMinedCell(row + 1, column + 1)) {
            count++;
        }
        return count;
    }

    private void open(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 10) {
            return;
        }
        if (BOARD[row][col].isOpened()) {
            return;
        }
        if (isLandMinedCell(row, col)) {
            return;
        }
        BOARD[row][col].open();

        if (BOARD[row][col].hasLandMineCount()) {
            return;
        }
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}

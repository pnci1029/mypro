package cleancode.minesweeper.tobe;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    private static String[][] board = new String[8][10];
    private static Integer[][] landMineCounts = new Integer[8][10];
    private static boolean[][] landMines = new boolean[8][10];
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public static void main(String[] args) {
        // 시작 알림 텍스트
        showGameStartComments();

        Scanner scanner = new Scanner(System.in);

        // 게임 초기화
        initializeGame();

        while (true) {
            // 보드를 그리는 부분
            showBoard();

            if (doesUserWinTheGmae()) {
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (doesUserLoseTheGame()) {
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }
            String cellInput = getCellInputFromUser(scanner);
            String userActionInput = getUserInputFromUser(scanner);

            int selectedColumnIndex = getSelectedUserIndex(cellInput);
            int selectedRowIndex = getSelectedRowIndex(cellInput);

            if (doesUserChooseToPlantFlag(userActionInput)) {
                board[selectedRowIndex][selectedColumnIndex] = "⚑";
                checkIfGameIsOver();
            } else if (doesUserPlantToOpenCell(userActionInput)) {
                if (isLandMinedCell(selectedRowIndex, selectedColumnIndex)) {
                    board[selectedRowIndex][selectedColumnIndex] = "☼";
                    changeGameStatusToLose();
                    continue;
                } else {
                    open(selectedRowIndex, selectedColumnIndex);
                }
                checkIfGameIsOver();
            } else {
                System.out.println("잘못된 번호를 선택하셨습니다.");
            }
        }
    }

    private static void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean isLandMinedCell(int selectedRowIndex, int selectedColumnIndex) {
        return landMines[selectedRowIndex][selectedColumnIndex];
    }

    private static boolean doesUserPlantToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private static boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private static int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        int selectedRowIndex = convertRowFrom(cellInputRow);
        return selectedRowIndex;
    }

    private static int getSelectedUserIndex(String cellInput) {
        char cellInputColumn = cellInput.charAt(0);
        int selectedColumnIndex = convertColumnFrom(cellInputColumn);
        return selectedColumnIndex;
    }

    private static String getUserInputFromUser(Scanner scanner) {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        String userActionInput = scanner.nextLine();
        return userActionInput;
    }

    private static String getCellInputFromUser(Scanner scanner) {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        String cellInput = scanner.nextLine();
        return cellInput;
    }

    private static boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinTheGmae() {
        return gameStatus == 1;
    }

    private static void checkIfGameIsOver() {
        boolean isAllOpened = isAllCellOpened();
        if (isAllOpened) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private static boolean isAllCellOpened() {
        boolean isAllOpened = true;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 10; column++) {
                if (board[row][column].equals("□")) {
                    isAllOpened = false;
                }
            }
        }
        return isAllOpened;
    }

    private static int convertRowFrom(char cellInputRow) {
        int selectedRowIndex = Character.getNumericValue(cellInputRow) - 1;
        return selectedRowIndex;
    }

    private static int convertColumnFrom(char cellInputColumn) {
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
                return -1;
        }
    }

    private static void showBoard() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < 8; row++) {
            System.out.printf("%d  ", row + 1);
            for (int column = 0; column < 10; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 10; column++) {
                board[row][column] = "□";
            }
        }
        for (int i = 0; i < 10; i++) {
            int col = new Random().nextInt(10);
            int row = new Random().nextInt(8);
            landMines[row][col] = true;
        }
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 10; column++) {
                int count = 0;
                if (!isLandMinedCell(row, column)) {
                    if (row - 1 >= 0 && column - 1 >= 0 && isLandMinedCell(row - 1, column - 1)) {
                        count++;
                    }
                    if (row - 1 >= 0 && isLandMinedCell(row - 1, column)) {
                        count++;
                    }
                    if (row - 1 >= 0 && column +1 < 10 && isLandMinedCell(row - 1, column + 1)) {
                        count++;
                    }
                    if (column -1 >= 0 && isLandMinedCell(row, column - 1)) {
                        count++;
                    }
                    if (column +1 < 10 && isLandMinedCell(row, column + 1)) {
                        count++;
                    }
                    if (row + 1 < 8 && column -1 >= 0 && isLandMinedCell(row + 1, column - 1)) {
                        count++;
                    }
                    if (row + 1 < 8 && isLandMinedCell(row + 1, column)) {
                        count++;
                    }
                    if (row + 1 < 8 && column +1 < 10 && isLandMinedCell(row + 1, column + 1)) {
                        count++;
                    }
                    landMineCounts[row][column] = count;
                    continue;
                }
                landMineCounts[row][column] = 0;
            }
        }
    }

    private static void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void open(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 10) {
            return;
        }
        if (!board[row][col].equals("□")) {
            return;
        }
        if (isLandMinedCell(row, col)) {
            return;
        }
        if (landMineCounts[row][col] != 0) {
            board[row][col] = String.valueOf(landMineCounts[row][col]);
            return;
        } else {
            board[row][col] = "■";
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

package tictactoe;

// Poden sortir warnings però perque no utilitzo les funcions d'aquest fitxer o per les 99 lines comentades.
/*
class Board_java {
    private static int gameSize;
    private static String[][] board;
    private final String empty;
    private int moveCount;

    public Board_java(int gameSize) {
        Board_java.gameSize = gameSize;
        empty = "___";
        int moveCount = 0;
        boolean isGameOver = false;
        board = new String[gameSize][gameSize];

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                board[i][j] = empty;
            }
        }
    }

    public void resetBoard() {
        board = new String[gameSize][gameSize];
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                board[i][j] = empty;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (board[i][j].equals(empty)) {
                    System.out.print("|" + board[i][j] + "|");
                } else {
                    System.out.print("| " + board[i][j] + " |");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static class WinMove {
        public static boolean isWinningMove(int row, int column, String move) {
            int gameSize = Board_java.gameSize;
            // Check the row
            for (int i = 0; i < gameSize; i++) {
                if (!board[row][i].equals(move)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return true;
                }
            }
            // Check the column
            for (int i = 0; i < gameSize; i++) {
                if (!board[i][column].equals(move)) {
                    break;
                }
                if (i == gameSize - 1) {
                    return true;
                }
            }
            // Check the diagonal
            if (row == column) {
                for (int i = 0; i < gameSize; i++) {
                    if (!board[i][i].equals(move)) {
                        break;
                    }
                    if (i == gameSize - 1) {
                        return true;
                    }
                }
            }
            // Check reverse diagonal
            if (row + column == gameSize - 1) {
                for (int i = 0; i < gameSize; i++) {
                    if (!board[i][(gameSize - 1) - i].equals(move)) {
                        break;
                    }
                    if (i == gameSize - 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    public boolean Draw() {
        return moveCount == (int) Math.pow(gameSize, 2) - 1;
    }

    public void resetGame() {
        resetBoard();
        boolean isGameOver = false;
        moveCount = 0;
    }
}*/
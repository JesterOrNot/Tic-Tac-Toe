
/**
 * @author Sean Hellum
 * **/
import java.util.Scanner;

public class TicTacToe {
    public static void printBoard(String[][] myBoard) {
        System.out.println("---------");
        for (int i = 0; i <= 2; i++) { // Loop through rows
            for (int m = 0; m <= 2; m++) { // Loop through collmns
                System.out.print("|");
                if (myBoard[i][m] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(myBoard[i][m]);
                }
                System.out.print("|");
                if (m == 2) {
                    System.out.println();
                }
            }
        }
        System.out.println("---------");
    }

    public static boolean isFull(String[][] theboard) {
        int occupiedCount = 0;
        try {
            for (int i = 0; i <= 2; i++) {
                for (int f = 0; f <= 2; f++) {
                    if (theboard[i][f].equals("X") || theboard[i][f].equals("0")) {
                        occupiedCount++;
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) { // This try catch is here to appease Java
            return false;
        }
        if (occupiedCount == 9) {
            return true;
        } else {
            return false;
        }
    }

    public static String[][] newBoard() {
        String[][] myBoard = { { null, null, null }, { null, null, null }, { null, null, null } };
        return myBoard;
    }

    public static String[][] makeMovePlayer1(int x, int y, String[][] theboard) {
        if (theboard[x][y] == null) {
            theboard[x][y] = "X";
        } else {
            System.out.println("Spot taken!");
        }
        return theboard;
    }

    public static String[][] makeMovePlayer2(int x, int y, String[][] theboard) {
        if (theboard[x][y] == null) {
            theboard[x][y] = "0";
        } else {
            System.out.println("Spot taken!");
        }
        return theboard;
    }

    public static int[] getMove() {
        Scanner input = new Scanner(System.in);
        System.out.print("What is the first item?: ");
        int item1 = input.nextInt();
        System.out.print("What is the second item?: ");
        int item2 = input.nextInt();
        int[] myArray = { item2, item1 };
        return myArray;
    }

    public static int isGameOver(String[][] theboard) { // Exit code 0 means player1 wins 1 is for player2 and 2 is game
                                                        // not over
        if ((theboard[0][0] == "X" && theboard[0][1] == "X" && theboard[0][2] == "X")
                || (theboard[0][0] == "X" && theboard[1][0] == "X" && theboard[2][0] == "X")
                || (theboard[1][0] == "X" && theboard[1][1] == "X" && theboard[1][2] == "X")
                || (theboard[2][0] == "X" && theboard[2][1] == "X" && theboard[2][2] == "X")
                || (theboard[0][1] == "X" && theboard[1][1] == "X" && theboard[2][1] == "X")
                || (theboard[0][2] == "X" && theboard[1][2] == "X" && theboard[2][2] == "X")
                || (theboard[0][0] == "X" && theboard[1][1] == "X" && theboard[2][2] == "X")
                || (theboard[0][2] == "X" && theboard[1][1] == "X" && theboard[2][0] == "X")) {
            return 0;
        } else if ((theboard[0][0] == "0" && theboard[0][1] == "0" && theboard[0][2] == "0")
                || (theboard[0][0] == "0" && theboard[1][0] == "0" && theboard[2][0] == "0")
                || (theboard[1][0] == "0" && theboard[1][1] == "0" && theboard[1][2] == "0")
                || (theboard[2][0] == "0" && theboard[2][1] == "0" && theboard[2][2] == "0")
                || (theboard[0][1] == "0" && theboard[1][1] == "0" && theboard[2][1] == "0")
                || (theboard[0][2] == "0" && theboard[1][2] == "0" && theboard[2][2] == "0")
                || (theboard[0][0] == "0" && theboard[1][1] == "0" && theboard[2][2] == "0")
                || (theboard[0][2] == "0" && theboard[1][1] == "0" && theboard[2][0] == "0")) {
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) {
        String[][] myBoard = newBoard();
        boolean playerOneTurn = true;
        printBoard(myBoard);
        while (true) {
            int[] myArray = getMove();
            if (playerOneTurn == true) {
                makeMovePlayer1(myArray[1], myArray[0], myBoard);
                printBoard(myBoard);
                playerOneTurn = false;
            } else {
                makeMovePlayer2(myArray[1], myArray[0], myBoard);
                printBoard(myBoard);
                playerOneTurn = true;
            }
            boolean isTrue = isFull(myBoard);
            int isOver = isGameOver(myBoard);
            if (isTrue) {
                System.out.println("\n\nIt's A Tie!\n\n");
                break;
            }
            if (isOver == 1) {
                System.out.println("\n\nPlayer 2 Wins!\n\n");
                break;
            } else if (isOver == 0) {
                System.out.println("\n\nPlayer 1 Wins\n\n");
                break;
            }
        }
    }
}
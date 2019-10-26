/**
 * @author Sean Hellum
 * **/
import java.util.Scanner;
import java.lang.Thread;

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

    public static String[][] makeMove(String[][] theboard, String playerIcon) {
        int[] set = getMove();
        int x = set[1];
        int y = set[0];
        if (theboard[x][y] == null) {
            theboard[x][y] = playerIcon;
        } else {
            System.out.println("Spot taken!");
            makeMove(theboard, playerIcon);
        }
        return theboard;
    }

    private static int[] getMove() {
        Scanner input = new Scanner(System.in);
        System.out.print("What is the first item?: ");
        int item1 = input.nextInt();
        System.out.print("What is the second item?: ");
        int item2 = input.nextInt();
        int[] myArray = { item2, item1 };
        return myArray;
    }

    public static String[][] cpuRandom(String[][] theBoard, String playerIcon) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (theBoard[i][j] == null) {
                    theBoard[i][j] = playerIcon;
                    try {
                        System.out.println("Thinking.....");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return theBoard;
                }
            }
        }
        return theBoard;
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
    public static String[] getPlayerTypes() {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to Tic-Tac-Toe!\nIs player one a cpu or a normal player(cpu/play)?: ");
        String choice1 = input.nextLine();
        System.out.print("Is player two a cpu or a normal(cpu/play)?: ");
        String choice2 = input.nextLine();
        if(((choice1.equals("play") || choice1.equals("cpu")) && (choice2.equals("play") || choice2.equals("cpu")))) {
            String[] output = {choice1, choice2};
            return output;
        } else {
            getPlayerTypes();
        }
        String[] pointless = {};
        return pointless;
    }
    public static String[] mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String[] playerTypes = getPlayerTypes();
        String[] levels = {playerTypes[0],playerTypes[1],null,null};
        if(playerTypes[0].equals("cpu")) {
            System.out.print("What level is the player1 cpu(0)?: ");
            String level1 = scanner.nextLine();
            levels[2] = level1;
        }
        if(playerTypes[1].equals("cpu")) {
            System.out.print("What level is the player2 cpu(0)?: ");
            String level2 = scanner.nextLine();
            levels[3] = level2;
        }
        return levels;
    }
    public static void main(String[] args) {
        String[] data = mainMenu();
        String[][] myBoard = newBoard();
        boolean playerOneTurn = true;
        printBoard(myBoard);
        while (true) {
            if (playerOneTurn == true) {
                if(data[0].equals("cpu") && data[2].equals("0")) {
                    cpuRandom(myBoard, "X");
                } else {
                    makeMove(myBoard, "X");
                }
                printBoard(myBoard);
                playerOneTurn = false;
            } else {
                if(data[1].equals("cpu") && data[3].equals("0")) {
                    cpuRandom(myBoard, "0");
                } else {
                    makeMove(myBoard, "0");
                }
                printBoard(myBoard);
                playerOneTurn = true;
            }
            boolean isTrue = isFull(myBoard);
            int isOver = isGameOver(myBoard);
            if (isTrue) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n\nIt's A Tie!\n\n");
                break;
            }
            if (isOver == 1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n\nPlayer 2 Wins!\n\n");
                break;
            } else if (isOver == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n\nPlayer 1 Wins\n\n");
                break;
            }
        }
    }
}
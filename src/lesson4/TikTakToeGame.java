package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TikTakToeGame {
    public static char[][] map;
    public static int SIZE = 3;
    public static final char DOT_EMPTY = '_';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                if ((i == SIZE - 1) && (map[i][j] == DOT_EMPTY))
                    System.out.print(" ");
                else
                    System.out.print(map[i][j]);
                if (j < SIZE - 1) System.out.print("|");
            }
            System.out.println();
        }
        //System.out.println();
    }

    public static Scanner sc = new Scanner(System.in);

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public static boolean isFullMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        System.out.println("Игра окончена. Ничья");
        return true;
    }

    public static void pauseAiTurn() throws InterruptedException {
        System.out.print("Ход компьютера");
        for (int i = 0; i < 4; i++) {
            Thread.sleep(300);
            System.out.print(".");
        }
        System.out.println();
    }

    public static void aiTurn() throws InterruptedException {
        pauseAiTurn();
        Random rand = new Random();
        if (flagBlockHorizontal) {
            flagBlockHorizontal = false;
            for (int i = 0; i < SIZE; i++) {
                if (map[blockTurn[HORIZONTAL]][i] == DOT_EMPTY) {
                    map[blockTurn[HORIZONTAL]][i] = DOT_O;
                    return;
                }
            }
        } else if (flagBlockVertical) {
            flagBlockVertical = false;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][blockTurn[VERTICAL]] == DOT_EMPTY) {
                    map[i][blockTurn[VERTICAL]] = DOT_O;
                    return;
                }
            }
        } else if (flagBlockDiagonal) {
            flagBlockDiagonal = false;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == DOT_EMPTY) {
                    map[i][i] = DOT_O;
                    return;
                }
            }
        } else if (flagBlockReversDiagonal) {
            flagBlockReversDiagonal = false;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][SIZE - 1 - i] == DOT_EMPTY) {
                    map[i][SIZE - 1 - i] = DOT_O;
                    return;
                }
            }
        } else {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            map[y][x] = DOT_O;
        }
    }

    public static boolean flagFinish = false;
    public static boolean flagBlockHorizontal = false;
    public static boolean flagBlockVertical = false;
    public static boolean flagBlockDiagonal = false;
    public static boolean flagBlockReversDiagonal = false;
    public static int[] finishTurn;
    public static int[] blockTurn;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static boolean checkWin() {
        int countTurn = SIZE * 2 + 2;
        int dotWinX = 0;
        int dotWinO = 0;
        finishTurn = new int[2];
        blockTurn = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_X) dotWinX++;
                else if (map[i][j] == DOT_O) dotWinO++;
            }
            if (dotWinX == SIZE) {
                System.out.println("Игра окончена. Победил человек");
                return true;
            }
            if (dotWinO == SIZE) {
                System.out.println("Игра окончена. Победил искуственный интелект");
                return true;
            }
            if (dotWinX - dotWinO == SIZE - 1) {
                flagBlockHorizontal = true;
                blockTurn[HORIZONTAL] = i;
            } else if (dotWinO - dotWinX == SIZE - 1) {
                flagFinish = true;
            }
            if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
            dotWinX = 0;
            dotWinO = 0;
        }
        /*dotWinX = 0;
        dotWinO = 0;*/
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == DOT_X) dotWinX++;
                else if (map[j][i] == DOT_O) dotWinO++;
            }
            if (dotWinX == SIZE) {
                System.out.println("Игра окончена. Победил человек");
                return true;
            }
            if (dotWinO == SIZE) {
                System.out.println("Игра окончена. Победил искуственный интелект");
                return true;
            }
            if (dotWinX - dotWinO == SIZE - 1) {
                flagBlockVertical = true;
                blockTurn[VERTICAL] = i;
            } else if (dotWinO - dotWinX == SIZE - 1) {
                flagFinish = true;
            }
            if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
            dotWinX = 0;
            dotWinO = 0;
        }
        /*dotWinX = 0;
        dotWinO = 0;*/
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == DOT_X) dotWinX++;
            else if (map[i][i] == DOT_O) dotWinO++;

            if (dotWinX == SIZE) {
                System.out.println("Игра окончена. Победил человек");
                return true;
            }
            if (dotWinO == SIZE) {
                System.out.println("Игра окончена. Победил искуственный интелект");
                return true;
            }
        }
        if (dotWinX - dotWinO == SIZE - 1) {
            flagBlockDiagonal = true;
        } else if (dotWinO - dotWinX == SIZE - 1) {
            flagFinish = true;
        }
        if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
        dotWinX = 0;
        dotWinO = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][SIZE - 1 - i] == DOT_X) dotWinX++;
            else if (map[i][SIZE - 1 - i] == DOT_O) dotWinO++;

            if (dotWinX == SIZE) {
                System.out.println("Игра окончена. Победил человек");
                return true;
            }
            if (dotWinO == SIZE) {
                System.out.println("Игра окончена. Победил искуственный интелект");
                return true;
            }
        }
        if (dotWinX - dotWinO == SIZE - 1) {
            flagBlockReversDiagonal = true;
        } else if (dotWinO - dotWinX == SIZE - 1) {
            flagFinish = true;
        }
        if ((dotWinX > 0) && (dotWinO > 0)) countTurn--;
        if (countTurn <= 0) {
            System.out.println("Игра окончена. Больше нет смысла ходить. Ничья");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите размер поля:");
        SIZE = sc.nextInt();
        initMap();
        int move = 0;
        printMap();
        while (true) {
            if ((move % 2) == 0) {
                move++;
                humanTurn();
            } else {
                move++;
                aiTurn();
            }
            printMap();
            if (checkWin()) break;
            if (isFullMap()) break;
        }
    }
}
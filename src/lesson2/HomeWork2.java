package lesson2;

import java.util.Arrays;

public class HomeWork2 {
    // Случайный одномерный массив
    public static int[] massRandom(int n, int min, int max) {
        int[] mass = new int[n];
        for (int i = 0; i < n; i++) {
            mass[i] = min + (int)Math.round(Math.random() * max);
        }
        System.out.println(Arrays.toString(mass) + " - случайный массив");
        return mass;
    }

    //Задание 1. Замена значений массива
    public static void massPermutation() {
        int[] mass = {0, 1, 1, 1, 0, 0, 0, 1};
        System.out.println(Arrays.toString(mass) + " - исходный");
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == 0) mass[i] = 1;
            else mass[i] = 0;

        }
        System.out.println(Arrays.toString(mass) + " - результат");
    }

    //Задание 2. Заполнить через цыкл
    public static void massFilling() {
        int[] mass = new int[8];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = i * 3;
        }
        System.out.println(Arrays.toString(mass));
    }

    //Задание 3. Умножить на 2 если меньше 6
    public static void massMultiply() {
        int[] mass = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(mass) + " - заданный массив");
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] < 6) mass[i] *= 2;
        }
        System.out.println(Arrays.toString(mass) + " - результат");
    }

    //Задание 4. Заполнение диагоналий квадратного массива
    public static void massFilledDiagonals() {
        int[][] mass = new int[10][10];
        for (int i = 0; i < mass.length; i++) {
            mass[i][i] = 1;
            mass[i][mass.length - 1 - i] = 1;
        }
        formatMass(mass);
    }

    // Мывод форматированной матрицы массива
    public static void formatMass(int[][] mass) {
        for (int i = 0; i < mass.length; i++) {
            for (int j = 0; j < mass[i].length; j++) {
                System.out.printf("%3d", mass[i][j]);
            }
            System.out.println();
        }
    }

    public static void massMinMax(int n) {
        int[] mass = massRandom(n, 5, 9);
        int min = mass[0];
        int max = min;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] > max) max = mass[i];
            else if (mass[i] < min) min = mass[i];
        }
        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
    }

    public static boolean checkBalans(int[] mass) {
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < mass.length; i++) {
            sumLeft += mass[i];
        }
        for (int i = mass.length - 1; i >= 0; i--) {
            sumRight += mass[i];
            if ((sumLeft - sumRight) == sumRight) return true;
        }
        return false;
    }

    public static void massShift(int[] mass, int n) {
        int buf;
        if (n % mass.length != 0) {
            n = n % mass.length;
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    buf = mass[0];
                    mass[0] = mass[mass.length - 1];
                    for (int j = 1; j < mass.length - 1; j++) {
                        mass[mass.length - j] = mass[mass.length - j - 1];
                    }
                    mass[1] = buf;
                }
            } else {
                for (int i = 0; i > n; i--) {
                    buf = mass[mass.length - 1];
                    mass[mass.length - 1] = mass[0];

                    for (int j = 1; j < mass.length - 1; j++) {
                        mass[j - 1] = mass[j];
                    }

                    mass[mass.length - 2] = buf;
                }
            }
            System.out.println(Arrays.toString(mass));
        }
    }

    public static void main(String[] args) {
        massPermutation();
        massFilling();
        massMultiply();
        massFilledDiagonals();
        massMinMax(20);
        System.out.println(checkBalans(massRandom(10, 1, 4)));
        massShift(new int[]{1, 2, 3, 4, 5}, -1);
    }
}

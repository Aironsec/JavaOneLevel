package lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWork3 {
    public static int inputNumber() {
        int num;
        do {
            showMes("Введите число:");
            Scanner scNum = new Scanner(System.in);
            num = scNum.nextInt();
        } while ((num < 0) || (num > 9));
        return num;
    }

    public static String inputStr() {
        showMes("Введите слово:");
        Scanner scStr = new Scanner(System.in);
        String word = scStr.nextLine();
        return word;
    }

    public static boolean checkNum(int attempt) {
        Random rand = new Random();
        int randomNum = rand.nextInt(10);
        while (true) {
            int userNum = inputNumber();
            attempt--;
            if (attempt <= 0) return false;
            if (randomNum == userNum)
                return true;
            else if (randomNum < userNum)
                showMes("Не угодали, число большое. Осталось попыток: " + attempt);
            else if (randomNum > userNum)
                showMes("Не угодали, число маленькое. Осталось попыток: " + attempt);

        }
    }

    public static void guess() {
        showMes("Угадайте число от 0 до 9. У вас есть 3 попытки.");
        if (checkNum(3))
            showMes("Вы угадали!");
        else
            showMes("Не угадали!");

        showMes("Чтобы сыграть заново введите 1 или 0 для выхода.");
        if (inputNumber() == 1) guess();
    }

    public static void showMes(String message) {
        System.out.println(message);
    }

    public static boolean checkWord(String word) {
        String userWord = inputStr();
        if (word.equals(userWord)) {
            showMes("Вы угадали!");
            return true;
        } else {
            String partWord = "";
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == userWord.charAt(i)) {
                    partWord += word.charAt(i);
                } else {
                    break;
                }
            }
            if (partWord.length() > 0) {
                partWord = String.format("%-15s", partWord);
                partWord = partWord.replace(" ", "#");
                showMes("Не угодали, но уже близко " + partWord);
            }
            return false;
        }
    }

    public static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random rand = new Random();
        String word = words[rand.nextInt(words.length)];
        showMes(Arrays.toString(words));
        showMes("Угадайте какое из этих слов я загадал?");
        boolean flagCicle = false;
        while (!flagCicle)
            flagCicle = checkWord(word);
    }

    public static void main(String[] args) {
        guess();
        guessWord();
    }
}

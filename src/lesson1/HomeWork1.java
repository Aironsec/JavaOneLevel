package lesson1;

public class HomeWork1 {
    //Задание 2. Все типы Java
    boolean varBool = false; // false или true
    char varChar = 'П'; //0..65535 // ''-символьные переменные
    String varString = "Привет"; // ""-строковые переменные
    byte varByte = 127; //-128..127  // 1 байт
    short varShort = 3154; // -32768..32767 // 2 байта
    int varInt = 2254589; //-2147483648..2147483647 // 4 байта
    long varLong = 54832415L; //–9 223 372 036 854 775 808..9 223 372 036 854 775 807 // 8 байт
    double varDouble = 235.2354; // 8 байт
    float varFloat = 1235.325F; // 4 байта
    //Задание 3. Вычисление вырожения
    static double sumMethod(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }
    //Задание 4. Вхождение числа в дипазон 10..20
    static int x;

    static boolean range(int a, int b) {
        x = a + b;
        if (10 <= x && x <= 20) return true;
        else return false;
    }
    //Задание 5. Определение знака числа.
    static String number(int a) {
        if (0 <= a) return "Положительное число";
        else return "Отрицательное число";

    }
    //Задание 6. Определение отрицательного числа
    static boolean numberOpposite(int a) {
        if (a < 0) return true;
        return false;
    }
    //Задание 7. Передача имени в пораметре
    static void name(String a) {
        System.out.println("Привет, " + a);
    }
    //задание 8. Определение високосный или не високосный год
    static String leapYear(int year) {
        if (year % 4 == 0)
            if (year % 100 != 0) return "Високосный год";
            else if (year % 400 == 0) return "Високосный год";
        return "Не високосный год";
    }

    public static void main(String[] args) {
        System.out.println(sumMethod(5, 4, 2, 8));

        System.out.println(range(7, 4));
        System.out.println(x);
        System.out.println(number(-345));
        if (numberOpposite(-1)) System.out.println(true);
        name("Сергей");
        System.out.println(leapYear(2020));
    }
}

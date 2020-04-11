package lesson6;

import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Animal sharik = new Dog("Шарик собака");
        Dog bobik = new PekinesesDog("Бобик Пекинес");
        Dog reks  = new ShepherdDog("Рэкс Овчарка");
        Animal barsik = new Cat("Барсик кот");
        System.out.println("Бег с припятствиями:");
        sharik.run(450);
        bobik.run(401);
        reks.run(550);
        barsik.run(100);
        System.out.println("Заплыв:");
        sharik.sail(10);
        bobik.sail(20);
        reks.sail(5);
        barsik.sail(10);
        System.out.println("Прыжки:");
        sharik.jump(0.1);
        bobik.jump(1);
        reks.jump(5);
        barsik.jump(2);
    }
}

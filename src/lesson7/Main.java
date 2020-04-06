package lesson7;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public enum nickname {
        Барсик,
        Васька,
        Пушистый,
        Черныш,
        Мурзик,
        Муся,
        Рыжик,
        Одноглазый,
        Серый,
        Снежок
    }

    static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<>();
        Plate plate1 = new Plate(15);
        Cat cat1 = new Cat("Барсик", 20);
        plate1.info();
        cat1.eat(plate1);
        plate1.info();
        plate1.setFood(100);
        plate1.info();
        for (int i = 0; i < 10; i++) {
            cats.add(new Cat(nickname.values()[i].toString(), rnd(5, 25)));
        }
        for (Cat cat : cats) {
            cat.eat(plate1);
        }
        System.out.println("------------------------------------");
        for (Cat cat : cats) {
            if (cat.satiety) System.out.println(cat.name + " Сытый!");
            else System.out.println(cat.name + " Голодный!");
        }
    }
}

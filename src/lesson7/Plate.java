package lesson7;

import java.util.Scanner;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    private boolean tmp;

    public boolean decreaseFood(int n) {
        tmp = false;
        if (food >= n) {
            food -= n;
            tmp = true;
        } else {
            System.out.println("Еды слишком мало, положите ещё!\n НЕТ - 0, ДА - 1");
            if (sc.nextInt() > 0) incFood(n);
        }
        return tmp;
    }

    Scanner sc = new Scanner(System.in);

    private void incFood(int n) {
        System.out.print("Добавить еды: ");
        food += sc.nextInt();
        decreaseFood(n);
    }

    public void info() {
        if (food > 0) System.out.println("В тарелке еды: " + food);
        else System.out.println("В тарелке нет еды!!");
    }

    public void setFood(int food) {
        this.food += food;
    }
}

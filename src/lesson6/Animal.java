package lesson6;

import java.util.Random;

public abstract class Animal {
    private String name;
    private int age;

    public Animal(String name) {
        this.name = name;
        age = 0;
    }

    public String getName(){
        return name;
    }

    abstract public void run(double value);
    public abstract void sail(double value);
    public abstract void jump(double value);

}

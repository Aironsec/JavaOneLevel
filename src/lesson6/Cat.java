package lesson6;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void run(double value) {
        if (value > 200) System.out.println(getName() + " не может пробежать больше 200м");
        else System.out.println(getName() + " успешно пробежал " + value + "м");
    }

    @Override
    public void sail(double value) {
        System.out.println(getName() + " и не умеет плавать");
    }

    @Override
    public void jump(double value) {
        if (value > 2) System.out.println(getName() + " не прыгнет выше 2м");
        else System.out.println(getName() + " прыгнул на " + value + "м");
    }
}

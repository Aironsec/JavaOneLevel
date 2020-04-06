package lesson6;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void run(double value) {
        if (value > 500) System.out.println(getName() + " не может пробежать больше 500м");
        else System.out.println(getName() + " успешно пробежал(а) " + value + "м");
    }

    @Override
    public void sail(double value) {
        if (value > 10) System.out.println(getName() + " не может проплыть больше 10м");
        else System.out.println(getName() + " успешно проплыл(а) " + value + "м");
    }

    @Override
    public void jump(double value) {
        if (value > 0.5) System.out.println(getName() + " не прыгнет больше 0.5м");
        else System.out.println(getName() + " прыгнул(а) на " + value + "м");
    }
}

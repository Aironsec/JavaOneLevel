package lesson6;

public class ShepherdDog extends Dog {
    public ShepherdDog(String name) {
        super(name);
    }

    @Override
    public void run(double value) {
        if (value > 600) System.out.println(getName() + " не может пробежать больше 600м");
        else System.out.println(getName() + " успешно пробежал(а) " + value + "м");
    }
}

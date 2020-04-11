package lesson6;

public class PekinesesDog extends Dog {
    public PekinesesDog(String name){
        super(name);
    }

    @Override
    public void run(double value) {
        if (value > 400) System.out.println(getName() + " не может пробежать больше 400м");
        else System.out.println(getName() + " успешно пробежал(а) " + value + "м");
    }
}

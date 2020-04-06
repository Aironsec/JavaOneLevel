package lesson7;

public class Cat {
    protected String name;
    private int appetite;
    protected boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        satiety = false;
    }

    public void eat(Plate p) {
        if (satiety) System.out.println(name + " мяу-мяу (Спасибо я сытый!)");
        else if (p.decreaseFood(appetite)) {
            satiety = true;
            eat(p);
        }
        else System.out.println(name + " Мяу-мяу-мяу (Я голодный!!)");
    }

}

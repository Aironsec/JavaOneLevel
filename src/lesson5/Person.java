package lesson5;

public class Person {
    private String fio;
    private PersonPost post;
    private String mail;
    private String phone;
    private int salary;
    private int age;

    static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public Person(String fio, String phone) {
        this.fio = fio;
        this.post = PersonPost.values()[rnd(0, PersonPost.values().length - 1)];
        this.mail = fio.substring(0, fio.indexOf(" ")).toLowerCase() + "@company.ru";
        this.phone = phone;
        this.salary = rnd(50000, 500000);
        this.age = rnd(25, 75);
    }


    public void info() {
            System.out.println("Сотрудник: " + fio + "\nДолжность: " + post + "\nПочта: " +
                    mail + "\nТелефон: " + phone + "\nЗарплата: " + salary + "\nВозраст: " + age + "\n");
    }

    public void info(int age) {
        if (this.age > age)
            System.out.println("Сотрудник: " + fio + "\nДолжность: " + post + "\nПочта: " +
                    mail + "\nТелефон: " + phone + "\nЗарплата: " + salary + "\nВозраст: " + age + "\n");
    }

}

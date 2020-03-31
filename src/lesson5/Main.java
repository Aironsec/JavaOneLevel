package lesson5;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Petrov Ivan Ivavovich", "8(910)500-01-01"));
        persons.add(new Person("Sidorov Nikolay Petrovich", "8(903)200-00-00"));
        persons.add(new Person("Ivanov Sergey Sergeevich", "8(910)300-05-05"));
        persons.add(new Person("Gol Zabil Vorotovich", "8(910)900-03-03"));
        persons.add(new Person("Shoroh Sovsem Grovkovich", "8(903)600-30-30"));

        persons.get(Person.rnd(0, persons.size()-1)).info();

        for (Person person : persons) {
            person.info(40);
        }
    }
}

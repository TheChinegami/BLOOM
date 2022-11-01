package Model;

public class Donator extends Person {
    // attributes
    private int age;

    // constructor
    public Donator() {
    }

    public Donator(String cin, String firstName, String lastName, String phoneNumber, int age) {
        super(cin, firstName, lastName, phoneNumber);
        this.age = age;
    }

    // getters
    public int getAge() {
        return age;
    }

    // setters
    public void setAge(int age) {
        this.age = age;
    }

    // add new user
    @Override
    public void add() {

    }

    // update a person
    @Override
    public void update() {

    }

    // delete a person
    @Override
    public void delete() {

    }
}

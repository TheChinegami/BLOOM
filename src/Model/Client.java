package Model;

public class Client {
    // attributes
    private int id;
    private String name;
    private String phoneNumber;

    // constructor
    public Client() {
    }

    public Client(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // add new client
    public void add() {

    }

    // update a client
    public void update() {

    }

    // delete a client
    public void delete() {

    }
}

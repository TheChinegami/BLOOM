package Model;

public class User extends Person {
    // attributes
    private String email;
    private String userName;
    private String password;

    // constructor

    public User() {

    }


    public User(String CIN, String firstName, String lastName, String phoneNumber, String email, String userName, String password) {
        super(CIN, firstName, lastName, phoneNumber);
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    // getters

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    // setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
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

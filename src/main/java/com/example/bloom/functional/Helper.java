package com.example.bloom.functional;

import com.example.bloom.model.Donor;
import com.example.bloom.model.User;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Helper
{
    private static Helper helper;
    private PreparedStatement statement;
    private ResultSet result;

    public static Helper getHelper()
    {
        if(helper==null)
            helper = new Helper();
        return helper;
    }
    public User getAdministrator() throws SQLException, ClassNotFoundException {
        statement = MyConnection.getCon().prepareStatement("select username,password from user");
        result = statement.executeQuery();
        result.next();
        User user = new User();
        user.setUserName(result.getString("username"));
        user.setPassword(result.getString("password"));
        return user;
    }

    public ArrayList<Donor> getDonors() throws SQLException, ClassNotFoundException {
        ArrayList<Donor> list = new ArrayList<>();
        statement = MyConnection.getCon().prepareStatement("select * from donor");
        result = statement.executeQuery();
        LocalDate d1;
        LocalDate d2;
        long age;
        Boolean sickcheck;
        String Sick ;
        Donor donor;
        while(result.next())
        {
            d2 = LocalDate.now();
            d1 = result.getDate("birth_date").toLocalDate();
            age = ChronoUnit.DAYS.between(d1,d2)/365;
            sickcheck = result.getBoolean("Sickness");
            if (sickcheck.toString() == " "){Sick =" Unknown"; System.out.println("this condition is working "+sickcheck+" "+Sick);}else {Sick=sickcheck.toString();System.out.println("this condition is FUCKED "+sickcheck+" "+Sick);};
            donor = new Donor(
                    result.getInt("id"),
                    result.getString("CIN"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("phone_number"),
                    age,
                    result.getString("emergency_number"),
                    Sick);
            list.add(donor);
        }
        return list;
    }


    public ArrayList<Donor> getDonorByindex(int id) throws SQLException,ClassNotFoundException{

        ArrayList<Donor> list = new ArrayList<>();
        statement = MyConnection.getCon().prepareStatement("select * from donor where id ="+id);
        result = statement.executeQuery();
        LocalDate d1;
        LocalDate d2;
        long age;
        Boolean sickcheck;
        String Sick ;
        Donor donor;
        while(result.next())
        {
            d2 = LocalDate.now();
            d1 = result.getDate("birth_date").toLocalDate();
            age = ChronoUnit.DAYS.between(d1,d2)/365;
            sickcheck = result.getBoolean("Sickness");
            if (sickcheck.toString() == " "){Sick =" Unknown"; System.out.println("this condition is working "+sickcheck+" "+Sick);}else {Sick=sickcheck.toString();System.out.println("this condition is FUCKED "+sickcheck+" "+Sick);};
            donor = new Donor(
                    result.getInt("id"),
                    result.getString("CIN"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getString("phone_number"),
                    age,
                    result.getString("emergency_number"),
                    Sick);
            list.add(donor);
        }
        return list;

    }

    public void showAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("warning");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void showSuccess(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

}

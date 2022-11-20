package com.example.bloom.functional;

import com.example.bloom.model.Donor;
import com.example.bloom.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Helper
{
    private static Helper helper;

    public static Helper getHelper()
    {
        if(helper==null)
            helper = new Helper();
        return helper;
    }
    private PreparedStatement statement;
    private ResultSet result;
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
        Donor donor;
        while(result.next())
        {
            donor = new Donor(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6));
            list.add(donor);
        }
        return list;
    }
}

package com.example.bloom;

import com.example.bloom.Model.Donor;
import com.example.bloom.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public User getAdminstrator() throws SQLException, ClassNotFoundException {
        statement = MyConnection.getCon().prepareStatement("select username,password from user");
        result = statement.executeQuery();
        result.next();
        User user = new User();
        user.setEmail(result.getString(1));
        user.setPassword(result.getString(2));
        return user;
    }

    public ObservableList<Donor> getDonors() throws SQLException, ClassNotFoundException {
        ObservableList<Donor> list = FXCollections.observableArrayList();
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

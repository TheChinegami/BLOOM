package com.example.bloom.functional;

import com.example.bloom.model.*;
import javafx.scene.control.Alert;

import java.sql.Date;
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
        String Sick= "true" ;
        Donor donor;
        while(result.next())
        {
            d2 = LocalDate.now();
            d1 = result.getDate("birth_date").toLocalDate();
            age = ChronoUnit.DAYS.between(d1,d2)/365;
            sickcheck = result.getBoolean("Sickness");
            // if (sickcheck.toString() == " "){Sick =" Unknown"; System.out.println("this condition is working "+sickcheck+" "+Sick);}else {Sick=sickcheck.toString();System.out.println("this condition is FUCKED "+sickcheck+" "+Sick);};
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

    public ArrayList<Client> getClients() throws SQLException, ClassNotFoundException {

        ArrayList<Client> list = new ArrayList<>();
        statement = MyConnection.getCon().prepareStatement("select * from client");
        result = statement.executeQuery();

        Client client;
        while(result.next())
        {


            // if (sickcheck.toString() == " "){Sick =" Unknown"; System.out.println("this condition is working "+sickcheck+" "+Sick);}else {Sick=sickcheck.toString();System.out.println("this condition is FUCKED "+sickcheck+" "+Sick);};
            client = new Client(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("phone_number"));

            list.add(client);
        }
        return list;
    }

    public ArrayList<Bag> getBags() throws SQLException, ClassNotFoundException {

        ArrayList<Bag> list = new ArrayList<>();
        statement = MyConnection.getCon().prepareStatement("select bag.id , bag.donorid , bag.donationdate, bag.bloodtypeid, bag.billid, bag.bagtypeid from bag , donor where bag.donorid = donor.id ");
        result = statement.executeQuery();

        Bag bag;
        while(result.next())
        {



            // if (sickcheck.toString() == " "){Sick =" Unknown"; System.out.println("this condition is working "+sickcheck+" "+Sick);}else {Sick=sickcheck.toString();System.out.println("this condition is FUCKED "+sickcheck+" "+Sick);};
            bag = new Bag(

                    result.getInt("id"),
                    result.getDate("donationDate").toLocalDate(),
                    result.getInt("donorid"),
                    result.getString("bagtypeid"),
                    result.getString("bloodtypeid"));

            list.add(bag);
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
    // to get the bill or the invoices
    public ArrayList<Bill> getAllInvoices() throws SQLException, ClassNotFoundException
    {
        ArrayList<Bill> list = new ArrayList();
        statement = MyConnection.getCon().prepareStatement("select * from bill");
        result = statement.executeQuery();
        Bill bill;
        LocalDate newDate;
        while(result.next())
        {
            newDate = result.getDate("billdate").toLocalDate();
            bill = new Bill(
                    result.getInt("id"),
                    newDate,
                    result.getDouble("amount"),
                    result.getInt("clientid")
            );
            list.add(bill);
        }

        return list;
    }

    // get the client names
    public ArrayList<Client> getAllClients() throws SQLException, ClassNotFoundException
    {
        ArrayList<Client> list = new ArrayList();
        statement = MyConnection.getCon().prepareStatement("select * from client");
        result = statement.executeQuery();
        while(result.next())
        {
            list.add(new Client(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("phone_number")
            ));
        }
        return list;
    }

    // get a string list of the bagtypes
    public ArrayList<String> getBagTypes() throws SQLException, ClassNotFoundException
    {
        ArrayList<String> list = new ArrayList<>();
        statement = MyConnection.getCon().prepareStatement("select * from bagtype");
        result = statement.executeQuery();
        while(result.next())
        {
            if(result.getInt("id")==4 || result.getInt("id")==5)
                continue;
            list.add(result.getString("type"));
        }
        return list;
    }

    // get a string list of the bloodtypes
    public ArrayList<String> getBloodTypes() throws SQLException, ClassNotFoundException
    {
        ArrayList<String> list = new ArrayList<>();
        statement = MyConnection.getCon().prepareStatement("select * from bloodtype");
        result = statement.executeQuery();
        while(result.next())
        {
            list.add(result.getString("type"));
        }
        return list;
    }

    // get the client name from the id
    public int getClientId(String name) throws SQLException, ClassNotFoundException
    {
        String query = "select * from client where name = ?";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setString(1,name);
        result = statement.executeQuery();
        result.next();
        int id = result.getInt("id");
        return id;
    }

    // add new invoice
    public void addNewInvoice(String name,LocalDate date) throws SQLException, ClassNotFoundException
    {
        Date myDate;
        myDate = Date.valueOf(date);
        int id = getClientId(name);
        String query = "insert into bill(billdate,amount,clientid) values(?,0,?);";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setDate(1,myDate);
        statement.setInt(2,id);
        statement.executeUpdate();
    }

    // get the invoices for this month
    public ArrayList<Bill> getInvoicesOfThisMonth() throws SQLException, ClassNotFoundException
    {
        ArrayList<Bill> list = new ArrayList();
        LocalDate date = LocalDate.now();
        statement = MyConnection.getCon().prepareStatement("select * from bill where month(billdate) = ? and year(billdate) = ?");
        statement.setInt(1,date.getMonthValue());
        statement.setInt(2,date.getYear());
        result = statement.executeQuery();
        Bill bill;
        LocalDate newDate;
        while(result.next())
        {
            newDate = result.getDate("billdate").toLocalDate();
            bill = new Bill(
                    result.getInt("id"),
                    newDate,
                    result.getDouble("amount"),
                    result.getInt("clientid")
            );
            list.add(bill);
        }

        return list;
    }

    ////////////////////////////////////////////////////////////////
    // the start of the invoice rows operations
    ////////////////////////////////////////////////////////////////

    // get the bag type by his name
    public int getBagTypeByHisName(String name) throws SQLException, ClassNotFoundException
    {
        String query = "select * from bagtype where type = ?";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setString(1,name);
        result = statement.executeQuery();
        result.next();
        int id = result.getInt("id");
        return id;
    }
    // get the blood type by his name
    public int getBloodTypeByHisName(String name) throws SQLException, ClassNotFoundException
    {
        String query = "select * from bloodtype where type = ?";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setString(1,name);
        result = statement.executeQuery();
        result.next();
        int id = result.getInt("id");
        return id;
    }
    // get number of a specific sample with the bag type and the blood type
    public int getNumberOfSample(String bagType, String bloodType) throws SQLException, ClassNotFoundException
    {
        int bagTypeId = getBagTypeByHisName(bagType);
        int bloodTypeId = getBloodTypeByHisName(bloodType);
        String query = "select count(*) from bag where bloodtypeid = ? and bagtypeid = ? and billid is null;";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,bloodTypeId);
        statement.setInt(2,bagTypeId);
        result = statement.executeQuery();
        result.next();
        int i = result.getInt(1);
        return i;
    }
    // get the list of the bags that is Availables
    public ArrayList<Integer> getIdsOfSample(String bagType, String bloodType, int quantity) throws SQLException, ClassNotFoundException
    {
        ArrayList<Integer> list = new ArrayList<>();
        int bagTypeId = getBagTypeByHisName(bagType);
        int bloodTypeId = getBloodTypeByHisName(bloodType);
        String query = "select id from bag where bloodtypeid = ? and bagtypeid = ? and billid is null limit ?;";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,bloodTypeId);
        statement.setInt(2,bagTypeId);
        statement.setInt(3,quantity);
        result = statement.executeQuery();
        while(result.next())
        {
            list.add(result.getInt(1));
        }
        return list;
    }
    // get the list of the bags with specific bloodtype and specific bagtype in a specific bill
    public ArrayList<Integer> getIdsOfSampleInBill(String bagType, String bloodType,int billId, int quantity) throws SQLException, ClassNotFoundException
    {
        ArrayList<Integer> list = new ArrayList<>();
        int bagTypeId = getBagTypeByHisName(bagType);
        int bloodTypeId = getBloodTypeByHisName(bloodType);
        String query = "select id from bag where bloodtypeid = ? and bagtypeid = ? and billid = ? limit ?;";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,bloodTypeId);
        statement.setInt(2,bagTypeId);
        statement.setInt(3,billId);
        statement.setInt(4,quantity);
        result = statement.executeQuery();
        while(result.next())
        {
            list.add(result.getInt(1));
        }
        return list;
    }
    // set the bag to a specific bill
    public void addTheBagsToAnInvoice(int billId, int bagId) throws SQLException, ClassNotFoundException
    {
        String query = "update bag set billid = ? where id = ?";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,billId);
        statement.setInt(2,bagId);
        statement.executeUpdate();
    }
    // set the bag to a specific bill
    public void deleteBagFromAnInvoice(int bagId) throws SQLException, ClassNotFoundException
    {
        String query = "update bag set billid = null where id = ?";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,bagId);
        statement.executeUpdate();
    }
    // get sample model
    public ArrayList<Sample> getBagsInBill(int billId) throws SQLException, ClassNotFoundException
    {
        ArrayList<Sample> list = new ArrayList<>();
        String query = "select CONCAT(b.type,\" \",c.type) as \"sample\",b.price,count(*) as \"quantity\",count(*)*b.price as \"price per row\" from bag a,bagtype b,bloodtype c where a.bagtypeid = b.id and a.bloodtypeid = c.id and billid = ? group by b.type,c.type;";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,billId);
        result = statement.executeQuery();
        while(result.next())
        {
            list.add(new Sample(
                    result.getString("sample"),
                    result.getDouble("price"),
                    result.getInt("quantity"),
                    result.getDouble("price per row")
            ));
        }
        return list;
    }
    // get number of a specific sample with the bag type and the blood type and the bill id
    public int getNumberOfSampleInBill(String bagType, String bloodType, int billId) throws SQLException, ClassNotFoundException
    {
        int bagTypeId = getBagTypeByHisName(bagType);
        int bloodTypeId = getBloodTypeByHisName(bloodType);
        String query = "select count(*) from bag where bloodtypeid = ? and bagtypeid = ? and billid = ?;";
        statement = MyConnection.getCon().prepareStatement(query);
        statement.setInt(1,bloodTypeId);
        statement.setInt(2,bagTypeId);
        statement.setInt(3,billId);
        result = statement.executeQuery();
        result.next();
        int i = result.getInt(1);
        return i;
    }

    ////////////////////////////////////////////////////////////////
    // the end of invoice rows operations
    ////////////////////////////////////////////////////////////////

    // get the bags of a specific bill
    public ArrayList<Bag> getBagsOfBill(int billId) throws SQLException, ClassNotFoundException
    {
        ArrayList<Bag> list = new ArrayList();
        statement = MyConnection.getCon().prepareStatement("select a.id,c.cin,a.donationdate,d.type,b.type from bag a,bloodtype b, donor c, bagtype d where a.bloodtypeid = b.id and a.donorid = c.id and a.bagtypeid = d.id and a.billid = ?;");
        statement.setInt(1,billId);
        result = statement.executeQuery();
        Bag bag = null;
        String bagType;
        LocalDate newDate;
        while(result.next())
        {
            newDate = result.getDate(3).toLocalDate();
            bagType = result.getString(4);
            if(bagType.equals("red cells"))
            {
                bag = new RedCells(
                        result.getInt(1),
                        newDate,
                        result.getInt(2),
                        "red cells",
                        result.getString(5)
                );
            }else if(bagType.equals("plasma"))
            {
                bag = new Plasma(
                        result.getInt(1),
                        newDate,
                        result.getInt(2),
                        "plasma",
                        result.getString(5)
                );
            }else
            {
                bag = new Platelets(
                        result.getInt(1),
                        newDate,
                        result.getInt(2),
                        "platelets",
                        result.getString(5)
                );
            }

            list.add(bag);
        }

        return list;
    }
}

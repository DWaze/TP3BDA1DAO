package com.company.DAO;

import com.company.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by lhadj on 1/1/2017.
 */
public class AddressDAO extends DAO {
    String SQL ;

    public AddressDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Object obj) {
        Address address = (Address) obj;
        try{
            SQL="INSERT INTO ADDRESS VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,address.getId()+"");
            preparedStatement.setString(2,address.getStreet());
            preparedStatement.setString(3,address.getCity());
            preparedStatement.setString(4,address.getZipCode());
            preparedStatement.setString(5,address.getCountery());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        }catch (SQLException e) {
            try {
                e.printStackTrace();
                connection.rollback();
                return false;
            } catch (SQLException e1) {
                e1.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public boolean delete(Object obj) {
        Address address = (Address)obj;
        try {
            SQL = "DELETE FROM ADDRESS WHERE IDADR=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,address.getId()+"");
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object obj) {
        Address address= (Address) obj;
        try{
            SQL="UPDATE ADDRESS SET STREET=?,CITY=?,ZIPCODE=?,COUNTRY=? WHERE IDADR=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(5,address.getId()+"");
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setString(2,address.getCity());
            preparedStatement.setString(3,address.getZipCode());
            preparedStatement.setString(4,address.getCountery());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Address find(int id) {
        String SQL = "SELECT * FROM ADDRESS WHERE IDADR= ?";
        Address address=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            preparedStatement.setString(1,id+"");
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                String idAdr =rs.getString("IDADR");
                String street  = rs.getString("STREET");
                String city = rs.getString("CITY");
                String zipCode = rs.getString("ZIPCODE");
                String countery = rs.getString("COUNTRY");
                address = new Address(Integer.parseInt(idAdr),street,city,zipCode,countery);
            }
            return address;
        } catch (SQLException e) {
            e.printStackTrace();
            return address;
        }
    }


    public ArrayList<Address> findAll(){
        String SQL = "SELECT * FROM ADDRESS";
        Address address=null;
        ArrayList<Address> allAddress=new ArrayList<Address>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                String idAdr =rs.getString("IDADR");
                String street  = rs.getString("STREET");
                String city = rs.getString("CITY");
                String zipCode = rs.getString("ZIPCODE");
                String countery = rs.getString("COUNTRY");
                address = new Address(Integer.parseInt(idAdr),street,city,zipCode,countery);
                allAddress.add(address);
            }
            return allAddress;
        } catch (SQLException e) {
            e.printStackTrace();
            return allAddress;
        }
    }
}

package com.company.DAO;

import com.company.Address;
import com.company.Student;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lhadj on 1/1/2017.
 */
public class StudentDAO extends DAO {
    String SQL ;
    public StudentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Object obj) {
        Student student= (Student) obj;
        try{
            SQL="INSERT INTO PERSONE VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getId()+"");
            preparedStatement.setString(2,student.getrAddress().getId()+"");
            preparedStatement.setString(3,student.getFirstName());
            preparedStatement.setString(4,student.getLastName());
            preparedStatement.setString(5,student.getEmail());
            preparedStatement.setDate(6, (Date) student.getDateOfBirth());
            preparedStatement.setString(7,"student");
            preparedStatement.executeUpdate();

            SQL="INSERT INTO STUDENT VALUES(?,?,?)";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getId()+"");
            preparedStatement.setString(2,student.getNumCard());
            preparedStatement.setDouble(3,student.getBacNote());
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
        Student student = (Student) obj;
        try {
            SQL = "DELETE FROM STUDENT_COURS WHERE IDPERSSONES=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getId()+"");
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM STUDENT WHERE IDPERSSONES=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getId()+"");
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM PERSONE WHERE IDPERSSONE=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getId()+"");
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
        Student student= (Student) obj;
        try{
            SQL="UPDATE PERSONE SET IDADR=?,FIRSTNAME=?,LASTNAME=?,EMAIL=?,BIRTHDAY=?" +
                    ",TYPEP=? WHERE IDPERSSONE=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getrAddress().getId()+"");
            preparedStatement.setString(2,student.getFirstName());
            preparedStatement.setString(3,student.getLastName());
            preparedStatement.setString(4,student.getEmail());
            preparedStatement.setDate(5, (Date) student.getDateOfBirth());
            preparedStatement.setString(6,"student");
            preparedStatement.setString(7,student.getId()+"");
            preparedStatement.executeUpdate();

            SQL="UPDATE STUDENT SET NUMCARD=?,BACNOTE=? WHERE IDPERSSONES=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,student.getNumCard()+"");
            preparedStatement.setDouble(2,student.getBacNote());
            preparedStatement.setString(3,student.getId()+"");
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
    public Student find(int id) {
        String SQL = "SELECT * FROM PERSONE WHERE IDPERSSONE = ?";
        Student student=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            preparedStatement.setString(1,id+"");
            ResultSet rs =preparedStatement.executeQuery();

            SQL = "SELECT * FROM STUDENT WHERE IDPERSSONES = ?";
            preparedStatement= connection.prepareStatement(SQL);
            preparedStatement.setString(1,id+"");
            ResultSet rs2 =preparedStatement.executeQuery();

            while (rs2.next()){
                rs.next();
                String idPerssone =rs.getString("IDPERSSONE");
                String idAdr  = rs.getString("IDADR");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                Date birthday = rs.getDate("BIRTHDAY");
                String typeP = rs.getString("TYPEP");
                String numCard = rs2.getString("NUMCARD");
                double bacNote = rs2.getDouble("BACNOTE");
                student = new Student(Integer.parseInt(idPerssone),firstName,lastName,email,birthday,numCard,bacNote);

                Address address ;

                SQL = "SELECT * FROM ADDRESS WHERE IDADR = ?";
                preparedStatement= connection.prepareStatement(SQL);
                preparedStatement.setString(1,idAdr+"");
                ResultSet rs3 =preparedStatement.executeQuery();
                rs3.next();
                String street = rs3.getString("STREET");
                String city = rs3.getString("CITY");
                String zipCode = rs3.getString("ZIPCODE");
                String country = rs3.getString("COUNTRY");
                address = new Address(Integer.parseInt(idAdr),street,city,zipCode,country);
                student.setAddress(address);
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return student;
        }
    }

    public ArrayList<Student> findAll(){
        String SQL = "SELECT * FROM STUDENT";
        ArrayList<Student> allStudents = new ArrayList<Student>() ;
        Student student=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                SQL = "SELECT * FROM PERSONE WHERE IDPERSSONE = ?";
                preparedStatement= connection.prepareStatement(SQL);
                String idPerssone =rs.getString("IDPERSSONES");
                String numCard = rs.getString("NUMCARD");
                double bacNote = rs.getDouble("BACNOTE");

                preparedStatement.setString(1,idPerssone);
                ResultSet rs2 =preparedStatement.executeQuery();
                rs2.next();

                String idAdr  = rs2.getString("IDADR");
                String firstName = rs2.getString("FIRSTNAME");
                String lastName = rs2.getString("LASTNAME");
                String email = rs2.getString("EMAIL");
                Date birthday = rs2.getDate("BIRTHDAY");

                student = new Student(Integer.parseInt(idPerssone),firstName,lastName,email,birthday,numCard,bacNote);

                Address address ;

                SQL = "SELECT * FROM ADDRESS WHERE IDADR = ?";
                preparedStatement= connection.prepareStatement(SQL);
                preparedStatement.setString(1,idAdr+"");
                ResultSet rs3 =preparedStatement.executeQuery();
                rs3.next();
                String street = rs3.getString("STREET");
                String city = rs3.getString("CITY");
                String zipCode = rs3.getString("ZIPCODE");
                String country = rs3.getString("COUNTRY");
                address = new Address(Integer.parseInt(idAdr),street,city,zipCode,country);
                student.setAddress(address);
                allStudents.add(student);

            }
            return allStudents;
        } catch (SQLException e) {
            e.printStackTrace();
            return allStudents;
        }
    }
}

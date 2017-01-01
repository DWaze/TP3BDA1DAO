package com.company.DAO;

import com.company.Address;
import com.company.Teacher;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lhadj on 1/1/2017.
 */
public class TeacherDAO extends DAO {
    String SQL ;
    public TeacherDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Object obj) {
        Teacher teacher= (Teacher) obj;
        try{
            SQL="INSERT INTO PERSONE VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,teacher.getId()+"");
            preparedStatement.setString(2,teacher.getrAddress().getId()+"");
            preparedStatement.setString(3,teacher.getFirstName());
            preparedStatement.setString(4,teacher.getLastName());
            preparedStatement.setString(5,teacher.getEmail());
            preparedStatement.setDate(6, (Date) teacher.getDateOfBirth());
            preparedStatement.setString(7,"teacher");
            preparedStatement.executeUpdate();

            SQL="INSERT INTO TEACHER VALUES(?,?)";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,teacher.getId()+"");
            preparedStatement.setInt(2,teacher.getGrade());
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
        Teacher teacher = (Teacher) obj;
        try {
            SQL = "DELETE FROM STUDENT_COURS WHERE IDPERSSONES=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,teacher.getId()+"");
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM TEACHER WHERE IDPERSSONET=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,teacher.getId()+"");
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM PERSONE WHERE IDPERSSONE=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,teacher.getId()+"");
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
        Teacher teacher= (Teacher) obj;
        try{
            SQL="UPDATE PERSONE SET IDADR=?,FIRSTNAME=?,LASTNAME=?,EMAIL=?,BIRTHDAY=?" +
                    ",TYPEP=? WHERE IDPERSSONE=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,teacher.getrAddress().getId()+"");
            preparedStatement.setString(2,teacher.getFirstName());
            preparedStatement.setString(3,teacher.getLastName());
            preparedStatement.setString(4,teacher.getEmail());
            preparedStatement.setDate(5, (Date) teacher.getDateOfBirth());
            preparedStatement.setString(6,"teacher");
            preparedStatement.setString(7,teacher.getId()+"");
            preparedStatement.executeUpdate();

            SQL="UPDATE TEACHER SET GRAD=? WHERE IDPERSSONET=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setInt(1,teacher.getGrade());
            preparedStatement.setString(2,teacher.getId()+"");
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
    public Teacher find(int id) {
        String SQL = "SELECT * FROM PERSONE WHERE IDPERSSONE = ?";
        Teacher teacher=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            preparedStatement.setString(1,id+"");
            ResultSet rs =preparedStatement.executeQuery();

            SQL = "SELECT * FROM TEACHER WHERE IDPERSSONET = ?";
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
                int grade = rs2.getInt("GRAD");
                teacher = new Teacher(Integer.parseInt(idPerssone),firstName,lastName,email,birthday,grade);

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
                teacher.setAddress(address);
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            return teacher;
        }
    }

    public ArrayList<Teacher> findAll(){
        String SQL = "SELECT * FROM TEACHER";
        ArrayList<Teacher> allTeacher = new ArrayList<Teacher>() ;
        Teacher teacher=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                SQL = "SELECT * FROM PERSONE WHERE IDPERSSONE = ?";
                preparedStatement= connection.prepareStatement(SQL);
                String idPerssone =rs.getString("IDPERSSONET");
                int grad = rs.getInt("GRAD");

                preparedStatement.setString(1,idPerssone);
                ResultSet rs2 =preparedStatement.executeQuery();
                rs2.next();

                String idAdr  = rs2.getString("IDADR");
                String firstName = rs2.getString("FIRSTNAME");
                String lastName = rs2.getString("LASTNAME");
                String email = rs2.getString("EMAIL");
                Date birthday = rs2.getDate("BIRTHDAY");

                teacher = new Teacher(Integer.parseInt(idPerssone),firstName,lastName,email,birthday,grad);

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
                teacher.setAddress(address);
                allTeacher.add(teacher);
            }
            return allTeacher;
        } catch (SQLException e) {
            e.printStackTrace();
            return allTeacher;
        }
    }
}

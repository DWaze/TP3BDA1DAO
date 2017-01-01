package com.company.DAO;

import com.company.Course;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lhadj on 1/1/2017.
 */
public class CoursDAO extends DAO {
    String SQL ;
    public CoursDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Object obj) {
        Course course = (Course)obj;
        try{
            SQL="INSERT INTO COURSE VALUES(?,?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,course.getId()+"");
            preparedStatement.setString(2,course.getName());
            preparedStatement.setInt(3,course.getCredit());
            preparedStatement.setString(4,course.getContent());
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
        Course course = (Course)obj;
        try {
            SQL = "DELETE FROM TEACHERCOURS WHERE IDCOURS=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,course.getId()+"");
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM STUDENT_COURS WHERE IDCOURS=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,course.getId()+"");
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM COURSE WHERE IDCOURS=?";
            preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,course.getId()+"");
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
        Course course = (Course)obj;
        try{
            SQL="UPDATE COURSE SET NAMEC=?,CREDIT=?,CONTENTC=? WHERE IDCOURS=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(4,course.getId()+"");
            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2,course.getCredit());
            preparedStatement.setString(3,course.getContent());
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
    public Course find(int id) {
        String SQL = "SELECT * FROM COURSE WHERE IDCOURS = ?";
        Course course=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            preparedStatement.setString(1,id+"");
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                String idCours =rs.getString("IDCOURS");
                String nameC  = rs.getString("NAMEC");
                int credit = rs.getInt("CREDIT");
                String contentC = rs.getString("CONTENTC");
                course = new Course(Integer.parseInt(idCours),nameC,credit,contentC);
            }
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            return course;
        }
    }

    public ArrayList<Course> findAll(){
        String SQL = "SELECT * FROM COURSE";
        Course course=null;
        ArrayList<Course> allCourse=new ArrayList<Course>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                String idCours =rs.getString("IDCOURS");
                String nameC  = rs.getString("NAMEC");
                int credit = rs.getInt("CREDIT");
                String contentC = rs.getString("CONTENTC");
                course = new Course(Integer.parseInt(idCours),nameC,credit,contentC);
                allCourse.add(course);
            }
            return allCourse;
        } catch (SQLException e) {
            e.printStackTrace();
            return allCourse;
        }
    }


}

package com.company.DAO;

import com.company.StudentCourse;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lhadj on 1/1/2017.
 */
public class StudentCourseDAO extends DAO {
    String SQL ;
    public StudentCourseDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Object obj) {
        StudentCourse studentCourse= (StudentCourse) obj;

        try{
            SQL="INSERT INTO STUDENT_COURS VALUES (?,?,?)";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setInt(1,studentCourse.getCourse().getId());
            preparedStatement.setString(2,studentCourse.getStudent().getId()+"");
            preparedStatement.setDouble(3,studentCourse.getNoteValue());
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
        StudentCourse studentCourse = (StudentCourse) obj;
        try {
            SQL = "DELETE FROM STUDENT_COURS WHERE IDPERSSONES=? AND IDCOURS=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setString(1,studentCourse.getStudent().getId()+"");
            preparedStatement.setString(2,studentCourse.getCourse().getId()+"");
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
        StudentCourse studentCourse= (StudentCourse) obj;
        try{
            SQL="UPDATE STUDENT_COURS SET NOTEVALUE=? WHERE IDPERSSONES=? AND IDCOURS=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL);
            preparedStatement.setDouble(1,studentCourse.getNoteValue());
            preparedStatement.setString(2,studentCourse.getStudent().getId()+"");
            preparedStatement.setString(3,studentCourse.getCourse().getId()+"");
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
    public StudentCourse find(int id) {
        return null;
    }

    public StudentCourse find(int idStudent,int idCourse) {
        String SQL = "SELECT * FROM STUDENT_COURS WHERE IDPERSSONES = ? AND IDCOURS= ?";
        StudentCourse studentCourse=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            preparedStatement.setString(1,idStudent+"");
            preparedStatement.setString(2,idCourse+"");
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                String idCours =rs.getString("IDCOURS");
                String idPerssone  = rs.getString("IDPERSSONES");
                Double notevalue = rs.getDouble("NOTEVALUE");
                studentCourse = new StudentCourse((new StudentDAO(connection)).find(Integer.parseInt(idPerssone)),(new CoursDAO(connection)).find(Integer.parseInt(idCours)));
                studentCourse.setNoteValue(notevalue);
            }
            return studentCourse;
        } catch (SQLException e) {
            e.printStackTrace();
            return studentCourse;
        }
    }

    public ArrayList<StudentCourse> findAll(){
        String SQL = "SELECT * FROM STUDENT_COURS";
        ArrayList<StudentCourse> allStudentsCours = new ArrayList<StudentCourse>() ;
        StudentCourse studentCourse=null;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SQL);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                String idCours =rs.getString("IDCOURS");
                String idPerssone  = rs.getString("IDPERSSONES");
                Double notevalue = rs.getDouble("NOTEVALUE");
                studentCourse = new StudentCourse((new StudentDAO(connection)).find(Integer.parseInt(idPerssone)),(new CoursDAO(connection)).find(Integer.parseInt(idCours)));
                studentCourse.setNoteValue(notevalue);
                allStudentsCours.add(studentCourse);
            }
            return allStudentsCours;
        } catch (SQLException e) {
            e.printStackTrace();
            return allStudentsCours;
        }
    }
}

package com.company;

import com.company.DAO.CoursDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        String BD_URL="jdbc:oracle:thin:@localhost:1521:XE";
        String USER = "bda1";
        String PASSWORD ="bda1";

        try{
            connection = DriverManager.getConnection(BD_URL,USER,PASSWORD);
            connection.setAutoCommit(false);
            CoursDAO coursDAO = new CoursDAO(connection);


//            Course course = new Course(20,"CAW3",5,"Conception application web");
//
//            coursDAO.create(course);

//            coursDAO.update(course);

//            coursDAO.delete(course);

              Course course1 = coursDAO.find(2);

              System.out.println(course1.getName());

//            ArrayList<Course> courses = coursDAO.findAll();


//            AddressDAO addressDAO = new AddressDAO(connection);

//            addressDAO.create(new Address(50,"daradji pharmacie","khroub",
//                    "25000","Constantine"));

//            addressDAO.delete(new Address(50,"daradji pharmacie","khroub",
//                    "25000","Constantine"));

//            addressDAO.update(new Address(50,"1013 batiment","khroub",
//                   "25000","Constantine"));

//            Address address = addressDAO.find(50);
//            System.out.println(address.getStreet());

//            ArrayList<Address> addresses = addressDAO.findAll();
////            System.out.println(addresses.get(addresses.size()-1).getStreet());
//
//            StudentDAO studentDAO = new StudentDAO(connection);
//            Student student = new Student(20,"gasmi","amar montasar billah","gas@mail.com",new Date(new java.util.Date().getTime()),"8998",14.04);
//            student.setAddress(new Address(50,"1013 batiment","khroub",
//                     "25000","Constantine"));
////            studentDAO.create(student);
//
////            studentDAO.update(student);
//
////            studentDAO.delete(student);
//
////            Student student1 = studentDAO.find(20);
////            System.out.println(student1.getLastName());
//
////            ArrayList<Student> students = studentDAO.findAll();
////            System.out.println(students.get(students.size()-1).getLastName());
//
//            StudentCourseDAO studentCourseDAO = new StudentCourseDAO(connection);
//            StudentCourse studentCourse = new StudentCourse(student,new Course(10,"PJA",6,"Programation java avancer"));
//            studentCourse.setNoteValue(18);
//
////            studentCourseDAO.create(studentCourse);
//
////            studentCourseDAO.update(studentCourse);
//
////            studentCourseDAO.delete(studentCourse);
//
////            System.out.println(studentCourseDAO.find(student.getId(),10).getNoteValue());
////            System.out.println(studentCourseDAO.findAll().get(5).getNoteValue());
//
//            TeacherDAO teacherDAO = new TeacherDAO(connection);
//
//            Teacher teacher = new Teacher(30,"chaouech ahmed","ahmed chaouki","ac.chaouech@univ2.com",new Date(new java.util.Date().getTime()),5);
//            teacher.setAddress(new Address(50,"900","khroub",
//                    "25000","Constantine"));
////            teacherDAO.create(teacher);
//
////            teacherDAO.update(teacher);
//
////            teacherDAO.delete(teacher);
//
////              Teacher teacher1 = teacherDAO.find(30);
//
//
////            ArrayList<Teacher> teachers = teacherDAO.findAll();
////
////            System.out.println(teachers.get(teachers.size()-1).getLastName());

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}

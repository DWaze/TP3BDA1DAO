package com.company;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by lhadj on 12/7/2016.
 */
public class Student extends Person{
    private String numCard;
    private double bacNote ;
    private List<StudentCourse> studentCourseRow;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String email, Date dateOfBirth, String numCard, double bacNote) {
        super(id, firstName, lastName, email, dateOfBirth);
        this.numCard = numCard;
        this.bacNote = bacNote;
        studentCourseRow = new ArrayList<StudentCourse>();
    }

    public void addCourse(Course course){
        StudentCourse sc3 = new StudentCourse(this, course);
        course.addStudentCourse(sc3);
        this.addStudentCourse(sc3);
    }



    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        return this.getId()== student.getId();
    }

    public List<StudentCourse> getArray(){
        return (studentCourseRow);
    }

    public void remove(StudentCourse student){
        studentCourseRow.remove(student);
    }


    public void addStudentCourse(StudentCourse studentCourse){
        if(!studentCourseRow.contains(studentCourse)){
            if(studentCourse.getStudent()!=null){
                studentCourse.getStudent().remove(studentCourse);
            }
            studentCourse.setStudent(this);
            studentCourseRow.add(studentCourse);
        }
    }

    public String getNumCard() {
        return numCard;
    }

    public double getBacNote() {
        return bacNote;
    }

    public List<StudentCourse> getrStudentCourse() {
        return studentCourseRow;
    }


    public double getNoteAverage (){
        List<StudentCourse> course = this.getrStudentCourse();
        double note=0;
        double i=0;
        for(StudentCourse rcourse :course){
            note +=rcourse.getNoteValue();
            i++;
        }
        return note/i;
    }

}

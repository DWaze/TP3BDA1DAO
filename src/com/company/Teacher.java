package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lhadj on 12/7/2016.
 */
public class Teacher extends Person{

    public static final int GRADE_DOCTOR=3;

    public int getGrade() {
        return grade;
    }

    private int grade ;
    private List<Course> course ;

    public Teacher(int id, String firstName, String lastName, String email, Date dateOfBirth, int grade) {
        super(id, firstName, lastName, email, dateOfBirth);
        this.grade = grade;
        course= new ArrayList<Course>();
    }


    public void addCourse(Course course){
        if(!this.course.contains(course)){
            this.course.add(course);
        }
    }

    public List<Course> getrCourse() {
        return course;
    }


    public int noteStudent(){
        return noteStudent();
    }


}

package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhadj on 12/7/2016.
 */
public class Course {
    private int id ;
    private String name ;
    private int credit ;
    private String content ;
    private List<StudentCourse> studentCourseRow;

    public Course(int id, String name, int credit, String content) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.content = content;
        studentCourseRow =new ArrayList<StudentCourse>();
    }

    public void addStudent(Student student){
        StudentCourse sc1 = new StudentCourse(student, this);
        this.addStudentCourse(sc1);
        student.addStudentCourse(sc1);
    }



    public List<StudentCourse> getrStudentCourse(){
        return studentCourseRow;
    }

    public void remove(StudentCourse studentCourse){
        studentCourseRow.remove(studentCourse);
    }


    public void addStudentCourse(StudentCourse studentCourse){
        if(!studentCourseRow.contains(studentCourse)){
            if(studentCourse.getCourse()!=null){
                studentCourse.getStudent().remove(studentCourse);
            }
            studentCourse.setCourse(this);
            studentCourseRow.add(studentCourse);

        }
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public String getContent() {
        return content;
    }
}

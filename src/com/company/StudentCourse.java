package com.company;

/**
 * Created by lhadj on 12/7/2016.
 */
public class StudentCourse {
    private double noteValue ;

    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    private Student student ;
    private Course course;

    public Student getStudent(){
        return student;
    }

    public Course getCourse(){
        return course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course){
        this.course=course;
    }

    public void setNoteValue(double noteValue) {
        this.noteValue = noteValue;
    }

    public double getNoteValue() {

        return noteValue;
    }

    public void addStudent(Student student){
        if(!student.getArray().contains(this)){
            if(this.student!=null){
                this.student.remove(this);
            }
            this.setStudent(student);
            student.getArray().add(this);
        }
    }

    public void AddCourse(Course course){
        if(!course.getrStudentCourse().contains(this)){
            if(this.course != null){
                this.course.remove(this);
            }
            this.setCourse(course);
            course.getrStudentCourse().add(this);
        }

    }






}

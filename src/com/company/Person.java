package com.company;

import java.util.Date;

/**
 * Created by lhadj on 11/30/2016.
 */
public abstract class Person {
    protected int id ;
    protected String firstName ;
    protected String lastName ;
    protected String email;
    protected Date dateOfBirth ;
    private Address adr ;


    public void addAddress(Address adr){
        this.setAddress(adr);
    }

    public Address getrAddress(){
        return (adr);
    }

    public void setAddress(Address adr){
        this.adr=adr;
    }


    public Person() {
    }

    public Person(int id, String firstName, String lastName, String email, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName(){
        return getFirstName()+" "+getLastName();
    }

}

package com.company;

/**
 * Created by lhadj on 11/30/2016.
 */
public class Address {
    private int id ;
    private String street;
    private String City;
    private String zipCode;
    private String countery ;

    public Address(int id, String street, String city, String zipCode, String countery) {
        this.id = id;
        this.street = street;
        City = city;
        this.zipCode = zipCode;
        this.countery = countery;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return City;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountery() {
        return countery;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountery(String countery) {
        this.countery = countery;
    }


    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", street='" + street + '\'' +
                ", City='" + City + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", countery='" + countery + '\'' +
                '}';
    }
}

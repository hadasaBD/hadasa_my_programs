package com.company;

import java.io.Serializable;

/**
 * Created by eladlavi on 22/03/2017.
 */
public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private int nationalId;

    public Person(String firstName, String lastName, int nationalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public String toString() {
        return "person first name: " + firstName + " last name: " + lastName + " national Id : " + nationalId;
    }
}

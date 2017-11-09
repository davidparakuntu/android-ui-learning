package com.example.davidma.tab.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidma on 11/10/17.
 */

public class Contact {
    private String name;
    private List<String> phoneNumbers = new ArrayList<>();
    private String emailID;
    private int age;
    private String proffesion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }
}

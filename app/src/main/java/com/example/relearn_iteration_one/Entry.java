package com.example.relearn_iteration_one;

import android.widget.EditText;

public class Entry {
    private String name;
    private String email;
    private String number;

    public Entry(){
        // Public no arg constructor for FireStore
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Entry(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;

    }


}

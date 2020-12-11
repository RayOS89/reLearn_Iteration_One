package com.example.relearn_iteration_one;

import com.google.firebase.firestore.Exclude;

public class Entry {
    private String ID;
    private String name;
    private String email;
    private String number;
    private int priority;

    public Entry(){
        // Public no arg constructor for FireStore
    }
    @Exclude // prevents document Id from being requested as it is redundant data. idea from Coding in Flow  https://www.youtube.com/playlist?list=PLrnPJCHvNZuDrSqu-dKdDi3Q6nM-VUyxD
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Entry(String name, String email, String number, int priority) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.priority = priority;

    }


}

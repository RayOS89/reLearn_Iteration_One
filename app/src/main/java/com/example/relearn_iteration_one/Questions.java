package com.example.relearn_iteration_one;

import android.widget.Spinner;

import com.google.firebase.firestore.Exclude;

public class Questions {



    private String spinnerA;
    private String ID;
    private String question;
    private String answer;
    private String a;
    private String b;
    private String c;
    private String d;


    public Questions(String spinner, String question, String answer, String a, String b, String c, String d) {
        // Public no arg constructor for FireStore
        this.spinnerA = spinner;
        this.question = question;
        this.answer = answer;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Exclude
    // prevents document Id from being requested as it is redundant data. idea from Coding in Flow  https://www.youtube.com/playlist?list=PLrnPJCHvNZuDrSqu-dKdDi3Q6nM-VUyxD
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
    public String getSpinnerA() { return spinnerA; }

    public void setSpinnerA(String spinnerA) { this.spinnerA = spinnerA; }

    }





package com.lanthier.benjamin.assignment1;

import java.util.Random;

public class Assignment {
    //Variables
    private int id;
    private String title;
    private float grade;

    //==================================================================================================
    //Methods
    //Constructor
    //Generates random assignment title and grade
    public Assignment(int id) {
        Random rng = new Random();
        this.id = id;
        this.title = "Assignment " + id;
        this.grade = rng.nextInt(100) + 1;
    }

    public Assignment(int id, String title, int grade) {
        this.title = title;
        this.grade = grade;
        this.id = id;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    //Returning an assignment in the form of a string
    //With a Percentage when false, Letter when true
    public String getAsString(boolean letterFormat) {
        if (!letterFormat) {
            return title + ": " + grade + "%";
        } else {
            if (grade > 0  && grade < 50) {return title + ": F";}
            else if (grade >= 50 && grade < 53) {return title + ": D-";}
            else if (grade >= 52 && grade < 57) {return title + ": D";}
            else if (grade >= 57 && grade < 60) {return title + ": D+";}
            else if (grade >= 60 && grade < 63) {return title + ": C-";}
            else if (grade >= 63 && grade < 67) {return title + ": C";}
            else if (grade >= 67 && grade < 70) {return title + ": C+";}
            else if (grade >= 70 && grade < 73) {return title + ": B-";}
            else if (grade >= 73 && grade < 77) {return title + ": B";}
            else if (grade >= 77 && grade < 80) {return title + ": B+";}
            else if (grade >= 80 && grade < 85) {return title + ": A-";}
            else if (grade >= 85 && grade < 90) {return title + ": A";}
            else if (grade >= 90 && grade <= 100) {return title + ": A+";}
            else {return title + ": N/A";}
        }
    }
}


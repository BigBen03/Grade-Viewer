package com.lanthier.benjamin.assignment1;

public class Profile {
    //Variables
    private String name;
    private String age;
    private String studentID;

//==================================================================================================
    //Methods
    //Constructor
    public Profile() {
        this.name ="";
        this.age = "";
        this.studentID = "";
    }

    public Profile(java.lang.String name, String age, String studentID) {
        this.name = name;
        this.age = age;
        this.studentID = studentID;
    }

    //Getters and Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAge() {return age;}
    public void setAge(String age) {this.age = age;}
    public String getStudentID() {return studentID;}
    public void setStudentID(String studentID) {this.studentID = studentID;}

    public String displayProfile() {
        return "Name: " + name + " \n " + "Age: " + age + " \n " +
                "Student ID: " + studentID;
    }
}

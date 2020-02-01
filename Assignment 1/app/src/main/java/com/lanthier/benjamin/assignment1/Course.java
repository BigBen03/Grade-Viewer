package com.lanthier.benjamin.assignment1;

import java.util.ArrayList;
import java.util.Random;

public class Course {
    //Variables
    private int id;
    private String title;
    private ArrayList <Assignment> assignments;

//==================================================================================================
    //Methods
    //Constructor
    //Generating a random course with random assignments
    public Course(int id, int numAssignments) {
        Random rng = new Random();
        int assignmentNum = rng.nextInt(numAssignments) +1;
        ArrayList<Assignment> tempAssignments = new ArrayList<Assignment>();

        for (int i=0; i < assignmentNum; i++)
            tempAssignments.add(new Assignment(i+1));

        this.id = id;
        this.title = "Course " + id;
        this.assignments = tempAssignments;
    }

    public Course(int id, String title, ArrayList <Assignment> assignments) {
        this.title = title;
        this.assignments = assignments;
        this.id = id;
    }

    //Getters and Setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public ArrayList<Assignment> getAssignments() {return assignments;}
    public void setAssignments(ArrayList<Assignment> assignments) {this.assignments = assignments;}

    public int getNumAssignments() {
        return assignments.size();
    }

    //Displaying all the whole course
    public String getAllAsString(boolean format) {
        String tempS = title + " \n ";
        if(!this.assignments.isEmpty()) {
            for (int i = 0; i < this.assignments.size(); i++) {
                tempS = tempS + this.assignments.get(i).getAsString(format) + " \n ";
            }
            return tempS;
        } else {return tempS + "This course has no assignments";}
    }
}

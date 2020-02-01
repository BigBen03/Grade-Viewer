package com.lanthier.benjamin.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceHelper {
    //Variable
    private SharedPreferences sharedPref;

//==================================================================================================
    //Methods
    //Constructor
    public SharedPreferenceHelper(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

//Profile Methods ==================================================================================
    //Getters and Setters
    public void saveProfileName(String name) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ProfileName", name).putString("Name",name).apply();
    }

    public void saveProfileAge(int age) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Age",String.valueOf(age)).apply();
    }

    public void saveProfileSID(int id) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Student_ID",String.valueOf(id)).apply();
    }

    public void saveProfileAll(String name, int age, int id) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ProfileName", name).putString("Name",name).putString("Age",String.valueOf(age))
                .putString("Student_ID",String.valueOf(id)).apply();
    }

    public String getProfileName() {return sharedPref.getString("ProfileName","");}
    public String getProfileAge() {return sharedPref.getString("Age", "");}
    public String getProfileID() {return sharedPref.getString("Student_ID","");}

//Grades Methods ===================================================================================
//Getters and Setters
    public boolean getGradeFormat() {return sharedPref.getBoolean("gradeFormat",false);}

    public void saveGradeFormat(boolean gradeFormat) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("gradeFormat", gradeFormat).apply();
    }
}

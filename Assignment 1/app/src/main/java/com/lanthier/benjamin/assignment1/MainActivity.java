/*
Created by: Benjamin Lanthier
Student ID: 40060560
 */

package com.lanthier.benjamin.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    //Variables
    private SharedPreferenceHelper profilePref;
    private Profile profile;
    private Button profileButton;
    private Button gradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConstructor();
        setToolBar();
        buttonListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        profileConstructor();

        if (profileInit()) {   //Sets the profile button's tag to profile name
            goToProfileAct();
        } else {
            String profileName = profilePref.getProfileName();
            profileButton.setText(profileName);
        }
    }

//==================================================================================================
    //Methods
    //Constructors
    protected void buttonConstructor() {
        profileButton = findViewById(R.id.profileButton);
        gradeButton = findViewById(R.id.gradeButton);
    }

    protected void profileConstructor() {
        try {
            profilePref = new SharedPreferenceHelper(getApplicationContext()); //= PreferenceManager

            profile = new Profile(profilePref.getProfileName(), profilePref.getProfileAge(), profilePref.getProfileID());
        } catch(Error e) {
            System.out.println(e);
        }
    }

    //Button set-up
    public void buttonListener() {
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileAct();
            }
        });

        gradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGradeAct();
            }
        });
    }

    //Sets-up the Toolbar to contain app name
    protected void setToolBar() {
        Toolbar mainToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionB = getSupportActionBar();
        //Disable the Up button
        actionB.setDisplayHomeAsUpEnabled(false);
    }

    //Profile Initialization
    public boolean profileInit() {
        return profile.getName().equals("") || profile.getAge().equals("")
                || profile.getStudentID().equals("");
        //true: went to profile
        //false: did not go to profile
    }

    //GOTOs
    public void goToProfileAct() {
        Intent startIntent = new Intent(getApplication(), profileActivity.class);
        startActivity(startIntent);
    }

    public void goToGradeAct() {
        Intent startIntent = new Intent(getApplication(), gradeActivity.class);
        startActivity(startIntent);
    }
}

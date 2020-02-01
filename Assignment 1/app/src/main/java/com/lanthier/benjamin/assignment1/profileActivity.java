package com.lanthier.benjamin.assignment1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

public class profileActivity extends AppCompatActivity {

    //Variables
    private SharedPreferenceHelper profilePref;
    private EditText nameText = null;
    private EditText ageText = null;
    private EditText idText = null;
    private Button saveButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater profileInflater = getMenuInflater();
        profileInflater.inflate(R.menu.profile_action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();

        profileActivity();
        if (profileInit()) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editProfile();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                nameText.setEnabled(true);
                ageText.setEnabled(true);
                idText.setEnabled(true);
                saveButton.setVisibility(View.VISIBLE);

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editProfile();
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//==================================================================================================
    //Methods
    //Internal Constructor
    protected void profileActivity() {
        profilePref = new SharedPreferenceHelper(getApplicationContext());
        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        idText = findViewById(R.id.idText);
        saveButton = findViewById(R.id.saveButton);

        nameText.setText(profilePref.getProfileName());
        ageText.setText(profilePref.getProfileAge());
        if (!profilePref.getProfileID().equals("")) {
            DecimalFormat form = new DecimalFormat("000000");
            idText.setText(form.format(Integer.parseInt(profilePref.getProfileID())));
        } else {idText.setText(profilePref.getProfileID());}
        nameText.setEnabled(false);
        ageText.setEnabled(false);
        idText.setEnabled(false);
    }

    //Sets-up the toolbar to contain app name and options
    protected void setToolBar() {
        Toolbar profileToolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(profileToolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionB = getSupportActionBar();
        //Enable the Up button
        actionB.setDisplayHomeAsUpEnabled(true);
    }

    //Checks if profile needs to be filled-up (used for onCreate)
    public boolean profileInit() {
        if(profilePref.getProfileName().equals("")
                ||profilePref.getProfileAge().equals("")
                ||profilePref.getProfileID().equals("")) {

            Toast.makeText(getApplicationContext(), "Please fill-in the profile information",
                    Toast.LENGTH_LONG).show();

            nameText.setEnabled(true);
            ageText.setEnabled(true);
            idText.setEnabled(true);
            saveButton.setVisibility(View.VISIBLE);

            return true; //Allowed profile edit
        } else {return false;} //did not allow profile edit
    }

    //Saving the edited info to SharedPreferences
    public void editProfile(){

        String name = nameText.getText().toString();
        String ageT = ageText.getText().toString();
        String idT = idText.getText().toString();

        try {
            if (name.equals("") || ageT.equals("") || idT.equals("")) {
                Toast.makeText(getApplicationContext(), "Please ensure all the text boxes " +
                        "are filled up", Toast.LENGTH_LONG).show();
            } else {
                int age = Integer.parseInt(ageT);
                int id = Integer.parseInt(idT);
                int id_length = idT.length();

                if ((age != 0) && (id != 0)) {
                    if ((age >= 18) && (id_length >= 6)) {
                        profilePref.saveProfileAll(name, age, id);

                        nameText.setEnabled(false);
                        ageText.setEnabled(false);
                        idText.setEnabled(false);
                        saveButton.setVisibility(View.INVISIBLE);

                        Toast.makeText(getApplicationContext(), "Saved!",
                                            Toast.LENGTH_LONG).show();
                    } else {
                        if (age < 18 && id_length < 6)
                            Toast.makeText(getApplicationContext(),"You must be 18 and older" +
                                    " to use this app and your student ID must be of 6 digits",
                                    Toast.LENGTH_LONG).show();
                        else if (age < 18)
                            Toast.makeText(getApplicationContext(), "You must be 18 and older" +
                                    " to use this app", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Your student ID must be" +
                                            " of 6 digits", Toast.LENGTH_LONG).show();
                    }
                } else if (id == 0)
                    Toast.makeText(getApplicationContext(), "Your student ID cannot be 0",
                                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Please ensure all the text " +
                                            "boxes are filled up", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

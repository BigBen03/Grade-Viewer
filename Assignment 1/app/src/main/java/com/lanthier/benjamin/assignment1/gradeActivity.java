package com.lanthier.benjamin.assignment1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class gradeActivity extends AppCompatActivity {

    //Variables
    private static int maxNumberOfCourses = 5;
    private static int maxNumberOfAssignments = 4;
    private static SharedPreferenceHelper gradePref;
    private ArrayList <Course> courses;
    private boolean letterFormat; //when false, the grades are in %/ true in letters

    //RecyclerView variables
    private RecyclerView recyclerView;
    private RecyclerView.Adapter gAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //Menu variables
    MenuItem toLetter;
    MenuItem toPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        setToolBar();
    }

    @Override
    protected void onStart() {
        super.onStart();

        gradeActivity();
        setupCourses();
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater gradeInflater = getMenuInflater();
        gradeInflater.inflate(R.menu.grade_action_bar, menu);

        toLetter = (MenuItem) menu.findItem(R.id.action_toLetter);
        toPercentage = (MenuItem) menu.findItem(R.id.action_toPercentage);

        if (!letterFormat) {
            setLetterVisible();
        } else {
            setPercentageVisible();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) { //toggle shown option

            case R.id.action_toLetter:
                setFormat(true); //sets saved format
                setPercentageVisible();
                break;

            case R.id.action_toPercentage:
                setFormat(false); //sets saved format
                setLetterVisible();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//==================================================================================================
    //Methods
    //Internal Constructor
    protected void gradeActivity() {
        gradePref = new SharedPreferenceHelper(getApplicationContext());
        letterFormat = gradePref.getGradeFormat();
    }

    //Sets up the action bar
    private void setToolBar() {
        Toolbar gradeToolbar = findViewById(R.id.gradeToolbar);
        setSupportActionBar(gradeToolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar actionB = getSupportActionBar();
        //Enable the Up button
        actionB.setDisplayHomeAsUpEnabled(true);
    }

    //Sets up RecyclerView i.e. courseList
    //setupCourses() must be called before the RecyclerViewer
    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);

        //Sets recyclerView to a fixed size since the number of courses is set when gradeActivity
        // is launched
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify adapter
        gAdapter = new courseAdapter(courses, getApplicationContext());
        recyclerView.setAdapter(gAdapter);
    }

    //sets up the random array of courses
    protected void setupCourses() {
        courses = new ArrayList<Course>();

        Random rng = new Random();
        int numberOfCourses =  rng.nextInt(maxNumberOfCourses) +1;

        for (int i = 0; i < numberOfCourses; i++)
            courses.add(new Course(i+1,maxNumberOfAssignments));
    }

    //Changes grade format from letters to % and vice-versa
    protected void setFormat(boolean gradeFormat) {
        this.letterFormat = gradeFormat;
        if(gradePref.getGradeFormat() != gradeFormat) gradePref.saveGradeFormat(gradeFormat);
        gAdapter.notifyDataSetChanged();
    }

//==================================================================================================
    //Setting action bar options visibility
    protected void setLetterVisible() { //toPercentage is now hidden and toLetter will show
        toLetter.setVisible(true);
        toPercentage.setVisible(false);
    }

    protected void setPercentageVisible() { //toLetter is now hidden and toPercentage will show
        toLetter.setVisible(false);
        toPercentage.setVisible(true);
    }

}

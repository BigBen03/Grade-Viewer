package com.lanthier.benjamin.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.courseViewHolder> {
    //Variables
    private ArrayList<Course> courses;
    private Context context;
    private assignmentAdapter aAdapter;
    private RecyclerView assignmentRecyclerView;
    private RecyclerView.LayoutManager asLayoutManager;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class courseViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textview;
        public View view;

        public courseViewHolder(View v) {
            super(v);
            view = v;
            textview = v.findViewById(R.id.course_view);
        }
    }

//==================================================================================================
    // Provide a suitable constructor (depends on the kind of dataset)
    public courseAdapter(ArrayList<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public courseAdapter.courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_cell, parent, false);

        //Pass view to holder
        return new courseViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(courseViewHolder holder, int position) {
        //Replacing contents with course
        holder.textview.setText(courses.get(position).getTitle());

        //Set RecyclerView for assignment
        assignmentRecyclerView = holder.view.findViewById(R.id.assignmentRecyclerView);

        //assignmentRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        asLayoutManager = new LinearLayoutManager(holder.view.getContext());
        assignmentRecyclerView.setLayoutManager(asLayoutManager);

        //set inner adapter for assignment - specify adapter
        aAdapter = new assignmentAdapter(courses.get(position).getAssignments(), context);
        assignmentRecyclerView.setAdapter(aAdapter);
    }
    //==================================================================================================
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (courses != null) return courses.size();
        else return 0;
    }
}

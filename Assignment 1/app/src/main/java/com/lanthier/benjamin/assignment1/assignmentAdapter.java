package com.lanthier.benjamin.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class assignmentAdapter extends RecyclerView.Adapter<assignmentAdapter.assignmentViewHolder>{
    private ArrayList<Assignment> assignments;
    SharedPreferenceHelper gradePref;
    boolean gradeFormat;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class assignmentViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView view;

        public assignmentViewHolder(View v) {
            super(v);
            view = v.findViewById(R.id.assignment_view);
        }
    }

    //==================================================================================================
    // Provide a suitable constructor (depends on the kind of dataset)
    public assignmentAdapter(ArrayList<Assignment> assignments, Context context) {
        this.assignments = assignments;
        gradePref = new SharedPreferenceHelper(context);
        gradeFormat = gradePref.getGradeFormat();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public assignmentAdapter.assignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assignment_cell, parent, false);

        //TextView courseTextView = v.findViewById(R.id.course_view);
        //Pass view to holder
        return new assignmentViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(assignmentViewHolder holder, int position) {
        holder.view.setText(assignments.get(position).getAsString(gradeFormat));
        //System.out.println(courses.get(position).getAsString(false));
        //Replacing contents with course
    }

    //==================================================================================================
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (assignments != null) return assignments.size();
        else return 0;
    }
}

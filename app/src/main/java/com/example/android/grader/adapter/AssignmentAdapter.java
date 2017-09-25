package com.example.android.grader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.grader.activities.MainActivity;
import com.example.android.grader.adapter.viewholder.AssignmentViewHolder;
import com.example.android.grader.databinding.AssignmentItemBinding;
import com.example.android.grader.models.Assignment;
import com.example.android.grader.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentViewHolder> {
    private List<Assignment> assignments;
    private Context context;

    public AssignmentAdapter(Context context, ArrayList<Assignment> assignments) {
        this.context = context;
        this.assignments = assignments;
    }

    @Override
    public AssignmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        AssignmentItemBinding itemBinding = AssignmentItemBinding.inflate(layoutInflater, parent, false);
        return new AssignmentViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder holder, int position) {
        final Assignment assignment = assignments.get(position);
        holder.assignmentTitle.setText(assignment.getTitle());
        holder.dueDate.setText("due " + Utilities.changeDateFormat(assignment.getDueAt(), "MMM d, yyyy"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).onAssignmentClick(assignment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public interface AssignmentClickHandler {
        void onAssignmentClick(Assignment assignment);
    }
}

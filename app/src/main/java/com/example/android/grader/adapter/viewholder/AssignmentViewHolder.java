package com.example.android.grader.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.android.grader.databinding.AssignmentItemBinding;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public class AssignmentViewHolder extends RecyclerView.ViewHolder {
    private AssignmentItemBinding binding;
    public TextView assignmentTitle;
    public TextView dueDate;

    public AssignmentViewHolder(AssignmentItemBinding itemBinding) {
        super(itemBinding.getRoot());
        binding = itemBinding;
        assignmentTitle = binding.assignmentTitle;
        dueDate = binding.dueDate;
    }
}

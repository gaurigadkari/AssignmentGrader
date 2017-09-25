package com.example.android.grader.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.grader.databinding.SubmissionItemBinding;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public class SubmissionViewHolder extends RecyclerView.ViewHolder {
    public SubmissionItemBinding binding;
    public ImageView avatar;
    public TextView studentName;
    public TextView submissionDate;

    public SubmissionViewHolder(SubmissionItemBinding itemBinding) {
        super(itemBinding.getRoot());
        binding = itemBinding;
        avatar = binding.avatar;
        studentName = binding.submittedBy;
        submissionDate = binding.submittedOn;
    }
}

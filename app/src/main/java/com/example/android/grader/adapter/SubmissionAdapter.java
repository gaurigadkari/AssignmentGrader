package com.example.android.grader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.android.grader.R;
import com.example.android.grader.activities.MainActivity;
import com.example.android.grader.adapter.viewholder.SubmissionViewHolder;
import com.example.android.grader.databinding.SubmissionItemBinding;
import com.example.android.grader.models.Submission;
import com.example.android.grader.utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionViewHolder> {
    private List<Submission> submissions;
    private Context context;

    public SubmissionAdapter(Context context, ArrayList<Submission> submissions) {
        this.context = context;
        this.submissions = submissions;
    }

    @Override
    public SubmissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        SubmissionItemBinding itemBinding = SubmissionItemBinding.inflate(layoutInflater, parent, false);
        return new SubmissionViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(SubmissionViewHolder holder, int position) {
        final Submission submission = submissions.get(position);
        holder.studentName.setText(submission.getAuthor().getFirstName() + " " + submission.getAuthor().getLastName());
        holder.submissionDate.setText("turned in " + Utilities.changeDateFormat(submission.getSubmittedAt(), "MMM d, yyyy"));
        Glide.clear(holder.avatar);
        holder.avatar.setImageResource(0);
        String imageUrl = submission.getAuthor().getAvatars().getSmall();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(holder.avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).onSubmissionClick(submission);
            }
        });
    }

    public interface SubmissionClickHandler {
        void onSubmissionClick(Submission submission);
    }

    @Override
    public int getItemCount() {
        return submissions.size();
    }
}

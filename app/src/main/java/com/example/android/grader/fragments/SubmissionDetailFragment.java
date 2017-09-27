package com.example.android.grader.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.grader.R;
import com.example.android.grader.databinding.FragmentSubmissionDetailBinding;
import com.example.android.grader.models.Submission;
import com.example.android.grader.utils.Utilities;

import org.parceler.Parcels;


public class SubmissionDetailFragment extends Fragment {
    private static final String SUBMISSION = "submission";
    private static final String ASSIGNMENT_TITLE = "assignment_title";
    private Submission submission;
    private FragmentSubmissionDetailBinding binding;
    private TextView studentName, submittedOn, content;
    private ImageView avatar;
    private Toolbar toolbar;
    private String assignmentTitle;
    private Context context;

    public SubmissionDetailFragment() {
        // Required empty public constructor
    }

    public static SubmissionDetailFragment newInstance(Submission submission, String assignmentTitle) {
        Bundle args = new Bundle();
        SubmissionDetailFragment submissionDetailFragment = new SubmissionDetailFragment();
        args.putParcelable(SUBMISSION, Parcels.wrap(submission));
        args.putString(ASSIGNMENT_TITLE, assignmentTitle);
        submissionDetailFragment.setArguments(args);
        return submissionDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        submission = Parcels.unwrap(getArguments().getParcelable(SUBMISSION));
        assignmentTitle = getArguments().getString(ASSIGNMENT_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_submission_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentName = binding.studentName;
        submittedOn = binding.submittedOn;
        content = binding.content;
        avatar = binding.avatar;
        toolbar = binding.toolbar;
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        toolbar.setTitle(assignmentTitle);
        studentName.setText(submission.getAuthor().getFirstName() + " " + submission.getAuthor().getLastName());
        submittedOn.setText(context.getResources().getString(R.string.turned_in) + " " + Utilities.changeDateFormat(submission.getSubmittedAt(), "MMM d, yyyy"));
        content.setText(submission.getContent());
        Glide.with(context).load(submission.getAuthor().getAvatars().getLarge()).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(avatar);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }
}

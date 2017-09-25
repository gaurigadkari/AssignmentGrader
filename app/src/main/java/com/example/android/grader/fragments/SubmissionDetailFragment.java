package com.example.android.grader.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private Submission submission;
    FragmentSubmissionDetailBinding binding;
    TextView studentName, submittedOn, content;
    ImageView avatar;

    public SubmissionDetailFragment() {
        // Required empty public constructor
    }

    public static SubmissionDetailFragment newInstance(Submission submission) {
        Bundle args = new Bundle();
        SubmissionDetailFragment submissionDetailFragment = new SubmissionDetailFragment();
        args.putParcelable(SUBMISSION, Parcels.wrap(submission));
        submissionDetailFragment.setArguments(args);
        return submissionDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        submission = (Submission) Parcels.unwrap(getArguments().getParcelable(SUBMISSION));
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
        studentName.setText(submission.getAuthor().getFirstName() + " " + submission.getAuthor().getLastName());
        submittedOn.setText("turned in " + Utilities.changeDateFormat(submission.getSubmittedAt(), "MMM d, yyyy"));
        content.setText(submission.getContent());
        Glide.with(getActivity()).load(submission.getAuthor().getAvatars().getLarge()).into(avatar);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
